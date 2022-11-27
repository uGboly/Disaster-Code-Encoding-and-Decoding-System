package com.bupt.disaster_encode.mapper;

import com.bupt.disaster_encode.domain.DisasterManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DisasterManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisasterManagement record);

    int insertSelective(DisasterManagement record);

    DisasterManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisasterManagement record);

    int updateByPrimaryKey(DisasterManagement record);

    int insertList(List<DisasterManagement> list);

    List<DisasterManagement> selectSelectiveByPage(@Param("disasterManagement") DisasterManagement disasterManagement, @Param("offset") int offset, @Param("limit") int limit);

    Integer selectSelectiveByPageCount(DisasterManagement disasterManagement);
}