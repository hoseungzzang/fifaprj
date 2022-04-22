package com.fifa.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fifa.web.bean.SignUpBean;
import com.fifa.web.service.PlayerService;


@Controller
@RequestMapping("/")
public class HomeController{
	@Autowired
    private PlayerService service;
	
	@RequestMapping(value={"/","/index"})
	public String index()throws Exception {
		return "index";
	}

	
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String Login(@ModelAttribute SignUpBean signUpBean, HttpServletRequest request,Model model)throws Exception {
		HttpSession session = request.getSession();
		String name = service.loginCheck(signUpBean);
		System.out.println(name);
		if(name != null) {
			session.setAttribute("userName", name);
			return "redirect:main/mainSearch";
		}
		else {
			model.addAttribute("errorCode","아이디 또는 비밀번호가 틀렸습니다.");
			return "index";
		}
		
	}
	
	@RequestMapping("/logout")
	public String Logout(HttpSession session)throws Exception {
		service.logout(session);
		return "redirect:index";
	}

}
