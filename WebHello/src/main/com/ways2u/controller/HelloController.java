package com.ways2u.controller;

import com.ways2u.model.User;
import com.ways2u.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * Created by huanglong on 16/9/7.
 */
@org.springframework.stereotype.Controller("/hello.do")
public class HelloController implements Controller
{
    @Autowired
    private UserService userService;

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView();
        //封装要显示到视图中的数据
        User user = userService.selectUserById(10);
        mav.addObject("liu", user);
        mav.setViewName("index.jsp");//这个目录要根据视图解析器的位置去判断
        //因为我设置的视图解析器前缀是根目录 所以 我这样配置 就会在根目录下
        //找到index.jsp 进行渲染
        return mav;
    }
}
