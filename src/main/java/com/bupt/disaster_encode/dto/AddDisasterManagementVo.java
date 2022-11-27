package com.bupt.disaster_encode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDisasterManagementVo {

    File file;

    String id;

}
