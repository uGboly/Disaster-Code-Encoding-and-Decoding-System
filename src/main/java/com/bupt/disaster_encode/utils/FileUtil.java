package com.bupt.disaster_encode.utils;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtil {

    private static DecimalFormat df = new DecimalFormat("0");
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public static File fileTransform(MultipartFile multipartFile) {
        //文件上传前的名称
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        OutputStream out = null;
        try{
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for(int i = 0; i < ss.length; i++){
                out.write(ss[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static void deleteTempFile(File file) {
        File f = new File(file.toURI());
        if (f.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    /**
     * excel导入
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static List<String[]> importExcelMultipartFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IOException("Excel文件为空或不存在");
        }
        String name = "Excel" + System.nanoTime();
        // File file = File.createTempFile(name, null);
        File file = fileTransform(multipartFile);
        if (!file.exists()) {
            throw new IOException("Excel文件" + file.getName() + "为空或不存在");
        }
        List<String[]> excelData = new LinkedList<>();
        FileInputStream inputStream = new FileInputStream(file);
        // FileInputStream inputStream = new FileInputStream("/Users/bytedance/Downloads/test.xlsx");
        // 工厂模式
        Workbook wb = WorkbookFactory.create(inputStream);

        // 读取sheet0中的数据
        Sheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        Pattern p = Pattern.compile("\\s*|\t|\r|\rn");

        // 读取首行列数
        int columnNum = getColumnNum(sheet);
        // 循环读取各行
        for (int i = 0; i <= lastRowNum; i++) {
            boolean isEmptyRow = true;
            Row row = sheet.getRow(i);
            if (row != null) {
                // 判断是否为空行
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String value = p.matcher(getCellValue(row.getCell(j))).replaceAll(""); // 将制表符等去掉
                    if (!value.trim().equals("")) {
                        isEmptyRow = false;
                        break;
                    }
                }
                // 校验规则：如果是空行，没有数据，仅有空格符、制表符等，则过滤掉
                if (!isEmptyRow) {
                    // 该行数据存在非空单元格，继续处理
                    List<String> rowList = new ArrayList<>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        String value = getCellValue(cell);
                        rowList.add(value);
                    }
                    // 处理末单元格为空的情况
                    int rowDiff = columnNum - row.getLastCellNum();
                    if (rowDiff > 0) {
                        for (int j = 0; j < rowDiff; j++) {
                            rowList.add("");
                        }
                    }
                    String[] rowArray = rowList.toArray(new String[rowList.size()]);
                    excelData.add(rowArray);
                }
            }
        }
        return excelData;
    }

    /**
     * 获得表的列
     * @param sheet
     * @return
     */
    private static int getColumnNum(Sheet sheet) {
        if (sheet.getLastRowNum() == 0) {
            return 0;
        }
        Row row = sheet.getRow(0);
        return row.getLastCellNum();
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    result = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    result = cell.getNumericCellValue();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        result = fmt.format(result);
                    } else {
                        result = df.format(result);
                    }
                    break;
                case BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case FORMULA:
                    // 公式单元格则返回器数值
                    result = cell.getNumericCellValue();
                    break;
                case ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

}
