package com.bupt.disaster_encode.mapper;

import com.bupt.disaster_encode.domain.DisasterManagement;

public interface DisasterManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisasterManagement record);

    int insertSelective(DisasterManagement record);

    DisasterManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisasterManagement record);

    int updateByPrimaryKey(DisasterManagement record);
}