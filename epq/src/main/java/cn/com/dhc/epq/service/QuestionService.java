package cn.com.dhc.epq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dhc.epq.bean.Question;
import cn.com.dhc.epq.mapper.QuestionMapper;

@Service
public class QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	/**
	 * 获取测试题
	 * 
	 * @return
	 */
	public Map<Integer, String> getQuestion() {
		return getBaseMap(questionMapper.getQuestion());

	}

	/**
	 * 增加测试题
	 * 
	 * @param question
	 * @return
	 */
	public Integer addQuestion(Question question) {
		return questionMapper.addQuestion(question);

	}

	/**
	 *  根据类型维度获取问题答案
	 *  
	 * @param Type
	 * @return
	 */
	public Map<Integer, String> getAnswerMapByType(String Type) {
		return getBaseMap(questionMapper.getAnswerMapByType(Type));

	}

	/**
	 * Map转换
	 * 
	 * @param listAnswerMap
	 * @return
	 */
	private Map<Integer, String> getBaseMap(List<Map<String, Object>> listAnswerMap) {
		Map<Integer, String> baseMap = new HashMap<>();
		for (Map<String, Object> map : listAnswerMap) {
			Integer no = null;
			String content = null;
			for (Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				if ("no".equals(key)) {
					no = (Integer) entry.getValue();
				} else if ("content".equals(key)) {
					content = (String) entry.getValue();
				}
			}
			baseMap.put(no, content);
		}
		return baseMap;

	}

}
