package com.weijuju.operation;

import com.weijuju.operation.jenkins.JenkinsBuildJob;
import com.weijuju.operation.nginx.NginxConfiguration;
import com.weijuju.operation.shell.LinuxShell;
import com.weijuju.operation.tomcat.TomcatCopyDirectory;

/**
 * Created by zhangyin on 2016/8/24.
 */
public class Main {


    public static void main(String[] args) {

        if(args.length!=6){
            System.out.println("需要六个参数  域名  项目名  端口号 svn地址 groupid  artifactId");
            System.exit(1);
        }
//       args=new String[]{"sf-schoolseason.weijuju.com","schoolseason","10658"};

        String domainName=args[0];
        String projectName=args[1];
        try {
            Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("端口号必须为整数");
            System.exit(1);
        }
        int port=Integer.parseInt(args[2]);
        String svnUrl=args[3];
        String groupId=args[4];
        String artifactId=args[5];



      //  String  prefixDomain=domainName.substring(0,domainName.indexOf("."));
        //System.out.println(prefixDomain);

//        try {
//            new Domain(prefixDomain);
//        } catch (Exception e) {
//            System.err.println("域名创建失败");
//            e.printStackTrace();
//        }

        try {
            NginxConfiguration ng=new NginxConfiguration(domainName,projectName,port);
            ng.generateFile();
            System.out.println("生成域名对应Nginx配置文件成功");
        } catch (Exception e) {
            System.out.println("生成域名对应nginx配置文件失败");
            System.out.println(e);
            e.printStackTrace();
        }

        try {
            TomcatCopyDirectory tomcat=new TomcatCopyDirectory(domainName,projectName,port);
            System.out.println("复制新的tomcat 成功");
        } catch (Exception e) {
            System.out.println("复制tomcat配置文件失败");
            System.out.println(e);
            e.printStackTrace();
        }
        LinuxShell linuxShell=null;

        try {
            linuxShell=new LinuxShell(domainName,projectName,port);
        } catch (Exception e) {
            System.out.println("新建liuxShell对象失败");
            e.printStackTrace();
        }

        try {
            linuxShell.setTomcatAccess();
            System.out.println("设置tomcat的执行权限 成功");
        } catch (Exception e) {
            System.out.println("设置tomcat目录的权限 失败");
            System.out.println(e);
            e.printStackTrace();
        }
        try {
            linuxShell.startTomcat();
            System.out.println("启动tomcat成功");
        } catch (Exception e) {
            System.out.println("启动tomcat失败");
            System.out.println(e);
            e.printStackTrace();
        }

        try {
            linuxShell.restartNginx();
            System.out.println("重新加载 Nginx 成功");
        } catch (Exception e) {
            System.err.println("重启加载Nginx 失败");
            System.out.println(e);
            e.printStackTrace();
        }


        try {
            JenkinsBuildJob j=new JenkinsBuildJob(domainName,projectName,port,svnUrl,groupId,artifactId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("success");

    }
}
