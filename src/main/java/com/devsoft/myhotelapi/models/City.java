package com.devsoft.myhotelapi.models;

import com.devsoft.myhotelapi.models.generics.ModelTimestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class City extends ModelTimestamp implements Serializable {

    private static Long serialVersionUID = 2L;

    @Id
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
