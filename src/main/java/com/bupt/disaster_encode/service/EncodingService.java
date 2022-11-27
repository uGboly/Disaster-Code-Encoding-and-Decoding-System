package com.bupt.disaster_encode.service;

import com.bupt.disaster_encode.domain.DisasterManagement;
import com.bupt.disaster_encode.domain.Region;
import com.bupt.disaster_encode.dto.DisasterManagementPageVo;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public interface EncodingService {

    public Region getRegion(String regionId);

    public DisasterManagement decoding(String disasterCode) throws Exception;

    public void setDescThruFile(DisasterManagement disasterManagement, File file);

    public void setDescThruWord(DisasterManagement disasterManagement, String desc);

    public boolean insertDisasterManagement(List<DisasterManagement> disasterManagements);

    public List<DisasterManagement> getDisasterManagement(DisasterManagementPageVo vo) throws Exception;

    Integer getDisasterManagementCount(DisasterManagementPageVo vo) throws ParseException;
}
