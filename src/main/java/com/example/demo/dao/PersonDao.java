package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Person;

@Repository
@Transactional
public class PersonDao {
	 @PersistenceContext
	private EntityManager entityManager;
	
	public void create(Person person) {
		entityManager.persist(person);
		return;
	}

	public void delete(Person person) {
		if(entityManager.contains(person)) {
			entityManager.remove(person);
		}else {
			entityManager.remove(entityManager.merge(person));
		}
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAll(){
		return entityManager.createQuery("from Person").getResultList();
	}
	
	public Person getById(String id) {
		System.out.println(entityManager.toString());
		return (Person) entityManager.createQuery(
				"from Person where id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}
