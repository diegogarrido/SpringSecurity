package com.codingdojo.java.util;

public enum Role {
	SUPER_ADMIN("ROLE_SUPER_ADMIN"), ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
	private String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
