package com.fifa.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fifa.web.bean.MainSearchBean;
import com.fifa.web.bean.MainVsSearchBean;
import com.fifa.web.bean.PageMakerBean;
import com.fifa.web.bean.PointCalcBean;


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
	
	@Override
	public String selectDrawVsSearch(MainVsSearchBean mainVsSearchBean) {
	
		return sqlSession.selectOne(namespace+".selectDrawVsSearch",mainVsSearchBean);
	}
	
	@Override
	public void updateVsHistory(MainSearchBean mainSearchBean) {
		sqlSession.update(namespace+".updateVsHistory",mainSearchBean);
	}
	
	@Override
    public String selectWinCnt(String userName) {
    	return sqlSession.selectOne(namespace+".selectWinCnt",userName);
    }
	
	@Override
    public String selectLossCnt(String userName) {
    	return sqlSession.selectOne(namespace+".selectLossCnt",userName);
    }
	
	@Override
    public String selectDrawCnt(String userName) {
    	return sqlSession.selectOne(namespace+".selectDrawCnt",userName);
    }
	
	@Override
    public List<Map<String, Object>> selectGoal(String userName) {
    	return sqlSession.selectList(namespace+".selectGoal",userName);
    }
}
