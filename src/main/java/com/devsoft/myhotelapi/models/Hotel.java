package com.devsoft.myhotelapi.models;


import com.devsoft.myhotelapi.models.generics.ModelTimestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Hotel extends ModelTimestamp implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private City city;

    @NotBlank
    private String address;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<User> admins;

    public Hotel(String name, City city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public Hotel addAdmin(User user) {
        if (this.admins.contains(user)) {
            this.admins.add(user);
        }

        return this;
    }

}
