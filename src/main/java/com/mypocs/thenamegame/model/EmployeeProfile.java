/**
 * 
 */
package com.mypocs.thenamegame.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Raj Thuppanna
 *
 */
@Entity
@Table(name = "employee_profile")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeProfile {

	@Id
	private String id;
	private String type;
	private String slug;
	private String jobTitle;
	private String firstName;
	private String lastName;
	
	@OneToOne @JoinColumn(name="headshot_id")
	private Headshot headshot;

	//private String[] socialLinks;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public Headshot getHeadshot() {
		return headshot;
	}

	public void setHeadshot(Headshot headshot) {
		this.headshot = headshot;
	}

	@Override
	public String toString() {
		return "EmployeeProfile [id=" + id + ", type=" + type + ", slug=" + slug + ", jobTitle=" + jobTitle
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", headshot=" + headshot + "]";
	}
	
	
}
