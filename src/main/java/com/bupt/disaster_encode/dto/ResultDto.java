package com.bupt.disaster_encode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> {

    Integer totalSize;

    Integer pageNum;

    Integer pageSize;

    List<T> data;

}
