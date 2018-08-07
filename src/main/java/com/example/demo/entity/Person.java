package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity			//JPA实体
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue( generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	//strategy = GenerationType.AUTO generator="system-uuid" strategy="uuid"
	//表明自动生成的唯一标识
	private String id;
	private String name;
	private int sex;
	private int age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
