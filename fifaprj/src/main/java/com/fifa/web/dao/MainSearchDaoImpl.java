package com.fifa.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fifa.web.bean.MainSearchBean;
import com.fifa.web.bean.MainVsSearchBean;
import com.fifa.web.bean.PageMakerBean;


@Repository
public class MainSearchDaoImpl implements MainSearchDao {
	private static final String namespace="com.fifa.web.testMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MainSearchBean> selectMainVsHistory() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("dao");
		return sqlSession.selectList(namespace+".selectMainVsHistory");
	}
	
	@Override
	public int countBoard(MainSearchBean mainSearchBean) {
	
	  
		return sqlSession.selectOne(namespace+".countBoard",mainSearchBean);
	}

	@Override
	public List<PageMakerBean> selectBoard(PageMakerBean pageMakerBean) {
		return sqlSession.selectList(namespace+".selectBoard",pageMakerBean);
	}
	
	@Override
	public int countBoard2() {
	
		return sqlSession.selectOne(namespace+".countBoard2");
	}
	
	@Override
	public void insertHistory(MainSearchBean mainSearchBean) {
	
		sqlSession.insert(namespace+".insertHistory",mainSearchBean);
	}
	
	@Override
	public String selectWinVsSearch(MainVsSearchBean mainVsSearchBean) {
	
		return sqlSession.selectOne(namespace+".selectWinVsSearch",mainVsSearchBean);
	}
	
	@Override
	public String selectLossVsSearch(MainVsSearchBean mainVsSearchBean) {
	
		return sqlSession.selectOne(namespace+".selectLossVsSearch",mainVsSearchBean);
	}
}