package com.springboot.deploy.docker.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.deploy.docker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
