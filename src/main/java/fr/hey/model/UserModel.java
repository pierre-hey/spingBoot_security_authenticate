package fr.hey.model;

import java.util.Collection;
import java.util.Set;

import fr.hey.entity.Role;
import lombok.Data;

@Data
public class UserModel {

	private String email;
	private String password;
	private Collection<Role> authorities;
}
