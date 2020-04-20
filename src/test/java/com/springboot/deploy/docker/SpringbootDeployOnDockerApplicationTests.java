package com.springboot.deploy.docker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.deploy.docker.model.User;
import com.springboot.deploy.docker.services.UserService;

@SpringBootTest
class SpringbootDeployOnDockerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private ObjectMapper objectMapper= new ObjectMapper();
   
	
	@Test
	@DisplayName("Test method findAllUsers() =>")
    public void findAllUsers_UserList() throws Exception {
	      when(userService.findAll()).thenReturn(Arrays.asList(new User()));
	      mockMvc.perform(get("/user/findall")
	              .accept(MediaType.APPLICATION_JSON))
	              .andExpect(status().isOk());
	      
	      verify(userService,times(1)).findAll();
   }
	
   @Test
   @DisplayName("Test method findUserById() =>")
   public void findUserById_UserAsResponse() throws Exception {
	      when(userService.findById(Mockito.anyLong())).thenReturn(Optional.empty());
	      mockMvc.perform(get("/user/findbyid/{id}",1L)
	              .accept(MediaType.APPLICATION_JSON))
	              .andExpect(status().isNotFound());
	      verify(userService,times(1)).findById(Mockito.anyLong());
   }
	
	@Test
	   @DisplayName("Test method createUser =>")
	   public void createUser_UserAsResponse() throws Exception {
	      
	      User usr = new User();
	      usr.setId(2L);
	      usr.setUserName("Alam");
	      usr.setUserEmail("abc@mail.com");
	      usr.setDob(new Date());
	      usr.setMobileNo("60111111111");
	 
	      when(userService.save(Mockito.any(User.class))).thenReturn(usr);
	    
	      when(userService.findById(Mockito.anyLong())).thenReturn(Optional.empty());
	      mockMvc.perform(post("/user/create/")
	              .contentType(MediaType.APPLICATION_JSON)
	              .content(objectMapper.writeValueAsString(usr))
	              .accept(MediaType.APPLICATION_JSON))
	              .andDo(print())
	              .andExpect(status().isOk());

	      verify(userService,times(1)).save(Mockito.any(User.class));
	   }

}
