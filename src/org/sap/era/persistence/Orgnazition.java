package org.sap.era.persistence;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.sap.era.persistence.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.sap.era.persistence.Orgnazition;

@Entity
@Table(name = "T_ORGNAZITION")

@NamedQueries({
	@NamedQuery(name = "AllOrgnazitions", query = "select o from Orgnazition o"),
	@NamedQuery(name = "OrgnazitionsByParentID", query = "select o from Orgnazition o where o.parentOrgnazition.id= :parentID"),
	@NamedQuery(name = "OrgnazitionByID", query = "select o from Orgnazition o where o.id= :ID"),
	@NamedQuery(name = "OrgnazitionOfTop", query = "select o from Orgnazition o where o.OrgnazitionLevel= 0")
})

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Orgnazition {

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
	private int OrgnazitionLevel;
	@Basic
	private String description;
	@OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="orgnazition")
	private List<Person> person = new ArrayList<Person>();
	@ManyToOne(cascade = { CascadeType.REFRESH}, optional=true)  
    @JoinColumn(name="parentOrgnazition")  
	private Orgnazition parentOrgnazition;
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, fetch=FetchType.LAZY, mappedBy="parentOrgnazition")  
	private List<Orgnazition> childOrgnazition;

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
		return childOrgnazition;
	}
	public void setChildOrgnazition(List<Orgnazition> childOrgnazition) {
		this.childOrgnazition = childOrgnazition;
	}
	public int getOrgnazitionLevel() {
		return OrgnazitionLevel;
	}
	public void setOrgnazitionLevel(int orgnazitionLevel) {
		OrgnazitionLevel = orgnazitionLevel;
	}
	public void addOrgChild(Orgnazition child){
		this.childOrgnazition.add(child);
	}
	public void removeOrgChild(Orgnazition child){
		for (int i = 0 ; i < this.childOrgnazition.size(); i++){
			if (childOrgnazition.get(i).getId() == child.getId()){
				this.childOrgnazition.remove(i);
				break;
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