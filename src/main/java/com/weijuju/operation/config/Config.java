package com.weijuju.operation.config;

/**
 * Created by zhangyin on 2016/8/24.
 */
public class Config {

    /**
     * Nginx 配置文件放置的目录
     */
    private static   String nginxConfDirectory="/etc/nginx/sites-enabled";

    /***
     *  项目放置的基准目录
     */
    private static  String  projectBaseDirectory="/mnt/website";

    /**
     * Tomcat 模板放置的目录路径
     */
    private static  String  tomcatTempletDirectory="/mnt/operation/tomcatTemplet";


    public static  String getNginxConfDirectory() {
        return nginxConfDirectory;
    }

    public  static void setNginxConfDirectory(String nginxConfDirector) {
        nginxConfDirectory = nginxConfDirector;
    }


    public static String getProjectBaseDirectory() {
        return projectBaseDirectory;
    }

    public static String getTomcatTempletDirectory() {
        return tomcatTempletDirectory;
    }
}
