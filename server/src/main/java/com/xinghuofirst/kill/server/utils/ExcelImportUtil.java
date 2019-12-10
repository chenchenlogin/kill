package com.xinghuofirst.kill.server.utils;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.model.entity.ReadExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * duanlian
 * excel工具类
 */
public class ExcelImportUtil {
    /**
     * 先创建一个实体类
     */
    ReadExcel readExcel = new ReadExcel();
    /**
     * 读取Excel文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<Business> getExcelInfo(MultipartFile mFile) {
        //获取文件名
        String fileName = mFile.getOriginalFilename();
        List<Business> ilist = null;
        try {
            //验证文件名是否合格
            if(!validateExcel(fileName)){
                //不合格的话直接return
                return null;
            }
            //根据文件名判断是2003版本的还是2007版本的
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            ilist= createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ilist;
    }
    /**
     * 判断是不是2003版本的excel
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    /**
     * 判断是不是2007版本的excel
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    /**
     * 判断是不是excel文件格式
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath){
        if(filePath ==null||!(isExcel2003(filePath) || isExcel2007(filePath))){
            readExcel.setErrorMsg("文件名不是excel格式");
            return false;
        }
        return true;
    }
    /**
     * 读取excel里面的信息
     */
    public List<Business> readExcelValue(Workbook wb){
        List<Business> ilist=new ArrayList<>();
        //先得到一个sheet
        Sheet sheet = wb.getSheetAt(0);
        //得到excel里面的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        readExcel.setTotalRows(totalRows);
        //得到excel里面的列，前提是有行
        //大于1是因为我从第二行就是数据了，这个大家看情况而定
        if(totalRows >1 && sheet.getRow(0)!=null){
            int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            readExcel.setTotalCells(totalCells);
        }
        for (int r = 1 ; r < totalRows; r++){
            Row row = sheet.getRow(r);
            if(row == null){
                continue;//如果行为空的话直接中断
            }
            Business business = new Business();
            //循环xcel的列
            for(int c = 0; c<readExcel.getTotalCells() ; c++){
                Cell cell = row.getCell(c);
                if(cell != null){
                    //根据excel需要导入的列数来写

                 if (c == 0){
                        /** 商户编号 businessNumber **/
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String businessNumber = String.valueOf(cell.getNumericCellValue());
                            business.setBusinessNumber(businessNumber.substring(0,businessNumber.length()-2>0?businessNumber.length()-2:1));
                        }else{
                            business.setBusinessNumber(cell.getStringCellValue());
                        }
                    }else if( c == 1){
                        /**手机号  phone **/
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String phone = String.valueOf(cell.getNumericCellValue());
                            business.setPhone(phone.substring(0, phone.length()-2>0?phone.length()-2:1));
                        }else{
                            business.setPhone(cell.getStringCellValue());
                        }
                    }else if( c == 2){
                        /** 商户姓名 name **/
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String name = String.valueOf(cell.getNumericCellValue());
                            business.setName(name.substring(0, name.length()-2>0?name.length()-2:1));
                        }else{
                            business.setName(cell.getStringCellValue());
                        }
                    }else if(c==3){
                        /** 身份证号 idcard **/
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String idcard = String.valueOf(cell.getNumericCellValue());
                            business.setIdcard(idcard.substring(0, idcard.length()-2>0?idcard.length()-2:1));
                        }else{
                            business.setIdcard(cell.getStringCellValue());
                        }
                    }else if( c == 4){
                        /** 省份 province **/
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String province = String.valueOf(cell.getNumericCellValue());
                            business.setProvince(province.substring(0, province.length()-2>0?province.length()-2:1));
                        }else{
                            business.setProvince(cell.getStringCellValue());
                        }
                    }
                }
            }

            //最后将这些全部添加到ilist中
            ilist.add(business);

        }
        return ilist;
    }
    public List<Business> createExcel(InputStream is , boolean isExcel2003){
        List<Business> ilist = null;
        try {
            Workbook wb = null;
            if(isExcel2003){
                //如果是2003版本的就new一个2003的wb出来
                wb = new HSSFWorkbook(is);
            }else{
                //否则就new 一个2007版的出来
                wb = new XSSFWorkbook(is);

            }
            //再让wb去解析readExcelValue(Workbook wb)方法
            ilist = readExcelValue(wb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ilist;
    }

}