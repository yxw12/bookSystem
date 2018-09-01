package com.yxw.controller;

import com.yxw.pojo.Users;
import com.yxw.service.UserService;
import com.yxw.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Yan on 2018/8/30.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/success")
    public String success(HttpSession session, Model model){
        String userCode = (String) session.getAttribute("userCode");
        model.addAttribute("userCode",userCode);
        return "success";
    }

    /**
     * 用户登录
     * @param userCode
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("user/findUser")
    @ResponseBody
    public E3Result findUser(String userCode, String password, HttpSession session) {
        E3Result result = userService.findUserByNamePass(userCode,password);
        session.setAttribute("userCode",userCode);
        return result;
    }


    /**
     * 用户注册
     * @param userCode
     * @param password
     * @param repassword
     * @param gender
     * @param email
     * @return
     */
    @RequestMapping("user/registerUser")
    @ResponseBody
    public E3Result registerUser(String userCode, String password,String repassword,String gender,String email){
        if(!password.equals(repassword)){
            return E3Result.build(400,"密码不一致");
        }
        Users user = new Users();
        user.setEmail(email);
        user.setGender(gender);
        user.setPassword(password);
        user.setRegisterTime(new Date());
        user.setUserCode(userCode);
        E3Result result = userService.addUser(user);
        return result;
    }


    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }



}
