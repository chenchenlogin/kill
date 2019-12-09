package com.xinghuofirst.kill.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhou_gc
 * @Date 2019/12/3
 * @Description  登陆重构 使用token实现单点登陆
*/
@Controller
public class LoginController {

  /*  private  final String  QTERROR="前台数据有误";
    private  final String CANNOTLOGINAGAIN="您已经登陆不可重复登陆";
    private final String   OK="success";
    private final String NOUSER="当前用户不存在";
    private  final  Long TOKEN_TIME=900000L;//token 过期时间为15分钟
    @Autowired
    private TbUserService tbUserService;

    *//**
     *@描述 用户登陆
     *@参数  [tbUser]
     *@返回值   map  封装响应状态信息 若是成功则会返回token 以及用户本体
     *@创建人  zhou_gc@suixingpay.com
     *@创建时间  2019/12/3
     *@修改人和其它信息
     *//*
   *//* @RequestMapping("/userlogin")
    @ResponseBody
    public Map<String,Object> loginCheckController(@RequestBody TbUser tbUser,HttpServletRequest request) throws JSONException {
        Map<String,Object> map=new HashMap();
        if(     tbUser==null
                ||(tbUser.getUserName()==null||"".equals(tbUser.getUserName()))
                ||(tbUser.getPassword()==null||"".equals(tbUser.getPassword()))
          ){
              map.put("message",QTERROR);
              return map;
        }

        if(request.getHeader("token") != null){
            map.put("message",CANNOTLOGINAGAIN);
            return map;
        }
        TbUser tempUser = tbUserService.userLogin(tbUser);
        if(tempUser == null){
            map.put("message",NOUSER);
            return map;
        }
        if( "1".equals(tempUser.getLoginStatus())){
            map.put("message",CANNOTLOGINAGAIN);
            return map;
        }
        tempUser.setLoginStatus("1");
        tbUserService.updateLoginStatus(tempUser);
        Map<String,String> claims=new HashMap<>();
        claims.put("username",tempUser.getUserName());
        claims.put("userid",tempUser.getUserId().toString());
        String token=TokenUtil.getToken(claims,50000000L);//50000s
        //String token=TokenUtil.getToken(claims,10000L);//10s
        map.put("token",token);
        map.put("tbUser",tempUser);
        map.put("message",OK);
        return map;
    }*//*
    @RequestMapping("/userlogin")
    @ResponseBody
    public Map<String,Object> loginCheckController(@RequestBody TbUser tbUser,
            HttpServletRequest request) throws JSONException {
        Map<String,Object> map = new HashMap();
        if(     tbUser==null
                ||(tbUser.getUserName()==null||"".equals(tbUser.getUserName()))
                ||(tbUser.getPassword()==null||"".equals(tbUser.getPassword()))
        ){
            map.put("message",QTERROR);
            return map;
        }
        String token_header=request.getHeader("token");
        if( token_header == "undefined" && "undefined".equals(token_header)){
            map.put("message",CANNOTLOGINAGAIN);
            return map;
        }
        *//*在redis数据库中判断当前用户是否已经登陆*//*
        if(RedisUtil.hasKey(tbUser.getUserName())){
            map.put("message",CANNOTLOGINAGAIN);
            return  map;
        }
        TbUser tempUser = tbUserService.userLogin(tbUser);
        if(tempUser == null){
            map.put("message",NOUSER);
            return map;
        }
        Map<String,String> claims=new HashMap<>();
        claims.put("username",tempUser.getUserName());
        claims.put("userid",tempUser.getUserId().toString());
        String token=TokenUtil.getToken(claims,TOKEN_TIME);
        *//*向redis中添加用户唯一性标志位  设置过期时间和token过期时间保持一致 即使不一致也没问题，不过在间隙时间内用户无法登陆*//*
        RedisUtil.set(tempUser.getUserName(),token,TOKEN_TIME);
        map.put("token",token);
        map.put("message",OK);

        return map;
    }

    *//**
     *@描述 用户注销登陆
     *@参数
     *@返回值
     *@创建人  zhou_gc@suixingpay.com
     *@创建时间  2019/12/4
     *@修改人和其它信息
     *//*
    @RequestMapping("/userloginout")
    @ResponseBody
    public String userLoginOutController(HttpServletRequest request){
              String token = request.getHeader("token");
        Map<String ,String> maps= (Map<String, String>) request.getAttribute("request_parameters");
            String username= maps.get("username");
            RedisUtil.del(username);
            return  "successLoginOut";
    }*/

}
