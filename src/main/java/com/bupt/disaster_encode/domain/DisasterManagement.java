package com.bupt.disaster_encode.domain;

import com.bupt.disaster_encode.domain.Enum.Carrier;
import com.bupt.disaster_encode.domain.Enum.Category;
import com.bupt.disaster_encode.domain.Enum.Label;
import com.bupt.disaster_encode.domain.Enum.Origin;
import com.bupt.disaster_encode.dto.DisasterManagementPageVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public DisasterManagement(String disasterCode, Region region, Date disasterDate, Origin origin, Carrier carrier, Category category, Label label) {
        this.disasterCode = disasterCode;
        this.location = region.toString();
        this.carrier = carrier.FORM;
        this.origin = origin.subClassName;
        this.disasterDate = disasterDate;
        this.category = category.subClassName;
        this.label = label.labelName;
    }

    public DisasterManagement(DisasterManagementPageVo vo) throws ParseException {
        if (vo.getId() != null) {
            this.id = vo.getId();
        }
        if (vo.getDisasterCode() != null) {
            this.disasterCode = vo.getDisasterCode();
        }
        if (vo.getCarrier() != null) {
            this.carrier = vo.getCarrier();
        }
        if (vo.getOrigin() != null) {
            this.origin = vo.getOrigin();
        }
        if (vo.getDisasterDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
            this.disasterDate = sdf.parse(vo.getDisasterDate());
        }
        if (vo.getCategory() != null) {
            this.category = vo.getCategory();
        }
        if (vo.getLabel() != null) {
            this.label = vo.getLabel();
        }
    }
}