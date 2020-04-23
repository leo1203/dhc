package cn.com.dhc.epq.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.dhc.epq.bean.Question;

@Repository
public interface QuestionMapper {
	
	/**
	 * 获取测试题
	 * 
	 * @return
	 */
	public List<Map<String,Object>> getQuestion();
	
	/**
	 * 增加测试题
	 * 
	 * @param question
	 * @return
	 */
	public Integer addQuestion(Question question);
	
	/**
	 * 根据类型维度获取问题答案
	 * 
	 * @param Type
	 * @return
	 */
	public List<Map<String,Object>> getAnswerMapByType(String Type);
	
	
}
