package com.booleanuk.api.controller;

import com.booleanuk.api.dto.UserDto;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User u = new User(userDto);
        u = this.repository.save(u);
        return new ResponseEntity<User>(u, HttpStatus.CREATED);

}
    @PutMapping("{id}")
        public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        Optional<User> uOpt = this.repository.findById(id);

        if (uOpt.isPresent()) {
            User u = uOpt.get();
            u.setFirstName(userDto.getFirstName());
            u.setLastName(userDto.getLastName());
            u.setEmail(userDto.getEmail());
            u.setUserName(userDto.getUsername());
            u.setPhone(userDto.getPhone());
            u = this.repository.save(u);
            return new ResponseEntity<User>(u, HttpStatus.CREATED);
            }
        return ResponseEntity.notFound().build();
        }
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        Optional<User> uOpt = this.repository.findById(id);

        if (uOpt.isPresent()) {
            User u = uOpt.get();
            this.repository.delete(u);
            return ResponseEntity.ok(u);
    }
        return ResponseEntity.notFound().build();


}
}
