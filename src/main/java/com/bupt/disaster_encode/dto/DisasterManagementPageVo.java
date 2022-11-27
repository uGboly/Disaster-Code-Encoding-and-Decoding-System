package com.bupt.disaster_encode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisasterManagementPageVo extends PageVo{

    private Integer id;

    private String disasterCode;

    private String location;

    private String carrier;

    private String origin;

    private String disasterDate;

    private String category;

    private String label;

}
