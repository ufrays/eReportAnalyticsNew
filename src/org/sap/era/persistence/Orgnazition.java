package org.sap.era.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name = "T_ORGNAZITION")
@NamedQueries({ @NamedQuery(name = "AllOrgnazitions", query = "select o from Orgnazition o"),
		@NamedQuery(name = "OrgnazitionsByParentID", query = "select o from Orgnazition o where o.parentOrgnazition.id= :parentID"),
		@NamedQuery(name = "OrgnazitionByID", query = "select o from Orgnazition o where o.id= :ID"),
		@NamedQuery(name = "OrgnazitionOfTop", query = "select o from Orgnazition o where o.orgnazitionLevel= 0") })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Orgnazition {

	/*
	 * Fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Basic
	private String typeID;
	@Basic
	private String name;
	@Basic
	private String parentID;
	@Basic
	private String parentName;
	@Basic
	private String type;
	@Basic
	private String reportDirect;
	@Basic
	private int orgnazitionLevel;
	@Basic
	private String description;

	/*
	 * Navigations
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orgnazition")
	private List<Person> person = new ArrayList<Person>();
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "parentOrgnazition")
	private Orgnazition parentOrgnazition;
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "parentOrgnazition")
	private List<Orgnazition> childOrgnazitions;

	/*
	 * Getters & Setters
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReportDirect() {
		return reportDirect;
	}

	public void setReportDirect(String reportDirect) {
		this.reportDirect = reportDirect;
	}

	@JsonIgnore
	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	@JsonIgnore
	public Orgnazition getParentOrgnazition() {
		return parentOrgnazition;
	}

	public void setParentOrgnazition(Orgnazition parentOrgnazition) {
		this.parentOrgnazition = parentOrgnazition;
	}

	public List<Orgnazition> getChildOrgnazition() {
		if (this.childOrgnazitions == null) {
			childOrgnazitions = new ArrayList<Orgnazition>();
		}
		return childOrgnazitions;
	}

	public void setChildOrgnazition(List<Orgnazition> childOrgnazition) {
		this.childOrgnazitions = childOrgnazition;
	}

	public int getOrgnazitionLevel() {
		return orgnazitionLevel;
	}

	public void setOrgnazitionLevel(int orgnazitionLevel) {
		this.orgnazitionLevel = orgnazitionLevel;
	}

	public void addOrgChild(Orgnazition child) {
		this.childOrgnazitions.add(child);
	}

	public void removeOrgChild(Orgnazition child) {
		if (!this.childOrgnazitions.remove(child)) {
			for (int i = 0; i < this.childOrgnazitions.size(); i++) {
				if (childOrgnazitions.get(i).getId() == child.getId()) {
					this.childOrgnazitions.remove(i);
					break;
				}
			}
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

}