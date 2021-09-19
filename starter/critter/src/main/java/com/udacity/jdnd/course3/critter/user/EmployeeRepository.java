package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository {
    /*
    // This was my attempt using normal JpaRepository functions
    // It's not OK to pass in a set to containing! Has to be scalar
    List<Employee> findAllBySkillsContainingAndDaysAvailableContaining(Set<EmployeeSkill> skills, DayOfWeek day);
     */

    // Try with a criteria builder instead
    List<Employee> findEmployeeBySkillsAndAvailability(Set<EmployeeSkill> skills, DayOfWeek day);

    Employee save(Employee employee);
    Employee findById(Long employeeId);

}
