package com.fifa.web.dao;

import java.util.List;

import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.SignUpBean;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;
import javax.inject.Inject;

@Repository
public class PlayerDaoImpl implements PlayerDao {
	private static final String namespace="com.fifa.web.testMapper";

	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<PlayerBean> getPlayerName(char signUp) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getPlayerName",signUp);
	}
	
	@Override
	public void insertUserInfo(SignUpBean signUpBean) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.insert(namespace+".insertUserInfo",signUpBean);
	}
	
	@Override
	public String loginCheck(SignUpBean signUpBean) {
	return sqlSession.selectOne(namespace+".login_check", signUpBean);
	 }
	
	@Override
	public String getUserId(SignUpBean signUpBean) {
		System.out.println("DAO"+signUpBean.getUserId());
	return sqlSession.selectOne(namespace+".getUserId", signUpBean);
	 }
	
	@Override
	public void updateUserPindex(int userPindex) {
		sqlSession.update(namespace+".updateUserPindex", userPindex);
	 }
	
	@Override
	public int getPlayerNum() {
		return sqlSession.selectOne(namespace+".getPlayerNum");
	 }

}
