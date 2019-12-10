package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.model.mapper.BusinessRepository;
import com.xinghuofirst.kill.server.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.xinghuofirst.kill.server.utils.ExcelImportUtil;
import javax.annotation.Resource;
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
        return businessRepository.insertWhiteBusiness(business);
    }


    /** 读文件，判断是白名单还是沉默用户
   * duanlian
   * **/
    @Override
    public String readExcelFile(MultipartFile file,Integer silentStatus) {
        String result = "";
        ExcelImportUtil excel = new ExcelImportUtil();
        List<Business> ilist = excel.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            /** 为0则为沉默用户，执行插入沉默用户**/
            if(silentStatus==0){
                Business b = new Business();
                b.setSilentStatus(0);
                ilist.add(b);
            for (Business business : ilist) {
                businessRepository.insertWhiteBusiness(business);
                }
            }else{
                Business b = new Business();
                b.setSilentStatus(1);
                ilist.add(b);
                for (Business business : ilist) {
                    businessRepository.insertWhiteBusiness(business);
                }
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }

}
