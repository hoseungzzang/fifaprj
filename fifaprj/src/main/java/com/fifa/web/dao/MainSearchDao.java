package com.fifa.web.dao;

import java.util.List;
import java.util.Map;

import com.fifa.web.bean.MainSearchBean;
import com.fifa.web.bean.MainVsSearchBean;
import com.fifa.web.bean.PageMakerBean;
import com.fifa.web.bean.PointCalcBean;

public interface MainSearchDao {

	public List<MainSearchBean> selectMainVsHistory() throws Exception;

	// 게시물 총 갯수
	public int countBoard(MainSearchBean mainSearchBean);

	// 페이징 처리 게시글 조회
	public List<PageMakerBean> selectBoard(PageMakerBean pageMakerBean);

	public int countBoard2();

	public void insertHistory(MainSearchBean mainSearchBean);
	
	public String selectWinVsSearch(MainVsSearchBean mainVsSearchBean);
	
	public String selectLossVsSearch(MainVsSearchBean mainVsSearchBean);
	
	public String selectDrawVsSearch(MainVsSearchBean mainVsSearchBean);
	
	public void updateVsHistory(MainSearchBean mainSearchBean);
	
	public String selectWinCnt(PointCalcBean pointCalcBean);
	
	public String selectLossCnt(PointCalcBean pointCalcBean);
	
	public String selectDrawCnt(PointCalcBean pointCalcBean);
	
	public List<Map<String, Object>> selectGoal(PointCalcBean pointCalcBean);
}
