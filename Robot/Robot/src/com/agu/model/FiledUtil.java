package com.agu.model;

import com.agu.utils.JsonUtils;
import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agu on 16/11/2.
 */
public class FiledUtil {


    public static String toTableName(@NotNull String name)
    {
        String[] temp = name.split("_");
        StringBuilder sb = new StringBuilder();
        //String t="";
        int n = 0;
        for(String str:temp)
        {
            n = str.length();
            if (n > 1)
            {
                sb.append(str.substring(0, 1).toUpperCase());
                sb.append(str.substring(1).toLowerCase());
            }
            else
            {
                sb.append(str.toUpperCase());
            }

        }

        return sb.toString();
    }

    public static String toMethodName(@NotNull String name)
    {
        String[] temp = name.split("_");
        StringBuilder sb = new StringBuilder();
       // String t = "";
        int n = 0;
        for (String str:temp)
        {
            n = str.length();
            if (n > 1)
            {
                sb.append(str.substring(0, 1).toUpperCase());
                sb.append(str.substring(1).toLowerCase());
                //t = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            }
            else
            {
                sb.append(str.toUpperCase());
                //t = str.toUpperCase();
            }
            //sb.append(t);
        }

        return sb.toString();
    }

    public static String toMethodName2(String name)
    {
        StringBuilder sb = new StringBuilder();
        int n = name.length();
        //String t = "";
        if (n > 1)
        {
            sb.append(name.substring(0, 1).toUpperCase());
            sb.append(name.substring(1).toLowerCase());
            //t = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        else
        {
            sb.append(name.toUpperCase());
           // t = name.toUpperCase();
        }
        //sb.append(t);
        return sb.toString();
    }


    public static String toFieldName(String name)
    {
        String[] temp = name.split("_");

        StringBuilder sb = new StringBuilder();
        int i=0;
        for(String str:temp)
        {
            int n = str.length();
            String t = "";
            i++;
            if (i == 1)
            {
                t = str.toLowerCase();
            }
            else
            {
                if (n > 1)
                {
                    t = str.substring(0, 1).toUpperCase() +str.substring(1).toLowerCase();
                }else{
                    t = str.toUpperCase();
                }
            }
            sb.append(t);
        }
        return sb.toString();
    }


    public static Map strToMap(String str)
    {
        try {
        String json = "{"+str+"}";

        json =   json.replaceAll("'","\"");

        Map map  = JsonUtils.toObject(json, HashMap.class);

        return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}

