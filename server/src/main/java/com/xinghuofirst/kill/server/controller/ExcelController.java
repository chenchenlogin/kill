package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
public class ExcelController {
    @Autowired
    private ExcelService excelService;
    /**段炼
     * 文件导入，白名单、沉默用户
     * **/
    @RequestMapping(value="/uploadExceltWhite",method = RequestMethod.POST)
    public BaseResponse uploadExcelN(@RequestParam(value="file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, Business business){
        String result = excelService.readExcelFile(file,business.getSilentStatus());


        return new BaseResponse(200,result);
    }
}


