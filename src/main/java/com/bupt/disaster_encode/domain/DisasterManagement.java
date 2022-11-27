package com.bupt.disaster_encode.domain;

import java.io.Serializable;
import java.util.Date;

public class DisasterManagement implements Serializable {
    private Integer id;

    private String disasterCode;

    private String location;

    private String carrier;

    private String origin;

    private Date disasterDate;

    private String category;

    private String label;

    private String desc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisasterCode() {
        return disasterCode;
    }

    public void setDisasterCode(String disasterCode) {
        this.disasterCode = disasterCode == null ? null : disasterCode.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier == null ? null : carrier.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public Date getDisasterDate() {
        return disasterDate;
    }

    public void setDisasterDate(Date disasterDate) {
        this.disasterDate = disasterDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}