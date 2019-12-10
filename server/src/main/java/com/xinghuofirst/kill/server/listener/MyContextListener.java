package com.xinghuofirst.kill.server.listener;
import com.xinghuofirst.kill.server.utils.SshUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 * @author zhou_gc@suixingpay.com
 * @description
 * @date 2019/12/9 17:21
 */
@WebListener
public class MyContextListener implements ServletContextListener {

    private SshUtil conexionssh;


    public MyContextListener() {
        super();
    }


    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Context initialized ... !");
        try {
            conexionssh = new SshUtil();
        } catch (Throwable e) {
            e.printStackTrace(); // error connecting SSH server
        }
    }


    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Context destroyed ... !");
        conexionssh.closeSSH(); // disconnect
    }

}
