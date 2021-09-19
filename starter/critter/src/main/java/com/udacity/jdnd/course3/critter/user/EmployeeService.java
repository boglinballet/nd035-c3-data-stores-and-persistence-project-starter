package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.*;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepositoryImpl employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> availability, Long employeeId){
        Employee employee = employeeRepository.findById(employeeId);
        employee.setDaysAvailable(availability);
        employeeRepository.save(employee);
    }

    public List<Employee> getAvailableEmployees(EmployeeRequestDTO employeeRequestDTO){
        List<Employee> availableEmployees = new ArrayList<>();
        // Loop through the different skills asked for
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        DayOfWeek day = employeeRequestDTO.getDate().getDayOfWeek();
        // Employees need to cover all requested skills to match the employee request
        /*
        // This doesn't work -> I thought covering one skill would be enough
        for(EmployeeSkill skill : skills){
            availableEmployees.addAll(employeeRepository.findAllBySkillsContainingAndDaysAvailableContaining(skill, day));
        }
         */

        return employeeRepository.findEmployeeBySkillsAndAvailability(skills, day);
    }
}
