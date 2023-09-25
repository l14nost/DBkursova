package com.example.DBkursova.repository;

import com.example.DBkursova.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepo extends JpaRepository<Crew,Long> {

    @Query(value = "SELECT c.crew_id, COUNT(o.object_id) AS object_count " +
            "FROM Crew c " +
            "JOIN Objects o ON c.crew_id = o.crew_crew_id " +
            "GROUP BY c.crew_id, c.crew_number " +
            "HAVING COUNT(o.object_id) >= ALL ( " +
            "    SELECT COUNT(object_id) " +
            "    FROM Objects " +
            "    GROUP BY crew_crew_id " +
            ")",nativeQuery = true)
    List<Object[]> crewCount();
}
