package com.example.DBkursova.service;



import com.example.DBkursova.entity.User;
import com.example.DBkursova.repository.UserRepo;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByMail(username);

        if (user.isPresent()){
            User authAdmin = User.builder().mail(user.get().getMail()).password(user.get().getPassword()).role(user.get().getRole()).build();
            return authAdmin;
        }
        else {
            throw new UsernameNotFoundException("Invalid login or pass");
        }

    }
    public String getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String username = userDetails.getUsername();
            String role = userDetails.getAuthorities().toString();


            return username+" "+role;
        } else {
            return "error";
        }
    }
}
