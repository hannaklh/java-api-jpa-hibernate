package com.booleanuk.api.model;

import com.booleanuk.api.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="email")
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_name")
    private String userName;
    @Column(name="phone")
    private String phone;

    public User(UserDto userdto) {
        setEmail(userdto.getEmail());
        setFirstName(userdto.getFirstName());
        setLastName(userdto.getLastName());
        setUserName(userdto.getUsername());
        setPhone(userdto.getPhone());
    }

}
