package com.devsoft.myhotelapi.models;

import com.devsoft.myhotelapi.models.generics.ModelTimestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Country extends ModelTimestamp implements Serializable {

    private static Long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<City> cities;

    public Country(String name) {
        this.name = name;
        this.cities = new ArrayList<>();
    }

    public Country addCity(City city) {
        if (!this.cities.contains(city)) {
            this.cities.add(city);
        }
        return this;
    }
}
