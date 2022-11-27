package com.bupt.disaster_encode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> {

    Integer returnCode;

    String returnMsg;

    T result;

}
