package com.proyect_Ignacio.Proyect.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Integer> {

     Optional<User> findByEmail(String email);

     Optional<User> findByFirstName(String firstname);


     


}
