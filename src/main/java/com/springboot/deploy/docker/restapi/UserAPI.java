package com.springboot.deploy.docker.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.deploy.docker.exception.UserNotFoundException;
import com.springboot.deploy.docker.model.User;
import com.springboot.deploy.docker.services.UserService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Alam_
 *
 */


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAPI {

	private final UserService userService;

    @RequestMapping(value="/findall", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {
    	
    	return new ResponseEntity<List<User>>
    	          (userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
    	
    	return new ResponseEntity<User>
    	         (userService.save(user), HttpStatus.OK);
    }

    @RequestMapping(value="/findbyid/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable Long id) {
    	
    	return userService.findById(id)
                .map(user->new ResponseEntity<User>(user,HttpStatus.OK))
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }

 
	@RequestMapping(value="/deletebyid/{id}", method = RequestMethod.GET)
    public Map<String, Boolean> delete(@PathVariable Long id) {
    	
    	User user = userService.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found for this id :: " + id));
    	
    	userService.deleteUser(user);
    	Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
    }
}
