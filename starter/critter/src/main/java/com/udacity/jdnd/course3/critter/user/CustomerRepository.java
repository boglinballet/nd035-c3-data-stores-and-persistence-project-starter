package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    //Optional<Customer> findByPetsPetId(Long petId);

    /*
    @Query("select c.customer from Customer c where :petId in c.pets")
    Optional<Customer> findByPetId(Long petId);

     */
}
