package kr.gudi.app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gudi.app.db1.mapper.ETLMapper1;
import kr.gudi.app.db2.mapper.ETLMapper2;


@Service
public class DbService {
	
	@Autowired ETLMapper1 etl1;
	@Autowired ETLMapper2 etl2;
	
	//ODS
	public int getOntime(){
		etl2.truncate();
		int count = etl1.count();
		System.out.println("총갯수 :" + count);
		int limit = 0; 
		if(count > 0) {	
			while(true) {
				List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
				resultList = etl1.getOntime(limit);
		        System.out.println("limit :" + limit);
		        etl2.setOntime(resultList);
		           limit += 10000;
	               if(limit >= count) {
	               System.out.println("end");
	               break;
	               }
		    }
		return 1;
		} 
		return 0;
	}
	
	public int cnt() {
		return etl2.cnt();
	}
	
	//DW
	public int setDW(){
		int count = etl2.setOdsCnt();
		System.out.println("총갯수 :" + count);
		int limit = 0; 
		if(count > 0) {	
			while(true) {
				List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
				resultList = etl2.getdw(limit);
		        System.out.println("limit :" + limit);
		        etl2.setDw(resultList);
		           limit += 10000;
	               if(limit >= count) {
	               System.out.println("end");
	               break;
	               }
		    }
		return 1;
		} 
		return 0;
	}
	
	public int getDwCnt() {
		return etl2.getDwCnt();
	}
	
	//DM
	public int setDM(){
		int DayCount = etl2.dayDmCnt();
		int WeekCount = etl2.weekDmCnt();
		int UniqueCarrierCount = etl2.UCDmCnt();
		if(DayCount > 0) {	
			System.out.println("일별 총갯수 :" + DayCount);
				List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
				resultList = etl2.getdm1();
				System.out.println("일별 데이터 :" + resultList);
				etl2.setDmDay(resultList);
		}
		if(WeekCount > 0) {	
			System.out.println("주별 총갯수 :" + WeekCount);
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			resultList = etl2.getdm2();
			System.out.println("주별 데이터 :" + resultList);
			etl2.setDmWeek(resultList);
		}
		if(DayCount > 0) {	
			System.out.println("항공사별 총갯수 :" + UniqueCarrierCount);
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			resultList = etl2.getdm3();
			System.out.println("항공사별 데이터 :" + resultList);
			etl2.setDmCarrier(resultList);
			return 1;
		}
		return 0;
	}
	
	public int getDmCnt() {
		return etl2.getDmCnt();
	}
}
