package cn.com.dhc.epq.mapper;

import java.util.List;

import cn.com.dhc.epq.bean.AnswerDataView;
import org.springframework.stereotype.Repository;

import cn.com.dhc.epq.bean.Score;

@Repository
public interface ScoreMapper {
		
	/**
	 *  添加成绩记录
	 *  
	 * @param score
	 * @return
	 */
	public Integer insertScore(Score score);
	
	/**
	 * 根据名字获取成绩
	 * 
	 * @param name
	 * @return
	 */
	public List<Score> getScoreByName(String name);
	
	/**
	 * 获取成绩个数
	 * 
	 * @param endTime 
	 * @param startTime 
	 * @return
	 */
	public Integer getScoreCountByTime(String startTime, String endTime);

	/**
	 * 根据时间获取成绩
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Score> getScoreByTime(String startTime, String endTime);

	public List<AnswerDataView> getAllAnswerByTime(String startTime, String endTime);

}
