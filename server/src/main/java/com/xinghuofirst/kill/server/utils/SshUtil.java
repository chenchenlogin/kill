package com.xinghuofirst.kill.server.utils;

/**
 * @author zhou_gc@suixingpay.com
 * @description   废弃
  @date 2019/12/9 17:00
 */

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class SshUtil {


    //private final static String S_PATH_FILE_PRIVATE_KEY = "/Users/hdwang/.ssh/id_rsa";
    //private final static String S_PATH_FILE_KNOWN_HOSTS = "/Users/hdwang/.ssh/known_hosts";
   // private final static String S_PASS_PHRASE = "";
    private final static int LOCAl_PORT = 6379;
    private final static int REMOTE_PORT = 6379;
    private final static int SSH_REMOTE_PORT = 22;
    private final static String SSH_USER = "root";
    private final static String SSH_PASSWORD = "txtAPOTOX4869";
   // private final static String SSH_PASSWORD = "txtAPOTOX4869";
    private final static String SSH_REMOTE_SERVER = "60.205.230.70";
    private final static String REDIS_REMOTE_SERVER = "60.205.230.70";

    private Session sesion; //represents each ssh session

    public void closeSSH ()
    {
        System.out.println("ssh连接关闭");
        sesion.disconnect();
    }

    public SshUtil () throws Throwable
    {

        JSch jsch = null;

        jsch = new JSch();
        //jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        //jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);

        sesion.setPassword(SSH_PASSWORD);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);

        sesion.connect(); //ssh connection established!
        System.out.println("ssh连接建立成功");
        //by security policy, you must connect through a fowarded port
        sesion.setPortForwardingL(LOCAl_PORT, REDIS_REMOTE_SERVER, REMOTE_PORT);
        System.out.println("转发请求成功");

    }



}
