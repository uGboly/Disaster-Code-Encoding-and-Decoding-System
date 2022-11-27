package com.bupt.disaster_encode.mapper;

import com.bupt.disaster_encode.domain.Region;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}