package fr.hey.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="utilisateurs")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	@JsonIgnore
	private String password;
	
//	@OneToMany(mappedBy = "utilisateur")
//	private Set<Authority> authorities;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="utilisateur_roles",
			joinColumns = @JoinColumn(
					name="utilisateur_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name="role_id",referencedColumnName ="id")
			)
	private Collection<Role> roles;
	

}
