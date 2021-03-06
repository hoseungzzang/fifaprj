package com.fifa.web.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fifa.web.bean.MainSearchBean;
import com.fifa.web.bean.MainVsSearchBean;
import com.fifa.web.bean.PageMakerBean;
import com.fifa.web.bean.PointCalcBean;
import com.fifa.web.dao.MainSearchDao;

@Service
public class MainSearchServiceImpl implements MainSearchService {
	@Inject
	private MainSearchDao dao;

	@Override
	public List<MainSearchBean> selectMainVsHistory() throws Exception {

		System.out.println("service");
		return dao.selectMainVsHistory();

	}

	@Override
	public int countBoard(MainSearchBean mainSearchBean) {
		return dao.countBoard(mainSearchBean);
	}

	@Override
	public List<PageMakerBean> selectBoard(PageMakerBean pageMakerBean) {
		return dao.selectBoard(pageMakerBean);
	}

	@Override
	public int countBoard2() {
		return dao.countBoard2();
	}

	@Override
	public void insertHistory(MainSearchBean mainSearchBean) {
		dao.insertHistory(mainSearchBean);
	}

	@Override
	public String selectWinVsSearch(MainVsSearchBean mainVsSearchBean) {
		
		
		return dao.selectWinVsSearch(mainVsSearchBean);
	}

	@Override
	public String selectLossVsSearch(MainVsSearchBean mainVsSearchBean) {
		
		return dao.selectLossVsSearch(mainVsSearchBean);
	}

	@Override
	public String selectDrawVsSearch(MainVsSearchBean mainVsSearchBean) {
		
		return dao.selectDrawVsSearch(mainVsSearchBean);
	}

	@Override
	public void updateVsHistory(MainSearchBean mainSearchBean) {
		 dao.updateVsHistory(mainSearchBean);
	}
	
	@Override
    public String selectWinCnt(PointCalcBean pointCalcBean) {
    	return dao.selectWinCnt(pointCalcBean);
    }
	
	@Override
    public String selectLossCnt(PointCalcBean pointCalcBean) {
    	return dao.selectLossCnt(pointCalcBean);
    }
	
	@Override
    public String selectDrawCnt(PointCalcBean pointCalcBean) {
    	return dao.selectDrawCnt(pointCalcBean);
    }
	
	@Override
    public List<Map<String, Object>> selectGoal(PointCalcBean pointCalcBean) {
    	return dao.selectGoal(pointCalcBean);
    }

}
