package kr.gudi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.gudi.app.service.DbService;

@Controller
public class HomeController {
	
	@Autowired DbService dbs;
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home");
		return "home";
	}
	//ODS
	@RequestMapping(value = "/ods", method=RequestMethod.POST)
	public @ResponseBody int ods() {
		System.out.println("home");
		int result = dbs.getOntime();
		System.out.println(result);
		if(result == 1) System.out.println("성공");
		else System.out.println("실패");
		return result;
	}
	
	@RequestMapping(value = "/odsCnt", method=RequestMethod.POST)
	public @ResponseBody int cnt() {
		return dbs.cnt();
	}
	
	//DW
	@RequestMapping(value = "/dw", method=RequestMethod.POST)
	public @ResponseBody int dw() {
		int result = dbs.setDW();
		System.out.println(result);
		if(result == 1) System.out.println("성공");
		else System.out.println("실패");
		return result;
	}
	
	@RequestMapping(value = "/dwCnt", method=RequestMethod.POST)
	public @ResponseBody int dwCnt() {
		return dbs.getDwCnt();
	}
	
	//DM
	@RequestMapping(value = "/dm", method=RequestMethod.POST)
	public @ResponseBody int dm() {
		int result = dbs.setDM();
		System.out.println(result);
		if(result == 1) System.out.println("성공");
		else System.out.println("실패");
		return result;
	}
	
	@RequestMapping(value = "/dmCnt", method=RequestMethod.POST)
	public @ResponseBody int dmCnt() {
		return dbs.getDmCnt();
	}
}
