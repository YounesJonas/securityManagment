package org.yb.securityservice.rest;

import java.io.Serializable;

public class Registartion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String passwordConfirmation;
	
	/**
	 * constructor without field
	 */
	public Registartion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Registartion(String username, String password, String passwordConfirmation) {
		super();
		this.username = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public String toString() {
		return "Registartion [username=" + username + ", password=" + password + ", passwordConfirmation="
				+ passwordConfirmation + "]";
	}
	
	
	
	
	
}
