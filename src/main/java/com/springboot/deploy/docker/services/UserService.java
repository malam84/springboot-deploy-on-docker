package com.springboot.deploy.docker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.deploy.docker.model.User;
import com.springboot.deploy.docker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Alam_
 *
 */

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository; 
	
	 public Optional<User> findById(Long id) {
	        return userRepository.findById(id);
	 }
	
	public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User usr) {
    	userRepository.delete(usr);
    }
}
