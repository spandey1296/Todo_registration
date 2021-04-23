package com.usertodo.userdirectory.controller;


import com.usertodo.userdirectory.model.UserLogin;
import com.usertodo.userdirectory.model.UserSignUp;
import com.usertodo.userdirectory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    //Mapping for login user - Get request
    @RequestMapping(method = RequestMethod.GET, value = "/users/login")
    public String loginUser(Model model){
        model.addAttribute("user", new UserLogin());
        return "login";
    }

    //Mapping to check user credentials - Post request
    @RequestMapping(method = RequestMethod.POST, value = "/users/login")
    public String checkUser(UserLogin user , HttpSession session){
        UserLogin existingUser = userService.loginUser(user);
        if(existingUser == null){
            System.out.println("User not exist");
            return "login";
        }else {
            // Maintain the session
            session.setAttribute("LoggedUser", existingUser);
            return "userdata";
        }
    }

    //Mapping to register user - Get request
    @RequestMapping(method = RequestMethod.GET, value = "/users/registration")
    public String register(Model model){
        UserLogin userLogin = new UserLogin();
        UserSignUp userSignUp = new UserSignUp();
        userLogin.setUserSignUp(userSignUp);
        model.addAttribute("user", new UserLogin());
        return "registration";

    }

    //Mapping to register user - Post request
    @RequestMapping(method = RequestMethod.POST, value = "/users/registration")
    public String registerUser(UserLogin user){
         userService.registerUser(user);
         return "redirect:/users/login";
    }


}
