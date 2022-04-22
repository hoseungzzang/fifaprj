package com.fifa.web.service;

import java.util.List;

import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.SignUpBean;
import com.fifa.web.dao.PlayerDao;
 
 
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
 
 
@Service
public class PlayerServiceImpl implements PlayerService {
 
     @Inject
     private PlayerDao dao;    
    
    @Override
    public List<PlayerBean> getPlayerName(char signUp) throws Exception {
        // TODO Auto-generated method stub
        return dao.getPlayerName(signUp);
    }
 
    @Override
	public void insertUserInfo(SignUpBean signUpBean) throws Exception {
		// TODO Auto-generated method stub
    
		 dao.insertUserInfo(signUpBean);
	}
    
    @Override
    public String loginCheck(SignUpBean signUpBean) {
    String name = dao.loginCheck(signUpBean);
     return name; 
    }

    @Override
    public void logout(HttpSession session) {
     session.invalidate(); // 세션 초기화
     }
    
    @Override
    public String getUserId(SignUpBean signUpBean) {
    String ID = dao.getUserId(signUpBean);
    if (ID == null) { // 세션 변수 저장
        ID = "nullCheck";
      }
    System.out.println("service"+ID);
     return ID; 
    }
    
    @Override
    public void updateUserPindex(int userPindex) {
    	dao.updateUserPindex(userPindex);
    }
    
    @Override
    public int getPlayerNum() {
    	return dao.getPlayerNum();
    }

}

