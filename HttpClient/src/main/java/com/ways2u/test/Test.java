package com.ways2u.test;

import com.google.gson.reflect.TypeToken;
import com.ways2u.net.PostJsonService;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by huanglong on 2016/12/14.
 */
public class Test {
    public static void main(String[] agvs) {
        Method method[] = null;
        try {
            method = PostJsonService.class.getMethods();//.getMethod("applyMethod",Map.Entry.class);

            for (Method m:method){
                System.out.println(m.getGenericReturnType());
            }
            testGetFunctionName();

            //method = new Test().getClass().getMethod("applyMethod",MyRespone.class);

            //Type[] types = method.getGenericParameterTypes();
            //ParameterizedType pType = (ParameterizedType)types[0];
            //for (Type type:types)
            //    System.out.println(type);
            //System.out.println(method.getGenericReturnType());

            MyRespone<Person> respone = new MyRespone<Person>();
            respone.body = new Person();

            //返回所有者类型，打印结果是interface java.util.Map
            //System.out.println(pType.getOwnerType());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static MyRespone<Person> applyMethod(MyRespone<Person> respone1){
        MyRespone<Person> respone = new MyRespone<Person>();
        respone.body = new Person();
        return respone;
    }



    public static void testGetFunctionName() {
        // 方法1：通过Throwable的方法getStackTrace()
        //String funcName2 = new Throwable().getStackTrace()[1].getMethodName();
        //System.out.println(funcName2);
        //方法2：通过Thread的方法getStackTrace()
        //1.获取当前函数名：Thread.currentThread().getStackTrace()[2].getMethodName();
        //2.获取当前类名：Thread.currentThread().getStackTrace()[2].getClassName();
        String clazzName4 = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(clazzName4);
    }

}
