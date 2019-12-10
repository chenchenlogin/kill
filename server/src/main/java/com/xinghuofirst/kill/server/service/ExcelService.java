package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.model.entity.Business;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {


    /** 文件导入 数据库 duanlian**/
    int insertWhiteBusiness(Business business);
    /** 读取excel文件 duanlian**/
    String readExcelFile(MultipartFile file,Integer silentStatus);
}
