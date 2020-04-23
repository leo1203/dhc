package cn.com.dhc.epq.mapper;

import cn.com.dhc.epq.bean.Answer;
import cn.com.dhc.epq.bean.AnswerDataView;
import cn.com.dhc.epq.bean.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerMapper {
		
	/**
	 *  添加答题记录
	 *  
	 * @param answer
	 * @return
	 */
	public Integer insertAnswer(Answer answer);
	
	/**
	 * 根据名字获取成绩
	 * 
	 * @param name
	 * @return
	 */
	public List<Answer> getSAnswerByName(String name);
	

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public List<AnswerDataView> getAllAnswer();


	public List<Answer> getAnswerByPhone(String phone);

}
