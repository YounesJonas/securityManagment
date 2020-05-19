package org.yb.securityservice.rest;

import java.io.Serializable;

public class ConnectedUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	/*
	 * constructor without params
	 */

	public ConnectedUser() {
		super();
	}

	/**
	 * 
	 */

	public ConnectedUser(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
