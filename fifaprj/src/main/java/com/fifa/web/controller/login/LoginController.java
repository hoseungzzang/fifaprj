package com.fifa.web.controller.login;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.SignUpBean;
import com.fifa.web.service.PlayerService;

@Controller
@RequestMapping("/login/")
public class LoginController {
	@Inject
    private PlayerService service;
	
	@RequestMapping("signup")
	public String signup(@Valid SignUpBean signUpBean,BindingResult result,Model model) throws Exception {
		model.addAttribute("getPlayerList",service.getPlayerName('N'));
		return "login/signup";
	}
	
	@RequestMapping("signok")
	public String signok() throws Exception {
		
		return "login/signok";
		
	}
	@RequestMapping(value="signok" , method=RequestMethod.POST)
	public String signok(@Valid SignUpBean signUpBean,BindingResult result,HttpServletRequest request,Model model) throws Exception {
		request.setCharacterEncoding("utf-8");
		model.addAttribute("getPlayerList",service.getPlayerName('N'));
		System.out.println(request.getParameter("userName")+"    ");
		if( result.hasErrors() ) {

			// ������ List�� ����
			List<ObjectError> list = result.getAllErrors();
			for( ObjectError error : list ) {
				System.out.println(error);
			}
			return "login/signup";
		}
		
		String userId = service.getUserId(signUpBean);
		if(userId.equals(signUpBean.getUserId())==true) {
			model.addAttribute("errorCode","�ش� ���̵�� ������ �Ǿ��ֽ��ϴ�.\n��й�ȣ �н� �� �����ڿ��� ���ǹٶ��ϴ�.");
			return "login/signup";
		}
		else {
		signUpBean.setUserYn('N');
		service.insertUserInfo(signUpBean);
		service.updateUserPindex(signUpBean.getUserPindex());
		return "login/signok";
		}
	}
	
	
}
