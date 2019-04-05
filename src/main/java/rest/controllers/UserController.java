package main.java.rest.controllers;

import main.java.models.bean.User;
import main.java.models.dao.UserDAO;
import main.java.rest.models.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users") // https://localhost:8080/users
public class UserController {

    @GetMapping(path="/{userId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable Long userId) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getById(userId);

        if(user != null) {
            UserRest userRest = user.getUserRest();

            return new ResponseEntity<>(userRest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRest userRequest) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.save(userRequest.getUser());

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        UserDAO userDAO = new UserDAO();
        userDAO.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
