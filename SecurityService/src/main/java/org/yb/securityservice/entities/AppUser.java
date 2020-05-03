package org.yb.securityservice.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String userName;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private boolean actived;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<AppRole> roles = new ArrayList<>();
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppUser(Long id, String userName, String password, boolean actived, List<AppRole> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.actived = actived;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public List<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", userName=" + userName + ", password=" + password + ", actived=" + actived
				+ ", roles=" + roles + "]";
	}
	

	
	

}
