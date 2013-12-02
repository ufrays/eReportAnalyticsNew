package org.sap.era.persistence;

import javax.persistence.*;

import org.sap.era.persistence.Orgnazition;

@Entity
@Table(name = "T_PERSON")
@NamedQueries({ @NamedQuery(name = "AllPersons", query = "select p from Person p"),
		@NamedQuery(name = "PersonsByOrgID", query = "select p from Person p where p.orgnazition.id = :orgID"),
		@NamedQuery(name = "PersonsByUserName", query = "select p from Person p where p.name = :userName") })
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Basic
	private String name;
	@Basic
	private String firstName;
	@Basic
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "orgnazition", nullable = false)
	private Orgnazition orgnazition;
	@Basic
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Orgnazition getOrgnazition() {
		return orgnazition;
	}

	public void setOrgnazition(Orgnazition orgnazition) {
		this.orgnazition = orgnazition;
	}

	public void setPassword(String param) {
		this.password = param;
	}

	public String getPassword() {
		return password;
	}

}
