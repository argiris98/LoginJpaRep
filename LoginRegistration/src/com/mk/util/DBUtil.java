package com.mk.util;
import com.mk.bean.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static EntityManagerFactory factory; 

	private static EntityManagerFactory getConnection() {	
		if(factory != null) {
			return factory;
		}
		factory = Persistence.createEntityManagerFactory( "User" );
		return factory;
	}

	public static User executeQuery(String username) throws Exception {
		User user = null;
		EntityManager em = getConnection().createEntityManager();
		user = em.find(User.class, username);
		em.close();
		return user;

	}
	
	public static void executeUpdate(String firstname,String lastname,String username,String password) throws Exception {
		EntityManager em = getConnection().createEntityManager();
		User user = new User(firstname,lastname,username,password);
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
		catch(Exception e) {
			if(em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		}
		finally {
			em.close();
		}
		
	}

}
