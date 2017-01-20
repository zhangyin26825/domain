package com.weijuju.operation.tomcat;

import com.weijuju.operation.config.Config;
import com.weijuju.operation.nginx.NginxConfiguration;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyin on 2016/8/24.
 */
public class TomcatCopyDirectory {


    private String domainName;

    private String projectName;

    private int port;

    String targetFilepath;

    public TomcatCopyDirectory(String domainName, String projectName, int port) throws IOException{
        this.domainName = domainName;
        this.projectName = projectName;
        this.port = port;
        copyTomcat();
    }

    public void copyTomcat() throws IOException {
        targetFilepath = Config.getProjectBaseDirectory() + File.separator +projectName + File.separator+ "server_" + port;
        String tomcatTempletDirectory = Config.getTomcatTempletDirectory();
        FileUtils.copyDirectory(new File(tomcatTempletDirectory),new File(targetFilepath));
        //copyFile(new File(tomcatTempletDirectory),new File(targetFilepath));
        generateFile();
        setFileAccess(new File(targetFilepath));
    }

    public  void setFileAccess(File f){

        f.setExecutable(true);
        f.setReadable(true);
        f.setWritable(true);
        if(f.isFile()){
            return ;
        }else  if(f.isDirectory()){
            File[] files = f.listFiles();
            for (int i=0;i<files.length;i++){
                setFileAccess(files[i]);
            }
        }
    }

    private String generateTomcatConfServerXML(){
        Configuration configuration = new Configuration();
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setTemplateLoader(new ClassTemplateLoader(NginxConfiguration.class, "/"));
        try {
            Template template = configuration.getTemplate("tomcatServerXml.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> context = new HashMap<String, Object>();
            context.put("port",port);
            context.put("shutdownport",port+1);
            template.process(context, writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateFile(){
        FileWriter fw=null;
        try {
            String filepath=targetFilepath+ File.separator+"conf"+ File.separator+"server.xml";
            File f=new File(filepath);
            f.createNewFile();
            fw=new FileWriter(f);
            fw.write(generateTomcatConfServerXML());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


        // 复制文件
        public static void copyFile(File sourceFile,File targetFile)
                throws IOException{
            // 新建文件输入流并对它进行缓冲
            FileInputStream input = new FileInputStream(sourceFile);
            BufferedInputStream inBuff=new BufferedInputStream(input);

            // 新建文件输出流并对它进行缓冲
            FileOutputStream output = new FileOutputStream(targetFile);
            BufferedOutputStream outBuff=new BufferedOutputStream(output);

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len =inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();

            //关闭流
            inBuff.close();
            outBuff.close();
            output.close();
            input.close();
        }
        // 复制文件夹
        public static void copyDirectiory(String sourceDir, String targetDir)
                throws IOException {
            // 新建目标目录
            (new File(targetDir)).mkdirs();
            // 获取源文件夹当前下的文件或目录
            File[] file = (new File(sourceDir)).listFiles();
            for (int i = 0; i < file.length; i++) {
                if (file[i].isFile()) {
                    // 源文件
                    File sourceFile=file[i];
                    // 目标文件
                    File targetFile=new
                            File(new File(targetDir).getAbsolutePath()
                            + File.separator+file[i].getName());
                    copyFile(sourceFile,targetFile);
                }
                if (file[i].isDirectory()) {
                    // 准备复制的源文件夹
                    String dir1=sourceDir + "/" + file[i].getName();
                    // 准备复制的目标文件夹
                    String dir2=targetDir + "/"+ file[i].getName();
                    copyDirectiory(dir1, dir2);
                }
            }
        }
    }

