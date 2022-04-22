package com.fifa.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.SignUpBean;
 
 
public interface PlayerService {
    
     public List<PlayerBean> getPlayerName(char signUp) throws Exception;
 
     public void insertUserInfo(SignUpBean signUpBean) throws Exception;
     
     public String loginCheck(SignUpBean signUpBean);
     
     public String getUserId(SignUpBean signUpBean);
     
     public void updateUserPindex(int userPindex);
     
     public void logout(HttpSession session);
     
     public int getPlayerNum();
}

