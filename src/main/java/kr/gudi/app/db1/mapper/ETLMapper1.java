package kr.gudi.app.db1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultContext;

@Mapper
public interface ETLMapper1 {
	
	@Select("select 1")
	public Integer test();
	
	@Select("SELECT COUNT(`Year`) AS count FROM ontime;")
	public Integer count();
	
	@Select("select * from ontime limit ${limit},10000")
	public List<Map<String, Object>> getOntime(int limit);
	

	
}
