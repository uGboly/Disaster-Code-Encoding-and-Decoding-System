package com.bupt.disaster_encode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region implements Serializable {
    private String id;

    private String province;

    private String city;

    private String county;

    private String street;

    private String neighborhood;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return province + city + county + street + neighborhood;
    }
}