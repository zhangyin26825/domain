package com.weijuju.operation.jenkins;/**
 * Created by zhangyin on 2016/10/13.
 */



import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhangyin
 * @create 2016-10-13
 */
public class JenkinsHttp {

    public static  void post(String urladdress,String bodyContent){
        try {
            StringBuffer buffer = new StringBuffer();
            URL url = new URL(urladdress);
            URLConnection rulConnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
            httpUrlConnection.setConnectTimeout(1000 * 3000);
            httpUrlConnection.setReadTimeout(1000 * 3000);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setRequestProperty("Content-type", "application/xml;");
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
            String userCredentials = "admin:admin";
            String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
            httpUrlConnection.setRequestProperty ("Authorization", basicAuth);
            DataOutputStream outStream = new DataOutputStream(httpUrlConnection.getOutputStream());
            outStream.write(bodyContent.toString().getBytes());
            outStream.flush();
            outStream.close();
            int responseCode = httpUrlConnection.getResponseCode();
            if(responseCode==200){
                System.out.println("新建jenkins 任务成功");
            }else{
                System.out.println("新建jenkins 任务失败");
                InputStream inputStream =  httpUrlConnection.getErrorStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                inputStream = null;
                System.out.println("失败原因     "+buffer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {

        String domainName="sf-thumb.weijuju.com";
        String projectName="testRemoteURl";
        int port=6990;
        String svnUrl="svn://10.170.27.248/recreation/iag/web/sf/weixinjia-recreation-sf-thumb";
        String groupId="com.weixinjia.recreation.thumb";
        String artifactId="thumb";
        JenkinsBuildJob jenkinsBuildJob=new JenkinsBuildJob(domainName,projectName,port,svnUrl,groupId,artifactId);

    }
}
