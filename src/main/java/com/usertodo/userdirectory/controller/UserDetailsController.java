package com.usertodo.userdirectory.controller;

import com.usertodo.userdirectory.model.UserDetails;
import com.usertodo.userdirectory.model.UserLogin;
import com.usertodo.userdirectory.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    //Mapping to get all user details enter by logged user
    @RequestMapping(method = RequestMethod.GET, value = "/users/getDetail")
    public String getAllUserDetails(Model model, HttpSession session){
        UserLogin user = (UserLogin) session.getAttribute("LoggedUser");
        List<UserDetails> userDetails = userDetailsService.getAllUserDetails(user.getId());
        model.addAttribute("user", userDetails);
        return "userdata";
    }

    //Mapping  for enter newDetail - get request
    @RequestMapping(method= RequestMethod.GET, value = "/users/newDetail")
    public String detail(){
        return "userdata";
    }

    //Mapping for enter newDetail - post request
    @RequestMapping(method = RequestMethod.POST, value = "/user/newDetail")
    public String newDetail(UserDetails userDetails, HttpSession session){
        //Pick the logged user
        UserLogin user = (UserLogin) session.getAttribute("LoggedUser");
        userDetails.setUserLogin(user);
        userDetailsService.createDetails(userDetails);
        return "redirect:/users/getDetail";
    }

    //Mapping to delete details
//    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
//    public String deleteDetail(@PathVariable("id") Integer userid){
//        //UserLogin user = (UserLogin) session.getAttribute("LoggedUser");
//        userDetailsService.deleteDetail(userid);
//        return "showData";
//    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "id") Integer id) {
        userDetailsService.deleteDetail(id);
        return "redirect:/users/getDetail";
    }


}
