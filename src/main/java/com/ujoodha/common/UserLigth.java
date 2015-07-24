package com.ujoodha.common;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(crlf = "UNIX", separator = ";")
public class UserLigth implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1183735466951121988L;

	@DataField(pos = 1)
	private String nom;
	@DataField(pos = 2)
	private String prenom;
	@DataField(pos = 3)
	private int age;
	@DataField(pos = 4)
	private String sexe;
	@DataField(pos = 5)
	private String id;

	public UserLigth() {
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [nom=").append(nom).append(", prenom=")
				.append(prenom).append(", age=").append(age).append("]");
		return builder.toString();
	}

	/**
	 * @return the _id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
