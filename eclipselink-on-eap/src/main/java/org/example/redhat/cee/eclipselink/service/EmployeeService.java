package org.example.redhat.cee.eclipselink.service;

import org.example.redhat.cee.eclipselink.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Employee getById(long id) {
        return em.find(Employee.class, id);
    }

    @Transactional
    public Employee addUser(long id, String name) {
        Employee newOne = new Employee();
        newOne.setId(id);
        newOne.setName(name);
        em.persist(newOne);
        return newOne;
    }
}
