package com.test_task.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.test_task.demo.entities.Role;
import com.test_task.demo.entities.User;
import com.test_task.demo.repositories.RoleRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

//    public Role getUserRole() {
//        return roleRepository.findByName("ROLE_USER").get();
//    }  old code

    public Role getUserRole() {
        Optional<Role> userRoleOptional = roleRepository.findByName("ROLE_USER");
        if (userRoleOptional.isPresent()) {
            return userRoleOptional.get();
        } else {
            throw new RuntimeException("Role with name 'ROLE_USER' not found.");
        }
    }
}