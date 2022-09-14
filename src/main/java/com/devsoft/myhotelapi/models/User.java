package com.devsoft.myhotelapi.models;

import com.devsoft.myhotelapi.models.generics.ModelTimestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User extends ModelTimestamp implements Serializable {


    private static Long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Hotel hotel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.hotel = hotel;
    }
}
