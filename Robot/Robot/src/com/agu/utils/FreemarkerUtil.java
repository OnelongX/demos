package com.agu.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by agu on 16/11/2.
 */
public class FreemarkerUtil {


    public Template getTemplate(String name) {
        try {
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration();

            String path = System.getProperty("user.dir")+"/template/";

            // 设定去哪里读取相应的ftl模板文件
            cfg.setDirectoryForTemplateLoading(new File(path));
            // 在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 控制台输出
     *
     * @param name
     * @param root
     */
    public void print(String name, Map<String, Object> root) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = this.getTemplate(name);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出HTML文件
     *
     * @param name
     * @param root
     * @param outFile
     */
    public void fprint(String name, Map<String, Object> root, String outFile) {
        FileWriter out = null;
        try {
            String path = System.getProperty("user.dir")+"/source/";
            if(!new File(path).exists())
            {
                new File(path).mkdirs();
            }
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File(path + outFile));
            Template temp = this.getTemplate(name);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
