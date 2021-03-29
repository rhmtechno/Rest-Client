package com.exam.client.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RestUser")
@ToString
public class User {
	@Id
	@GeneratedValue
	private int id;
	private int clientId;
	private String name;
	private String email;
//	public User(int clientId, String name, String email) {
//		super();
//		this.clientId = clientId;
//		this.name = name;
//		this.email = email;
//	}

     
	

}
