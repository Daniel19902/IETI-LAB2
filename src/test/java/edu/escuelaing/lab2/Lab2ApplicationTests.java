package edu.escuelaing.lab2;

import edu.escuelaing.lab2.controller.UserController;
import edu.escuelaing.lab2.data.User;
import edu.escuelaing.lab2.dto.UserDto;
import edu.escuelaing.lab2.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Lab2ApplicationTests {

	@Autowired
	UserService userService;

	private final UserController userController = new UserController(userService);

	/** Petición GET para obtener todos los usuarios creados*/
	@Test
	void testGetAllUsers() {
		UserController userController = new UserController(userService);
		ResponseEntity<List<User>> users = userController.getAll();
		assertEquals(users.getStatusCode(), HttpStatus.OK);
	}

	/** Petición POST para la creación de un usuario*/
	@Test
	void createAUser() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		UserController userController = new UserController(userService);
		jsonObject.put("name", "Daniel");
		jsonObject.put("lastName", "Porras");
		jsonObject.put("email", "daniel@daniel.com");
		ResponseEntity<User> users = userController.create(new UserDto(jsonObject));
		assertEquals(Objects.requireNonNull(users.getBody()).getName(), "Daniel");
		assertEquals(Objects.requireNonNull(users.getBody()).getLastName(), "Porras");
		assertEquals(Objects.requireNonNull(users.getBody()).getEmail(), "daniel@daniel.com");
		assertEquals(users.getStatusCode(), HttpStatus.ACCEPTED);
	}

	/** Petición GET para encontrar un usuario existente con un respectivo id */
	@Test
	void findById() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		UserController userController = new UserController(userService);
		jsonObject.put("name", "Daniel");
		jsonObject.put("lastName", "Porras");
		jsonObject.put("email", "daniel@daniel.com");
		userController.create(new UserDto(jsonObject));
		ResponseEntity<User> user = userController.findById("1");
		assertEquals(Objects.requireNonNull(user.getBody()).getId(), "1");
		assertEquals(Objects.requireNonNull(user.getBody()).getName(), "Daniel");
		assertEquals(Objects.requireNonNull(user.getBody()).getLastName(), "Porras");
		assertEquals(Objects.requireNonNull(user.getBody()).getEmail(), "daniel@daniel.com");
	}

	/** Petición GET para encontrar un usuario que no se encuentra creado con un respectivo id */
	@Test
	void findByIdNoFound() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		UserController userController = new UserController(userService);
		jsonObject.put("name", "Daniel");
		jsonObject.put("lastName", "Porras");
		jsonObject.put("email", "daniel@daniel.com");
		userController.create(new UserDto(jsonObject));
		ResponseEntity<User> user = userController.findById("2");
		assertEquals(user.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	public void createUser(UserController userController) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Daniel");
		jsonObject.put("lastName", "Porras");
		jsonObject.put("email", "daniel@daniel.com");
		userController.create(new UserDto(jsonObject));
	}

	/** Petición DELETE para eliminar un usuario que es encuentra en la base de datos*/
	@Test
	void deleteById() throws JSONException {
		UserController userController = new UserController(userService);
		createUser(userController);
		userController.delete("1");
		assertEquals(userController.findById("1").getStatusCode(), HttpStatus.NOT_FOUND);
	}

	public JSONObject updateUser(String name, String mail, String lastName) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("lastName", lastName);
		jsonObject.put("email", mail);
		return jsonObject;
	}

	/** Petición PUT para actualizar un usuario ya creado*/
	@Test
	void update() throws JSONException {
		UserController userController = new UserController(userService);
		createUser(userController);
		ResponseEntity<User> userUpdate = userController.update(new UserDto(updateUser("Daniel", "Daniel@danil.com", "Martin")), "1");
		assertEquals(userUpdate.getStatusCode(), HttpStatus.OK);
		assertEquals(Objects.requireNonNull(userUpdate.getBody()).getLastName(), "Martin");
	}


}
