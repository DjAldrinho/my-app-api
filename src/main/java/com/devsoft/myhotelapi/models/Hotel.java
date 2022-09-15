package com.devsoft.myhotelapi.models;


import com.devsoft.myhotelapi.models.generics.ModelTimestamp;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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
    @ToString.Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hotel hotel = (Hotel) o;
        return id != null && Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
