package org.frank.vote.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value="/login.html",method={RequestMethod.GET,RequestMethod.POST})
    public String loginPage(){
        return "/login";
    }

    @ResponseBody
    @RequestMapping(value="/test",method={RequestMethod.GET,RequestMethod.POST})
    public String test(){
        return "test";
    }
}
