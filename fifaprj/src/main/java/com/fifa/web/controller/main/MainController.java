package com.fifa.web.controller.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fifa.web.bean.MainSearchBean;
import com.fifa.web.bean.MainVsSearchBean;
import com.fifa.web.bean.PageMakerBean;
import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.SignUpBean;
import com.fifa.web.service.MainSearchService;
import com.fifa.web.service.PlayerService;

@Controller
@RequestMapping("/main/")
public class MainController {

	@Inject
	private MainSearchService service;

	@Inject
	private PlayerService playerService;

	@RequestMapping("mainSearch")
	public String mainSearch(HttpSession session, Model model, HttpServletRequest request, PlayerBean playerBean)
			throws Exception {

		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}

		model.addAttribute("getPlayerList", playerService.getPlayerNum());
		System.out.println(playerBean.getName());
		System.out.println();
		System.out.println(session.getId());
		System.out.println(session);
		session = request.getSession();
		System.out.println(session.getAttribute("userName"));
		return "main.mainSearch";
	}

	@RequestMapping(value = "mainAllSearch", method = RequestMethod.GET)
	public String mainAllSearchGet(PageMakerBean pageMakerBean, HttpSession session, Model model,
			HttpServletRequest request, MainSearchBean mainSearchBean, PlayerBean playerBean,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) throws Exception {

		
		model.addAttribute("getPlayerList", playerService.getPlayerName('Y'));
		model.addAttribute("getUserName", session.getAttribute("userName"));
		String userName = request.getParameter("mySearch");

		if (userName == null && request.getParameter("userName1") != null
				&& request.getParameter("userName2") != null) {
			mainSearchBean.setVsWriter(request.getParameter("userName1"));
			mainSearchBean.setVsOpponent(request.getParameter("userName2"));

		} else {
			mainSearchBean.setVsWriter(userName);

		}

	

		int total = service.countBoard(mainSearchBean);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "10";
		}
		
		pageMakerBean = new PageMakerBean(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),
				mainSearchBean.getVsWriter(), mainSearchBean.getVsOpponent());
		model.addAttribute("paging", pageMakerBean);
		model.addAttribute("viewAll", service.selectBoard(pageMakerBean));
		return "main.mainAllSearch";
	}
	
	@RequestMapping(value = "mainAllSearch", method = RequestMethod.POST)
	public String mainAllSearchPost(@ModelAttribute MainSearchBean mainSearchBean,HttpSession session, Model model,
			HttpServletRequest request) throws Exception {

		
		System.out.println(mainSearchBean.getVsIndex()+"  "+ mainSearchBean.getVsWriter());
		return "main.mainAllSearch";
	}
	
	@RequestMapping(value = "mainUpdate", method = RequestMethod.GET)
	public String mainUpdate(MainSearchBean mainSearchBean, HttpSession session, Model model, HttpServletRequest request
			) throws Exception {
		
		System.out.println(request.getParameter("vsIndex"));
	
		model.addAttribute("getPlayerList", playerService.getPlayerName('Y'));
		
		return "main.mainUpdate";
	}
	
	@RequestMapping(value = "mainUpdate", method = RequestMethod.POST)
	public String mainUpdatePost(MainSearchBean mainSearchBean, HttpSession session, Model model, HttpServletRequest request
			) throws Exception {
		
		System.out.println(mainSearchBean.getVsIndex()+" ");
		if (mainSearchBean.getVsWriterScore() > mainSearchBean.getVsOpponentScore()) {
			mainSearchBean.setVsWinner(mainSearchBean.getVsWriter());
			mainSearchBean.setVsLoser(mainSearchBean.getVsOpponent());
		} else if (mainSearchBean.getVsWriterScore() < mainSearchBean.getVsOpponentScore()) {
			mainSearchBean.setVsWinner(mainSearchBean.getVsOpponent());
			mainSearchBean.setVsLoser(mainSearchBean.getVsWriter());
		} else {
			mainSearchBean.setVsWinner("公铰何");
			mainSearchBean.setVsLoser("公铰何");
		}
		System.out.println(mainSearchBean.getVsOpponent() + " " + mainSearchBean.getVsWriter() + " "
				+ mainSearchBean.getVsWriterScore()+" "+mainSearchBean.getVsOpponentScore() + " " + mainSearchBean.getVsWinner() + " "
				+ mainSearchBean.getVsLoser());	
		service.updateVsHistory(mainSearchBean);
		return "redirect:mainAllSearch";
	}

	@RequestMapping("mainWrite")
	public String mainWriteGet(HttpSession session, Model model, HttpServletRequest request,
			MainSearchBean mainSearchBean) throws Exception {
		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}
		model.addAttribute("getPlayerList", playerService.getPlayerName('Y'));
		
		return "main.mainWrite";
	}

	@RequestMapping(value = "mainWrite", method = RequestMethod.POST)
	public String mainWritePost(HttpSession session, Model model, HttpServletRequest request,
			MainSearchBean mainSearchBean) throws Exception {
		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}
		mainSearchBean.setVsIndex(service.countBoard2() + 1);
		if (mainSearchBean.getVsWriterScore() > mainSearchBean.getVsOpponentScore()) {
			mainSearchBean.setVsWinner(mainSearchBean.getVsWriter());
			mainSearchBean.setVsLoser(mainSearchBean.getVsOpponent());
		} else if (mainSearchBean.getVsWriterScore() < mainSearchBean.getVsOpponentScore()) {
			mainSearchBean.setVsWinner(mainSearchBean.getVsOpponent());
			mainSearchBean.setVsLoser(mainSearchBean.getVsWriter());
		} else {
			mainSearchBean.setVsWinner("公铰何");
			mainSearchBean.setVsLoser("公铰何");
		}
			

		System.out.println(mainSearchBean.getVsOpponent() + " " + mainSearchBean.getVsWriter() + " "
				+ mainSearchBean.getVsWriterScore());
		service.insertHistory(mainSearchBean);
		return "redirect:mainWrite";
	}

	@RequestMapping("mainVsSearch")
	public String mainVsSearch(HttpSession session, Model model, HttpServletRequest request,
			MainVsSearchBean mainVsSearchBean) throws Exception {
		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}
		List<PlayerBean> list = playerService.getPlayerName('Y');
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

		/* model.addAttribute("getPlayerList", playerService.getPlayerName('Y')); */
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (!session.getAttribute("userName").equals(list.get(i).getName().toString())) {
				mainVsSearchBean.setPlayer1((String) session.getAttribute("userName"));
				mainVsSearchBean.setPlayer2(list.get(i).getName().toString());
				System.out.println(list.get(i).getName().toString());
				String winCnt = service.selectWinVsSearch(mainVsSearchBean);
				String lossCnt = service.selectLossVsSearch(mainVsSearchBean);
				String draw = service.selectDrawVsSearch(mainVsSearchBean);
				float score = ((float) Integer.parseInt(winCnt) / (float) (Integer.parseInt(winCnt) + Integer.parseInt(lossCnt) + Integer.parseInt(draw))) * 100;
				ArrayList<String> data1 = new ArrayList<>();
				data1.add((String) session.getAttribute("userName"));
				data1.add(list.get(i).getName().toString());
				data1.add(winCnt);
				data1.add(lossCnt);
				data1.add(draw);
				if (winCnt.equals("0") && lossCnt.equals("0")) {
					data1.add("0.0");
				} else
					data1.add(String.format("%.1f",score));
				data1.add("模急傈");
				datas.add(data1);
			}

		}
		/*
		 * (String)
		 * session.getAttribute("userName"),list.get(i).getName().toString(),1,1,"dsad";
		 */
		model.addAttribute("getVsList", datas);
		model.addAttribute("getPlayerList", list);

		return "main.mainVsSearch";
	}

	@RequestMapping(value = "mainVsSearch", method = RequestMethod.POST)
	public String mainVsSearchPost(HttpSession session, Model model, HttpServletRequest request,
			MainVsSearchBean mainVsSearchBean) throws Exception {
		/*
		 * if (request.getHeader("referer") == null || session.getAttribute("userName")
		 * == null) {
		 * 
		 * return "error"; }
		 */
		List<PlayerBean> list = playerService.getPlayerName('Y');
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

		/* model.addAttribute("getPlayerList", playerService.getPlayerName('Y')); */
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (!request.getParameter("userName1").equals(list.get(i).getName().toString())) {
				mainVsSearchBean.setPlayer1(request.getParameter("userName1"));
				mainVsSearchBean.setPlayer2(list.get(i).getName().toString());
				String winCnt = service.selectWinVsSearch(mainVsSearchBean);
				String lossCnt = service.selectLossVsSearch(mainVsSearchBean);
				String draw = service.selectDrawVsSearch(mainVsSearchBean);
				float score = ((float) Integer.parseInt(winCnt) / (float) (Integer.parseInt(winCnt) + Integer.parseInt(lossCnt) + Integer.parseInt(draw))) * 100;
				ArrayList<String> data1 = new ArrayList<>();
				data1.add(mainVsSearchBean.getPlayer1());
				data1.add(list.get(i).getName().toString());
				data1.add(winCnt);
				data1.add(lossCnt);
				data1.add(draw);
				if (winCnt.equals("0") && lossCnt.equals("0")) {
					data1.add("0.0");
				} else
					data1.add(String.format("%.1f",score));
				data1.add("模急傈");
				datas.add(data1);
			}

		}
		/*
		 * (String)
		 * session.getAttribute("userName"),list.get(i).getName().toString(),1,1,"dsad";
		 */
		model.addAttribute("getVsList", datas);
		model.addAttribute("getPlayerList", list);

		return "main.mainVsSearch";
	}
}
