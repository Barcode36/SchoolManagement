package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import db.DbException;
import model.entites.Annotation;
import model.entites.Student;

public class AnnotationDao {
	
	EntityManager manager;
	
	public AnnotationDao(EntityManager manager) {
		// He needs a Connection to acess the database
		this.manager = manager;
	}
	
	public void insert(Annotation annotation) throws DbException {
		if(manager == null) {
			throw new DbException("DB Connection not instantiated");
		}
		manager.getTransaction().begin();
		manager.persist(annotation);
		manager.getTransaction().commit();
	}
	
	public void update(Annotation annotation) throws DbException {
		if(manager == null) {
			throw new DbException("DB Connection not instantiated");
		}
		manager.getTransaction().begin();;
		annotation = manager.merge(annotation);
		manager.getTransaction().commit();
	}
	
	public Annotation findById(Integer id) throws DbException {
		if(manager == null) {
			throw new DbException("DB Connection not instantiated");
		}
		Annotation a = manager.find(Annotation.class, id);
		if(a != null && a.getExcluded() == null) {
			return a;
		} else {
			return null;
		}
	}
	
	public void delete(Annotation annotation) throws DbException {
		if(manager == null) {
			throw new DbException("DB Connection not instantiated");
		}
		manager.getTransaction().begin();
		annotation = manager.find(Annotation.class, annotation.getId());
		annotation.setExcluded("S");
		annotation = manager.merge(annotation);
		manager.getTransaction().commit();
	}
	
}
