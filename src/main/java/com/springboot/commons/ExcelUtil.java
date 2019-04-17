package com.springboot.commons;

import com.springboot.dto.DrugProfileDto;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private static final Logger LOGGER = Logger.getLogger(ExcelUtil.class);

    public static List<DrugProfileDto> importExcel(File file) {
        LOGGER.info("导入解析开始");
        try {
            List<DrugProfileDto> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC.getCode()) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellType() == CellType.STRING.getCode()) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType() == CellType.BOOLEAN.getCode()) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType() == CellType.ERROR.getCode()) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                DrugProfileDto drugProfileDto = new DrugProfileDto();
                drugProfileDto.setId((String) objects[0]);
                drugProfileDto.setName((String) objects[1]);
                drugProfileDto.setStatus((String) objects[2]);
                drugProfileDto.setType((String) objects[3]);
                drugProfileDto.setDefaultQuantity((int) objects[4]);
                drugProfileDto.setAmount((int) objects[5]);
                drugProfileDto.setUnit((String) objects[6]);
                drugProfileDto.setDescription((String) objects[7]);
                list.add(drugProfileDto);
            }
            LOGGER.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            LOGGER.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }
}
