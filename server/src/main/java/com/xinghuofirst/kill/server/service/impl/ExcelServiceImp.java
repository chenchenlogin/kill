package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.model.mapper.BusinessRepository;
import com.xinghuofirst.kill.server.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.xinghuofirst.kill.server.utils.ExcelImportUtil;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImp implements ExcelService {

    @Autowired
    @Resource
    private BusinessRepository businessRepository;
    /** 文件导入 **/

    /** 文件导入 **/
    @Override
    public int insertWhiteBusiness(Business business) {
        return businessRepository.insertSelective(business);
    }


    /** 读文件，判断是白名单还是沉默用户
   * duanlian
   * **/
    @Override
    public String readExcelFile(MultipartFile file,Integer silentStatus) {
        String result = "";
        ExcelImportUtil excel = new ExcelImportUtil();
        List<Business> ilist = excel.getExcelInfo(file);
        //List<Business> list = new ArrayList<>();
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            /** 为0则为沉默用户，执行插入沉默用户**/
            if(silentStatus==0){

            for (Business business : ilist) {
                business.setSilentStatus(0);
                System.out.println("0的时候，注意看***"+business);
                businessRepository.insertSelective(business);
                }
            }else{
                for (Business business : ilist) {
                    business.setSilentStatus(1);
                    System.out.println("1的时候，注意看***"+business);
                    businessRepository.insertSelective(business);
                }
            }
            result = "成功";
        } else {
            result = "失败";
        }
        return result;
    }

}
