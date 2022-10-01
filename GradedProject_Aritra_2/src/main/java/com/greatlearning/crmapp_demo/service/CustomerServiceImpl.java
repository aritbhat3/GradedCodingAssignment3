package com.greatlearning.crmapp_demo.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.greatlearning.crmapp_demo.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;
	private Session session;

	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	public List<Customer> findAll() {

		Transaction tx = session.beginTransaction();

		// from "EntityName"
		List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
		tx.commit();

		return customers;
	}

	public Customer findByEmail(String email) {
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class, email);

		tx.commit();
		return customer;
	}

	public void save(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
	}

	public void deleteByEmail(String email) {
		Transaction tx = session.beginTransaction();

		try {
			Customer customer = session.get(Customer.class, email);
			session.delete(customer);
		} finally {
			tx.commit();
		}

	}

}
