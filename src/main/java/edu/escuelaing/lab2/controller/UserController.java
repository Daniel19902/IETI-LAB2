package edu.escuelaing.lab2.controller;



import edu.escuelaing.lab2.data.User;
import edu.escuelaing.lab2.dto.UserDto;
import edu.escuelaing.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    private final AtomicLong id = new AtomicLong();

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById( @PathVariable String id ) {
        User user = userService.findById(id);
        if(user != null){
            return new ResponseEntity<> (user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto ) {
        User user = new User(userDto);
        user.setId(id.incrementAndGet());
        return new ResponseEntity<> (userService.create(user), HttpStatus.ACCEPTED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update(@RequestBody UserDto userDto, @PathVariable String id ) {
        User user = userService.findById(id);
        String idUSer = user.getId();
        User newUSer = new User(userDto);
        newUSer.setId(Long.parseLong(idUSer));
        userService.update(newUSer, id);
        return new ResponseEntity<>(newUSer, HttpStatus.OK);

    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete(@PathVariable String id ) {
        try {
            userService.deleteById(id);
            return new ResponseEntity <>(true, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.ACCEPTED);
        }
    }


}
