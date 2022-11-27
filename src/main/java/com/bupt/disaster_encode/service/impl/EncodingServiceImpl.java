package com.bupt.disaster_encode.service.impl;

import com.bupt.disaster_encode.domain.DisasterManagement;
import com.bupt.disaster_encode.domain.Enum.Carrier;
import com.bupt.disaster_encode.domain.Enum.Category;
import com.bupt.disaster_encode.domain.Enum.Label;
import com.bupt.disaster_encode.domain.Enum.Origin;
import com.bupt.disaster_encode.domain.Region;
import com.bupt.disaster_encode.dto.DisasterManagementPageVo;
import com.bupt.disaster_encode.mapper.DisasterManagementMapper;
import com.bupt.disaster_encode.mapper.RegionMapper;
import com.bupt.disaster_encode.service.EncodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EncodingServiceImpl implements EncodingService {

    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private DisasterManagementMapper disasterManagementMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public Region getRegion(String regionId) {
        return regionMapper.selectByPrimaryKey(regionId);
    }

    /**
     * 解码过程
     * @param disasterCode
     * @return
     * @throws Exception
     */
    @Override
    public DisasterManagement decoding(String disasterCode) throws Exception {
        String regionCode = disasterCode.substring(0, 12);
        String timeCode = disasterCode.substring(12, 26);
        String originCode = disasterCode.substring(26, 29);
        String carrierCode = disasterCode.substring(29, 30);
        String infoCode = disasterCode.substring(30, 36);

        Region region = getRegion(regionCode);
        Date disasterDate = sdf.parse(timeCode);
        Origin origin = getOrigin(originCode);
        Carrier carrier = getCarrier(carrierCode);
        Category category = getCategory(infoCode.substring(0, 3));
        Label label = getLabel(infoCode.substring(0, 1), infoCode.substring(3, 6));

        return new DisasterManagement(disasterCode, region, disasterDate, origin, carrier, category, label);

    }

    /**
     * 通过视频、音频等文件生成desc过程
     * @param disasterManagement
     * @param file
     */
    @Override
    public void setDescThruFile(DisasterManagement disasterManagement, File file) {
        if (file.isFile()) {
            FileInputStream inputStream = null;
            FileOutputStream outputStream = null;
            String filePath = "src/main/resources/files";
            try {
                inputStream = new FileInputStream(file);
                outputStream = new FileOutputStream(filePath);
                // 一边读一边写
                byte[] bytes = new byte[1024 * 1024];
                int readCount;
                while ((readCount = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, readCount);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            File outputFile = new File(filePath);
            disasterManagement.setDesc(outputFile.getAbsolutePath() + file.getName());
        }
    }

    /**
     * 通过Excel中的文本生成desc
     * @param disasterManagement
     * @param desc
     */
    @Override
    public void setDescThruWord(DisasterManagement disasterManagement, String desc) {
        disasterManagement.setDesc(desc);
    }

    /**
     * 插入灾情数据List
     * @param disasterManagements
     * @return
     */
    @Override
    public boolean insertDisasterManagement(List<DisasterManagement> disasterManagements) {
        return disasterManagementMapper.insertList(disasterManagements) == disasterManagements.size();
    }

    /**
     * 批量查询灾情数据
     * @param vo
     * @return
     */
    @Override
    public List<DisasterManagement> getDisasterManagement(DisasterManagementPageVo vo) throws Exception {
        DisasterManagement disasterManagement = new DisasterManagement(vo);
        return disasterManagementMapper.selectSelectiveByPage(disasterManagement, vo.getOffset(), vo.getLimit());
    }

    @Override
    public Integer getDisasterManagementCount(DisasterManagementPageVo vo) throws ParseException {
        DisasterManagement disasterManagement = new DisasterManagement(vo);
        return disasterManagementMapper.selectSelectiveByPageCount(disasterManagement);
    }

    private Label getLabel(String bigCategory, String infoCode) {
        Label[] labels = Label.values();
        for (Label label : labels) {
            if (String.valueOf(label.bigCategory).equals(bigCategory) && label.CODE.equals(infoCode)) {
                return label;
            }
        }
        return null;
    }

    private Category getCategory(String categoryCode) {
        Category[] categories = Category.values();
        for (Category category : categories) {
            String bigCategoryCode = String.valueOf(category.bigCategory);
            String code = bigCategoryCode + category.subClassCode;
            if (code.equals(categoryCode)) {
                return category;
            }
        }
        return null;
    }

    public Origin getOrigin(String originCode){
        Origin[] origins = Origin.values();
        for (Origin origin : origins) {
            if (origin.CODE.equals(originCode)) {
                return origin;
            }
        }
        return null;
    }

    public Carrier getCarrier(String carrierCode) {
        Carrier[] carriers = Carrier.values();
        for (Carrier carrier : carriers) {
            if (carrier.CODE == Integer.parseInt(carrierCode)) {
                return carrier;
            }
        }
        return null;
    }


}
