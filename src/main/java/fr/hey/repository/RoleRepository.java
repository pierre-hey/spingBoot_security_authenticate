package fr.hey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hey.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
