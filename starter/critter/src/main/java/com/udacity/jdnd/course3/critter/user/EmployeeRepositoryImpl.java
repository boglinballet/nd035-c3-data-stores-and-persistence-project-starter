package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findEmployeeBySkillsAndAvailability(Set<EmployeeSkill> skills, DayOfWeek day){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        // Set up the list of predicates based on the skills needed
        Predicate[] predicates = new Predicate[skills.size()+1];
        int i = 0;
        for(EmployeeSkill skill : skills){
            predicates[i] = criteriaBuilder.isMember(skill, root.get("skills"));
            i++;
        }

        predicates[i] = criteriaBuilder.isMember(day, root.get("availability"));
        criteriaQuery.select(root).where(predicates);

        Query query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Employee save(Employee employee){
        if(employee.getId() == null || employee.getId() <= 0){
            entityManager.persist(employee);
        } else {
            employee = entityManager.merge(employee);
        }
        return employee;
    }

    @Override
    public Employee findById(Long employeeId){
        return entityManager.find(Employee.class, employeeId);
    }
}
