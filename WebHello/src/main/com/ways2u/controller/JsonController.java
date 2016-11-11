package com.ways2u.controller;

import com.ways2u.model.User;
import com.ways2u.redis.RedisProvider;
import com.ways2u.server.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by huanglong on 16/9/7.
 */
@Controller
public class JsonController {
    private final  Logger logger = LoggerFactory.getLogger(JsonController.class);
    //Logger logger = Logger.getLogger(JsonController.class.getName());
    @Autowired
    private UserService userService;
/*
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        //指定格式
        logger.info("initBinder=" + webDataBinder);
    }
*/

    @RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String hello(HttpServletRequest request, HttpServletResponse response) {

        //Jedis r = RedisProvider.getJedis();
        //r.set("qq","sss");
        //r.close();

        return "你好！hello";
    }

    @RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String say(@PathVariable(value = "msg") String msg) {
        return "{\"msg\":\"you say:'" + msg + "'\"}";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getPerson(@RequestParam(value="tt", required = false)  String txt) {
        logger.info("txt=" + txt);
        //Jedis r = RedisProvider.getJedis();
        //logger.info("qq=" + r.get("qq"));
       // r.close();

        return userService.selectUser();
    }

    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET)
    public @ResponseBody
    User getPerson(@PathVariable("id") int id) {
        logger.info("获取人员信息id=" + id);
        User person =userService.selectUserById(id);
        return person;
    }

    @RequestMapping(value = "/del/{id:\\d+}", method = RequestMethod.GET)
    public @ResponseBody
    List<User> delPerson(@PathVariable("id") int id) {
        logger.info("获取人员信息id=" + id);
        User user = new User();
        user.setUserId(id);
        userService.deleteUser(user);
        return userService.selectUser();
    }

    @RequestMapping(value = "/update/{id:\\d+}", method = RequestMethod.GET)
    public @ResponseBody
    User updatePerson(@PathVariable("id") int id)
    {
        logger.info("获取人员信息id=" + id);
        User person =userService.selectUserById(id);
        person.setUserEmail("onelong@qq.com");
        userService.updateUser(person);
        return userService.selectUserById(id);
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object addPerson(@RequestBody(required = false) String string) {
        logger.info("string=" + string);
        return string;
    }


/*
    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
    public @ResponseBody
    Object deletePerson(@PathVariable("id") int id) {
        logger.info("删除人员信息id=" + id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "删除人员信息成功");
        return jsonObject;
    }
*/
/*
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public @ResponseBody
    Object addPerson(Person person) {
        logger.info("注册人员信息成功id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "注册人员信息成功");
        return jsonObject;
    }
    */
/*
    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    public @ResponseBody
    Object updatePerson(Person person) {
        logger.info("更新人员信息id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "更新人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PATCH)
    public @ResponseBody
    List<Person> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

        logger.info("查询人员name like " + name);
        List<Person> lstPersons = new ArrayList<Person>();

        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        person.setAge(25);
        person.setId(101);
        lstPersons.add(person);

        Person person2 = new Person();
        person2.setName("李四");
        person2.setSex("女");
        person2.setAge(23);
        person2.setId(102);
        lstPersons.add(person2);

        Person person3 = new Person();
        person3.setName("王五");
        person3.setSex("男");
        person3.setAge(27);
        person3.setId(103);
        lstPersons.add(person3);

        return lstPersons;
    }
*/
}
