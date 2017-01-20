package com.weijuju.operation.jenkins;/**
 * Created by zhangyin on 2016/10/13.
 */


import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 新建 jenkins 任务
 * @author zhangyin
 * @create 2016-10-13
 */
public class JenkinsBuildJob {

    private static  String  jenkinsUrl="http://admin:admin@iag-jenkins.weijuju.com/jenkins/createItem?name=";

    private String domainName;

    private String projectName;

    private int port;

    private String svnUrl;

    private String svnUrlend;

    private String groupId;

    private String artifactId;


    public JenkinsBuildJob(String domainName, String projectName, int port, String svnUrl, String groupId, String artifactId) {
        this.domainName = domainName;
        this.projectName = projectName;
        this.port = port;
        this.svnUrl = svnUrl;
        this.groupId = groupId;
        this.artifactId = artifactId;
        int i = svnUrl.lastIndexOf("/");
        svnUrlend="."+svnUrl.substring(i+1);
        String bodyContent=generateJenkinJobxml();
        JenkinsHttp.post(jenkinsUrl+projectName,bodyContent);

    }

    private String generateJenkinJobxml(){
        Configuration configuration = new Configuration();
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setTemplateLoader(new ClassTemplateLoader(JenkinsBuildJob.class, "/"));
        try {
            Template template = configuration.getTemplate("config.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> context = new HashMap<String, Object>();
            context.put("domainName", domainName);
            context.put("projectName", projectName);
            context.put("port",port);
            context.put("svnUrl",svnUrl);
            context.put("svnUrlend",svnUrlend);
            context.put("groupId",groupId);
            context.put("artifactId",artifactId);
            template.process(context, writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
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



    public String getSvnUrlend() {
        return svnUrlend;
    }

    public void setSvnUrlend(String svnUrlend) {
        this.svnUrlend = svnUrlend;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }
}
