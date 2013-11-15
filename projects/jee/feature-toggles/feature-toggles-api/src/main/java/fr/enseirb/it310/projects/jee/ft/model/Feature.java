package fr.enseirb.it310.projects.jee.ft.model;

import java.io.Serializable;


public class Feature implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private String key;

	public Feature(final String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
