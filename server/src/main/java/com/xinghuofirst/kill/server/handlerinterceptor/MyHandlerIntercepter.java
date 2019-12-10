package com.xinghuofirst.kill.server.handlerinterceptor;


import com.xinghuofirst.kill.server.utils.RedisUtil;
import com.xinghuofirst.kill.server.utils.TokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author zhou_gc
 * @Date 2019/12/3
 * @Description  拦截器设置 实现token拦截访问
 */
@Component
@Configuration
public class MyHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       String token = request.getHeader("token");
       if(token == null || token == "undefined") return false;
       try {
           Map<String,String> map = TokenUtil.verifyToken(token);
           /*判断当前redis中的此用户是否处于登陆状态 否则 则强制重新登陆*/
           if(!RedisUtil.hasKey(map.get("userid")))  return false;
           /*token验证成功后将token中的信息封装到request域中 不允许操作*/
          // request.setAttribute("request_parameters",map);
       }catch (Exception e){
           System.out.println(e);
            returnJson(response,"no_token_use");//用户需要重新登陆
           return false;
       }

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,  ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler,  Exception ex) throws Exception {

    }


    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
