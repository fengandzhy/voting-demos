package org.frank.vote.controllers;

import com.google.code.kaptcha.Producer;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
public class LoginController {

    private Producer kaptCha;

    @RequestMapping(value="/login.html",method={RequestMethod.GET,RequestMethod.POST})
    public String loginPage(){
        return "/login";
    }

    @RequestMapping(value="/vote",method={RequestMethod.GET})
    public String welcome(@NotNull Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username",username);
        return "/pages/vote";
    }

    @GetMapping("/vc.jpg")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = kaptCha.createText();
        session.setAttribute("captcha", text);
        BufferedImage image = kaptCha.createImage(text);
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

    @Autowired
    public void setKaptCha(Producer kaptCha) {
        this.kaptCha = kaptCha;
    }
}
