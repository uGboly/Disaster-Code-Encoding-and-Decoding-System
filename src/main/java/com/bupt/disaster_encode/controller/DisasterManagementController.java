package com.bupt.disaster_encode.controller;

import com.bupt.disaster_encode.domain.DisasterManagement;
import com.bupt.disaster_encode.dto.AddDisasterManagementVo;
import com.bupt.disaster_encode.dto.DisasterManagementPageVo;
import com.bupt.disaster_encode.dto.ResponseEntity;
import com.bupt.disaster_encode.dto.ResultDto;
import com.bupt.disaster_encode.enums.ExceptionEnum;
import com.bupt.disaster_encode.service.EncodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/dm")
public class DisasterManagementController {

    @Autowired
    private EncodingService encodingService;

    /**
     * 查
     * @param vo
     * @return
     */
    @GetMapping()
    public ResponseEntity<ResultDto<DisasterManagement>> getDisasterManagement(DisasterManagementPageVo vo) {
        ResponseEntity<ResultDto<DisasterManagement>> responseEntity = new ResponseEntity<>();
        try{
            List<DisasterManagement> disasterManagementList = encodingService.getDisasterManagement(vo);
            ResultDto<DisasterManagement> result = new ResultDto<>();
            result.setTotalSize(encodingService.getDisasterManagementCount(vo));
            result.setPageNum(vo.getPageNum());
            result.setPageSize(vo.getPageSize());
            result.setData(disasterManagementList);
            responseEntity.setResult(result);
            responseEntity.setReturnCode(ExceptionEnum.SUCCESS.getReturnCode());
            responseEntity.setReturnMsg(ExceptionEnum.SUCCESS.getReturnMsg());
        } catch (Exception e) {
            responseEntity.setReturnCode(ExceptionEnum.SystemError.getReturnCode());
            responseEntity.setReturnMsg(ExceptionEnum.SystemError.getReturnMsg() + ": " + e.getMessage());
        }
        return responseEntity;
    }

    /**
     * 插
     * @param vo
     * @return
     */
    @PatchMapping()
    public ResponseEntity<Object> insertDisasterManagement(@RequestBody AddDisasterManagementVo vo) {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
        File file = vo.getFile();
        try {
            if (file.getName().endsWith(".xlxs")) {

            } else {
                // 解码
                DisasterManagement disasterManagement = encodingService.decoding(file.getName());
                // 根据carrier类型setDesc
                encodingService.setDescThruFile(disasterManagement, file);
                // 插入数据库
                List<DisasterManagement> list = new LinkedList<>();
                list.add(disasterManagement);
                encodingService.insertDisasterManagement(list);

                responseEntity.setReturnCode(ExceptionEnum.SUCCESS.getReturnCode());
                responseEntity.setReturnMsg(ExceptionEnum.SUCCESS.getReturnMsg());
            }
        } catch (Exception e) {
            responseEntity.setReturnCode(ExceptionEnum.SystemError.getReturnCode());
            responseEntity.setReturnMsg(ExceptionEnum.SystemError.getReturnMsg() + ": " + e.getMessage());
        }
        return responseEntity;
    }

}
