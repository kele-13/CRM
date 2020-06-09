package com.kele.controller;

import com.kele.pojo.CrmUser;
import com.kele.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 12402
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("dologin")
    public String doLogin(String usercode, String password, Model model, HttpSession session) {

        try {
            CrmUser user = userService.userLogin(usercode, password);
            session.setAttribute("user", user);
            return "redirect:/customer/list";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/login";
        }

    }

    @RequestMapping("/logoutuser")
    public String logoutuser(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/doregist")
    public String userRegist(String usercode, String password, String password2, Model model) {
        /**
         * 1.用户名密码不能为空
         * 2.用户不能重复
         * 3.两次密码一致
         */

        if (!password.equals(password2)) {
            model.addAttribute("error", "两次密码不一致");
            return "/regist";
        }
        try {
            CrmUser user = userService.userRegist(usercode, password);
            return "/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "regist";
        }
    }
}
