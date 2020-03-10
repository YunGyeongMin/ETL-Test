package kr.gudi.app.db2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ETLMapper2 {
	//TEST
	@Select("select 2")
	public Integer test();
	
	//ODS
	@Insert("<script>"
			+"INSERT INTO ODS (`FlightNum`,`Origin`,`LateAircraftDelay`,`NASDelay`,`ArrTime`,`AirTime`,`DepTime`,`Month`,`CRSElapsedTime`,`DayofMonth`,`Distance`,`CRSDepTime`,`SecurityDelay`,`DayOfWeek`,`Dest`,`CancellationCode`,`DepDelay`,`TaxiIn`,`UniqueCarrier`,`ArrDelay`,`Cancelled`,`Diverted`,`TaxiOut`,`ActualElapsedTime`,`CarrierDelay`,`Year`,`WeatherDelay`,`CRSArrTime`,`TailNum`) "
			+ "VALUES "
			+"<foreach item='item' index='index' collection='list' separator=','>"
			+ "(#{item.FlightNum},#{item.Origin},#{item.LateAircraftDelay},#{item.NASDelay},#{item.ArrTime},#{item.AirTime},#{item.DepTime},#{item.Month},#{item.CRSElapsedTime},#{item.DayofMonth},#{item.Distance},#{item.CRSDepTime},#{item.SecurityDelay},#{item.DayOfWeek},#{item.Dest},#{item.CancellationCode},#{item.DepDelay},#{item.TaxiIn},#{item.UniqueCarrier},#{item.ArrDelay},#{item.Cancelled},#{item.Diverted},#{item.TaxiOut},#{item.ActualElapsedTime},#{item.CarrierDelay},#{item.Year},#{item.WeatherDelay},#{item.CRSArrTime},#{item.TailNum})" 
			+"</foreach>"
			+"</script>")
	public int setOntime(List<Map<String, Object>> list);
	
	@Delete("TRUNCATE TABLE ODS")
	public void truncate();
	
	@Select("SELECT COUNT(`Year`) AS count FROM ODS")
	public int cnt();
	
	//DW 
	@Select("SELECT `Cancelled`, `ArrDelay`, `DepDelay`, `DayofMonth`, `DayofWeek`, `UniqueCarrier`,`reg` FROM ODS WHERE Cancelled = '0' AND `reg` = date_format(now(), '%Y-%m-%d') limit ${limit},10000")
	public List<Map<String, Object>> getdw(int limit);
	
	@Insert("<script>"
			+"INSERT INTO DW (`Cancelled`, `ArrDelay`, `DepDelay`, `DayofMonth`, `DayofWeek`, `UniqueCarrier`) "
			+ "VALUES "
			+"<foreach item='item' index='index' collection='list' separator=','>"
			+ "(#{item.Cancelled}, #{item.ArrDelay}, #{item.DepDelay}, #{item.DayofMonth}, #{item.DayofWeek}, #{item.UniqueCarrier})" 
			+"</foreach>"
			+"</script>")
	public int setDw(List<Map<String, Object>> list);
	
	@Select("SELECT COUNT(`ArrDelay`) AS count FROM ODS WHERE Cancelled = '0' AND `reg` = date_format(now(), '%Y-%m-%d') ")
	public int setOdsCnt();
	
	@Select("SELECT COUNT(`Cancelled`) AS count FROM DW")
	public int getDwCnt();
	
	//DM
	@Select("SELECT ROUND(AVG(`ArrDelay` - `DepDelay`), 2) AS DelayTimer , `DayofMonth`, `reg` FROM DW WHERE Cancelled = '0' AND `reg` = date_format(now(), '%Y-%m-%d')  GROUP BY `DayofMonth` ORDER BY `DayofMonth`+0")
	public List<Map<String, Object>> getdm1(); // 일
	
	@Select("SELECT ROUND(AVG(`ArrDelay` - `DepDelay`), 2) AS DelayTimer , `DayofWeek`, `reg` FROM DW WHERE Cancelled = '0' AND `reg` = date_format(now(), '%Y-%m-%d')  GROUP BY `DayofWeek` ORDER BY `DayofWeek`")
	public List<Map<String, Object>> getdm2(); // 요일
	
	@Select("SELECT ROUND(AVG(`ArrDelay` - `DepDelay`), 2) AS DelayTimer , `UniqueCarrier`,`reg` FROM DW WHERE Cancelled = '0' AND `reg` = date_format(now(), '%Y-%m-%d')  GROUP BY `UniqueCarrier` ORDER BY `UniqueCarrier`")
	public List<Map<String, Object>> getdm3(); // 항공사
	
	@Insert("<script>"
			+"INSERT INTO DM (`DelayTimer`, `DayofMonth`) "
			+ "VALUES "
			+"<foreach item='item' index='index' collection='list' separator=','>"
			+ "(#{item.DelayTimer}, #{item.DayofMonth})" 
			+"</foreach>"
			+"</script>")
	public int setDmDay(List<Map<String, Object>> list); // 일별 DM Insert
	
	@Insert("<script>"
			+"INSERT INTO DM (`DelayTimer`, `DayofWeek`) "
			+ "VALUES "
			+"<foreach item='item' index='index' collection='list' separator=','>"
			+ "(#{item.DelayTimer}, #{item.DayofWeek})" 
			+"</foreach>"
			+"</script>")
	public int setDmWeek(List<Map<String, Object>> list); // 요일별 DM Insert
	
	@Insert("<script>"
			+"INSERT INTO DM (`DelayTimer`, `UniqueCarrier`) "
			+ "VALUES "
			+"<foreach item='item' index='index' collection='list' separator=','>"
			+ "(#{item.DelayTimer}, #{item.UniqueCarrier})" 
			+"</foreach>"
			+"</script>")
	public int setDmCarrier(List<Map<String, Object>> list); // 항공사별 DM Insert
	
	@Select("SELECT COUNT(`reg`) AS COUNT FROM DM WHERE `reg` = date_format(now(), '%Y-%m-%d')")
	public int getDmCnt(); //당일 총 dm insert 개수
	
	@Select("SELECT COUNT(*) AS count FROM  \n" + 
			"(SELECT `DayofMonth` FROM DW GROUP BY `DayofMonth`) AS uc")
	public int dayDmCnt(); //일 총 개수
	
	@Select("SELECT COUNT(*) AS count FROM  \n" + 
			"(SELECT `UniqueCarrier` FROM DW GROUP BY `UniqueCarrier`) AS d")
	public int UCDmCnt(); // 항공사 총 개수
	
	@Select("SELECT COUNT(*) AS count FROM  \n" + 
			"(SELECT `DayofWeek` FROM DW GROUP BY `DayofWeek`) AS w")
	public int weekDmCnt();// 요일 총 개수
}
