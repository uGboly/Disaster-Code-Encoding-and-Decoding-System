package com.bupt.disaster_encode.controller;

import com.bupt.disaster_encode.domain.DisasterManagement;
import com.bupt.disaster_encode.dto.DisasterManagementPageVo;
import com.bupt.disaster_encode.dto.ResponseEntity;
import com.bupt.disaster_encode.dto.ResultDto;
import com.bupt.disaster_encode.enums.ExceptionEnum;
import com.bupt.disaster_encode.service.EncodingService;
import com.bupt.disaster_encode.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/info")
public class DisasterManagementController {

    @Autowired
    private EncodingService encodingService;

    /**
     * 查
     * @param vo
     * @return
     */
    @CrossOrigin
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
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping()
    @ResponseBody
    public ResponseEntity<Object> insertDisasterManagement(HttpServletRequest request) {

        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        // 获取文件
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
        try {
            if (files.get(0).getOriginalFilename().contains(".xlsx")) { // 如果是Excel文件
                List<String[]> content = FileUtil.importExcelMultipartFile(files.get(0));
                // 列名
                String[] columnName = content.remove(0);
                List<DisasterManagement> disasterManagementsToInsert = new ArrayList<>();
                for (String[] c : content) {
                    DisasterManagement dm = encodingService.decoding(c[0]);
                    encodingService.setDescThruWord(dm, c[1]);
                    disasterManagementsToInsert.add(dm);
                }
                encodingService.insertDisasterManagement(disasterManagementsToInsert);

            } else { // 是其他音频视频文件
                File file = FileUtil.fileTransform(files.get(0));
                // 解码
                DisasterManagement disasterManagement = encodingService.decoding(file.getName());
                // 根据carrier类型setDesc
                encodingService.setDescThruFile(disasterManagement, file);
                // 插入数据库
                List<DisasterManagement> list = new LinkedList<>();
                list.add(disasterManagement);
                encodingService.insertDisasterManagement(list);
                // 删除生成的临时文件
                FileUtil.deleteTempFile(file);
            }
            responseEntity.setReturnCode(ExceptionEnum.SUCCESS.getReturnCode());
            responseEntity.setReturnMsg(ExceptionEnum.SUCCESS.getReturnMsg());
        } catch (Exception e) {
            responseEntity.setReturnCode(ExceptionEnum.SystemError.getReturnCode());
            responseEntity.setReturnMsg(ExceptionEnum.SystemError.getReturnMsg() + ": " + e.getMessage());
        }
        return responseEntity;
    }

}
