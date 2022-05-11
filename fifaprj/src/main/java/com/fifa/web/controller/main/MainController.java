package com.fifa.web.controller.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fifa.web.bean.CalendarCalcBean;
import com.fifa.web.bean.MainSearchBean;
import com.fifa.web.bean.MainVsSearchBean;
import com.fifa.web.bean.PageMakerBean;
import com.fifa.web.bean.PlayerBean;
import com.fifa.web.bean.PointCalcBean;
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
		session = request.getSession();
		System.out.println(session.getAttribute("userName"));
		return "main.mainSearch";
	}

	@RequestMapping(value = "mainAllSearch", method = RequestMethod.GET)
	public String mainAllSearchGet(@RequestParam(value = "selectVsMatch", defaultValue = "fMatch") String vsMatch,
			PageMakerBean pageMakerBean, HttpSession session, Model model, HttpServletRequest request,
			MainSearchBean mainSearchBean, PlayerBean playerBean,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) throws Exception {
		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}

		model.addAttribute("getPlayerList", playerService.getPlayerName('Y'));
		model.addAttribute("getUserName", session.getAttribute("userName"));
		model.addAttribute("getVsType", vsMatch);
		String userName = request.getParameter("mySearch");

		mainSearchBean.setVsMatch(vsMatch);
		System.out.println("ctr selectVsMatch  " + request.getParameter("selectVsMatch"));
		System.out.println("mainctr  " + mainSearchBean.getVsMatch());
		if (userName == null && request.getParameter("userName1") != null
				&& request.getParameter("userName2") != null) {
			mainSearchBean.setVsWriter(request.getParameter("userName1"));
			mainSearchBean.setVsOpponent(request.getParameter("userName2"));

		} else {
			mainSearchBean.setVsWriter(userName);

		}

		int total = service.countBoard(mainSearchBean);
		System.out.println(total);

		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "10";
		}

		pageMakerBean = new PageMakerBean(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),
				mainSearchBean.getVsWriter(), mainSearchBean.getVsOpponent(), mainSearchBean.getVsMatch());
		model.addAttribute("paging", pageMakerBean);
		model.addAttribute("viewAll", service.selectBoard(pageMakerBean));
		return "main.mainAllSearch";
	}

	@RequestMapping(value = "mainAllSearch", method = RequestMethod.POST)
	public String mainAllSearchPost(@ModelAttribute MainSearchBean mainSearchBean, HttpSession session, Model model,
			HttpServletRequest request) throws Exception {
		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}
		System.out.println(mainSearchBean.getVsIndex() + "  " + mainSearchBean.getVsWriter());
		return "main.mainAllSearch";
	}

	@RequestMapping(value = "mainUpdate", method = RequestMethod.GET)
	public String mainUpdate(MainSearchBean mainSearchBean, HttpSession session, Model model,
			HttpServletRequest request) throws Exception {

		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}
		mainSearchBean.setVsIndex(Integer.parseInt(request.getParameter("vsIndex")));
		model.addAttribute("getPlayerList", playerService.getPlayerName('Y'));
		model.addAttribute("vsIndex", request.getParameter("vsIndex"));
		return "main.mainUpdate";
	}

	@RequestMapping(value = "mainUpdate", method = RequestMethod.POST)
	public String mainUpdatePost(MainSearchBean mainSearchBean, HttpSession session, Model model,
			HttpServletRequest request) throws Exception {

		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}
		if (mainSearchBean.getVsWriterScore() > mainSearchBean.getVsOpponentScore()) {
			mainSearchBean.setVsWinner(mainSearchBean.getVsWriter());
			mainSearchBean.setVsLoser(mainSearchBean.getVsOpponent());
		} else if (mainSearchBean.getVsWriterScore() < mainSearchBean.getVsOpponentScore()) {
			mainSearchBean.setVsWinner(mainSearchBean.getVsOpponent());
			mainSearchBean.setVsLoser(mainSearchBean.getVsWriter());
		} else {
			mainSearchBean.setVsWinner("무승부");
			mainSearchBean.setVsLoser("무승부");
		}
		/*
		 * System.out.println(mainSearchBean.getVsWriter() + " " +
		 * mainSearchBean.getVsOpponent() + " " +
		 * mainSearchBean.getVsWriterScore()+" "+mainSearchBean.getVsOpponentScore() +
		 * " " + mainSearchBean.getVsWinner() + " " + mainSearchBean.getVsLoser());
		 */
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
	public String mainWritePost(@RequestParam(value = "grparr", required = false) List<String> list,
			HttpSession session, Model model, HttpServletRequest request, MainSearchBean mainSearchBean)
			throws Exception {
		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}

		int cnt = 1;
		if (list != null) {
			if (list.size() == 5) {
				cnt = 1;
			} else
				cnt = list.size();
			for (int i = 0; i < cnt; i++) {
				if (cnt == 1) {// 단건
					mainSearchBean.setVsWriter(list.get(0));
					mainSearchBean.setVsWriterScore(Integer.parseInt(list.get(1)));
					mainSearchBean.setVsOpponentScore(Integer.parseInt(list.get(2)));
					mainSearchBean.setVsOpponent(list.get(3));
					mainSearchBean.setVsMatch(list.get(4));
				} else {// 복수
					StringTokenizer tokenizer1 = new StringTokenizer(list.get(i), ",");
					mainSearchBean.setVsWriter(tokenizer1.nextToken());
					mainSearchBean.setVsWriterScore(Integer.parseInt(tokenizer1.nextToken()));
					mainSearchBean.setVsOpponentScore(Integer.parseInt(tokenizer1.nextToken()));
					mainSearchBean.setVsOpponent(tokenizer1.nextToken());
					mainSearchBean.setVsMatch(tokenizer1.nextToken());
				}

				mainSearchBean.setVsIndex(service.countBoard2() + 1);
				System.out.println(mainSearchBean.getVsIndex());
				if (mainSearchBean.getVsWriterScore() > mainSearchBean.getVsOpponentScore()) {
					mainSearchBean.setVsWinner(mainSearchBean.getVsWriter());
					mainSearchBean.setVsLoser(mainSearchBean.getVsOpponent());
				} else if (mainSearchBean.getVsWriterScore() < mainSearchBean.getVsOpponentScore()) {
					mainSearchBean.setVsWinner(mainSearchBean.getVsOpponent());
					mainSearchBean.setVsLoser(mainSearchBean.getVsWriter());
				} else {
					mainSearchBean.setVsWinner("무승부");
					mainSearchBean.setVsLoser("무승부");
				}

				System.out.println(mainSearchBean.getVsWriter() + " " + mainSearchBean.getVsOpponent() + " "
						+ mainSearchBean.getVsWriterScore());
				service.insertHistory(mainSearchBean);

			}

		}

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

		CalendarCalcBean cal = new CalendarCalcBean();
		String date = cal.dateCalc("7");
		mainVsSearchBean.setDate(date);
		/* model.addAttribute("getPlayerList", playerService.getPlayerName('Y')); */

		for (int i = 0; i < list.size(); i++) {
			if (!session.getAttribute("userName").equals(list.get(i).getName().toString())) {
				mainVsSearchBean.setPlayer1((String) session.getAttribute("userName"));
				mainVsSearchBean.setPlayer2(list.get(i).getName().toString());
				String winCnt = service.selectWinVsSearch(mainVsSearchBean);
				String lossCnt = service.selectLossVsSearch(mainVsSearchBean);
				String draw = service.selectDrawVsSearch(mainVsSearchBean);

				float score = ((float) Integer.parseInt(winCnt)
						/ (float) (Integer.parseInt(winCnt) + Integer.parseInt(lossCnt) + Integer.parseInt(draw)))
						* 100;
				ArrayList<String> data1 = new ArrayList<>();
				data1.add((String) session.getAttribute("userName"));
				data1.add(list.get(i).getName().toString());
				data1.add(winCnt);
				data1.add(lossCnt);
				data1.add(draw);
				if (winCnt.equals("0") && lossCnt.equals("0")) {
					data1.add("0.0");
				} else
					data1.add(String.format("%.1f", score));
				data1.add("친선전");
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

		if (request.getHeader("referer") == null || session.getAttribute("userName") == null) {

			return "error";
		}

		List<PlayerBean> list = playerService.getPlayerName('Y');
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

		if (!mainVsSearchBean.getDate().equals("all")) {// 전체보기가아닐떄
			CalendarCalcBean cal = new CalendarCalcBean();
			String date = cal.dateCalc(mainVsSearchBean.getDate());
			mainVsSearchBean.setDate(date);
		}

		/* model.addAttribute("getPlayerList", playerService.getPlayerName('Y')); */
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (!request.getParameter("userName1").equals(list.get(i).getName().toString())) {
				mainVsSearchBean.setPlayer1(request.getParameter("userName1"));
				mainVsSearchBean.setPlayer2(list.get(i).getName().toString());

				String winCnt = service.selectWinVsSearch(mainVsSearchBean);
				String lossCnt = service.selectLossVsSearch(mainVsSearchBean);
				String draw = service.selectDrawVsSearch(mainVsSearchBean);
				float score = ((float) Integer.parseInt(winCnt)
						/ (float) (Integer.parseInt(winCnt) + Integer.parseInt(lossCnt) + Integer.parseInt(draw)))
						* 100;
				ArrayList<String> data1 = new ArrayList<>();
				data1.add(mainVsSearchBean.getPlayer1());
				data1.add(list.get(i).getName().toString());
				data1.add(winCnt);
				data1.add(lossCnt);
				data1.add(draw);
				if (winCnt.equals("0") && lossCnt.equals("0")) {
					data1.add("0.0");
				} else
					data1.add(String.format("%.1f", score));
				data1.add("친선전");
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

	@RequestMapping("mainLeagueSearch")
	public String mainLeagueSearch(HttpSession session, Model model, HttpServletRequest request,
			PointCalcBean pointCalcBean) throws Exception {
		int sum =0;
		int cnt = 1;
		List<String> name = playerService.selectUserCount();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < name.size(); i++) {
			List<String> cntList = new ArrayList<String>();
			String winCnt = service.selectWinCnt(name.get(i));
			String lossCnt = service.selectLossCnt(name.get(i));
			String drawCnt = service.selectDrawCnt(name.get(i));
			int calc = (Integer.parseInt(winCnt) * 3) + Integer.parseInt(drawCnt);
			List<Map<String, Object>> goal = service.selectGoal(name.get(i));
			sum=0;
			if (goal.size() > 1) {//득실체크
				if (goal.get(0) != null && goal.get(1) != null) {
					sum= Integer.parseInt(goal.get(0).get("myScore").toString())+Integer.parseInt(goal.get(1).get("myScore").toString())
					-Integer.parseInt(goal.get(0).get("enemyScore").toString())-Integer.parseInt(goal.get(1).get("enemyScore").toString());
				
				}
				else if (goal.get(0) == null && goal.get(1) != null) {
					sum= Integer.parseInt(goal.get(1).get("myScore").toString())
					-Integer.parseInt(goal.get(1).get("enemyScore").toString());
					
				}
				else {
					sum= Integer.parseInt(goal.get(0).get("myScore").toString())
							-Integer.parseInt(goal.get(0).get("enemyScore").toString());
					
				}
			}
			cntList.add(winCnt);
			cntList.add(drawCnt);
			cntList.add(lossCnt);
			cntList.add(Integer.toString(calc));
			cntList.add(Integer.toString(sum));
			map.put(name.get(i), calc);// 이름하고 승점
			map2.put(name.get(i), cntList);// 이름하고 승무

		}
		List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
		entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		for (Map.Entry<String, Integer> entry : entryList) {
			String[] array = map2.get(entry.getKey()).toString().split(", ");
			
			ArrayList<String> data1 = new ArrayList<>();
			data1.add(Integer.toString(cnt));
			data1.add(entry.getKey());

			data1.add(array[0].substring(1));
			data1.add(array[1]);
			data1.add(array[2]);
			data1.add(array[3]);
			String[] array2 = array[4].split("]");
			data1.add(array2[0]);
			datas.add(data1);
			cnt++;
		}
		model.addAttribute("getPoint", datas);
		return "main.mainLeagueSearch";
	}
}
