package com.example.howtodoinjava.springbootrest;


import org.springframework.data.repository.CrudRepository;

    public interface UserRepository extends CrudRepository<User, Integer> {
}
