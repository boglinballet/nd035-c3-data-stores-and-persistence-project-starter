package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    // Checked out the forum and my solution should work!
    // https://knowledge.udacity.com/questions/428195

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public Employee(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getEmployeeSkills() {
        return skills;
    }

    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.skills = employeeSkills;
    }

    public Set<DayOfWeek> getAvailability() {
        return daysAvailable;
    }

    public void setAvailability(Set<DayOfWeek> availability) {
        this.daysAvailable = availability;
    }
}
