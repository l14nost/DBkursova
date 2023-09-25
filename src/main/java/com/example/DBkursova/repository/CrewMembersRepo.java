package com.example.DBkursova.repository;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.entity.CrewMember;
import com.example.DBkursova.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CrewMembersRepo extends JpaRepository<CrewMember,Long> {
    @Query(value = "select * " +
            "from crewmembers " +
            "where crew_crew_id = :idCrew " +
            "order by member_name ", nativeQuery = true)
    List<CrewMember> findAllCrew(@Param("idCrew")int idCrew);

    @Query(value = "select crew_crew_id,count(*) " +
            "from crewmembers " +
            "group by crew_crew_id",nativeQuery = true)
    List<Object[]> countMemberForAll();

    @Query(value = "SELECT member_id, member_name, member_position, member_salary,'CrewMembers' AS source " +
            "FROM CrewMembers " +
            "WHERE crew_crew_id = :crew " +
            "UNION " +
            "SELECT  object_id, street, repair_date, damage_type,'Objects' AS source " +
            "FROM Objects " +
            "WHERE crew_crew_id = :crew",nativeQuery = true)
    List<Object[]> crewMemObject(int crew);
    @Modifying
    @Query(value = "update crewmembers" +
            " set member_description = 'Член активной бригады'" +
            " where crew_crew_id in (" +
            " select c.crew_id" +
            " from Crew c" +
            " JOIN Objects o ON c.crew_id = o.crew_crew_id" +
            " GROUP BY c.crew_id, c.crew_number" +
            " HAVING COUNT(o.object_id) >= ALL (" +
            " SELECT COUNT(object_id)" +
            " FROM Objects" +
            " GROUP BY crew_crew_id" +
            " )" +
            ")", nativeQuery = true)
    @Transactional
    void updateMemberDescriptionActive();
    @Modifying
    @Query(value = "UPDATE crewmembers" +
            " SET member_description  = 'Член не активной бригады'" +
            " WHERE crew_crew_id not in (" +
            " SELECT c.crew_id" +
            " FROM Crew c" +
            " JOIN Objects o ON c.crew_id = o.crew_crew_id" +
            " GROUP BY c.crew_id, c.crew_number" +
            " HAVING COUNT(o.object_id) >= ALL (" +
            "  SELECT COUNT(object_id)" +
            "  FROM Objects" +
            "  GROUP BY crew_crew_id" +
            " )" +
            ")",nativeQuery = true)
    @Transactional
    void updateMemberDescriptionNotActive();
}


