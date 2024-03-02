package org.frank.vote.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @RequestMapping(value="/login.html",method={RequestMethod.GET,RequestMethod.POST})
    public String loginPage(){
        return "/login";
    }

    @RequestMapping(value="/fail",method={RequestMethod.GET,RequestMethod.POST})
    public String failLogin(Model model){        
        return "/login";
    }

    @RequestMapping(value="/welcome",method={RequestMethod.GET})
    public String welcome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username",username);
        return "/pages/welcome";
    }
}
