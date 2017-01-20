package com.weijuju.operation.shell;

import com.weijuju.operation.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhangyin on 2016/8/25.
 */
public class LinuxShell {

    private String domainName;

    private String projectName;

    private int port;


    public LinuxShell(String domainName, String projectName, int port) {
        this.domainName = domainName;
        this.projectName = projectName;
        this.port = port;
    }
    public  void  setTomcatAccess(){
        try {
            String   targetFilepath = Config.getProjectBaseDirectory() + File.separator +projectName + File.separator+ "server_" + port;
            Process process = Runtime.getRuntime().exec("chmod 777 "+targetFilepath);
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void  startTomcat(){
        try {
            String tomcatpath= Config.getProjectBaseDirectory() + File.separator
                    +projectName + File.separator+ "server_" + port+File.separator+"bin"+File.separator+"startup.sh";
            Process process = Runtime.getRuntime().exec(tomcatpath);
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void restartNginx(){
        try {
            Process process = Runtime.getRuntime().exec("nginx -s reload");
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
