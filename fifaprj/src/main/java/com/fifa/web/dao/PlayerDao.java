package com.fifa.web.dao;

import java.util.List;
import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.SignUpBean;
public interface PlayerDao {
	
	public List<PlayerBean> getPlayerName(char signUp) throws Exception;
	
	public void insertUserInfo(SignUpBean signUpBean) throws Exception;
	
	public String loginCheck(SignUpBean signUpBean);
	
	public String getUserId(SignUpBean signUpBean);
	
	public void updateUserPindex(int userPindex);
	
	public int getPlayerNum();
	
	public List<String> selectUserCount();
}
