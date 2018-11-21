package com.example.sweater.Repositories;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.Roles;
import com.example.sweater.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Integer> {
    Roles findByusername(String username);
}
