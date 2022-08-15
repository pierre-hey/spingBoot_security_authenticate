package fr.hey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hey.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByEmail(String email);

}
