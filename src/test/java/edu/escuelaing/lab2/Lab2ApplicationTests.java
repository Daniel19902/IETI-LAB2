package edu.escuelaing.lab2;

import edu.escuelaing.lab2.controller.UserController;
import edu.escuelaing.lab2.data.User;
import edu.escuelaing.lab2.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Lab2ApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void testGetAllUsers() {

		UserController userController = new UserController(userService);
		ResponseEntity<List<User>> users = userController.getAll();
		assertEquals(users.getStatusCode(), HttpStatus.ACCEPTED);



	}

}
