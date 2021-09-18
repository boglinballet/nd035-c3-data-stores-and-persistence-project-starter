package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> availability, Long employeeId){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if(optional.isPresent()){
            Employee employee = optional.get();
            employee.setAvailability(availability);
            employeeRepository.save(employee);
        }
        // TODO: consider some way to handle this error?
    }
}
