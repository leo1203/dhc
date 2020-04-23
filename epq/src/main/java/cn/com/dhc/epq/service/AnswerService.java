package cn.com.dhc.epq.service;

import cn.com.dhc.epq.bean.Answer;
import cn.com.dhc.epq.bean.AnswerDataView;
import cn.com.dhc.epq.bean.Excel;
import cn.com.dhc.epq.bean.Score;
import cn.com.dhc.epq.bean.vo.PageVo;
import cn.com.dhc.epq.mapper.AnswerMapper;
import cn.com.dhc.epq.mapper.ScoreMapper;
import cn.com.dhc.epq.utils.ExcelUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService {

	@Autowired
	private AnswerMapper answerMapper;

	@Autowired
	private Excel excel;

	/**
	 * 添加答题记录
	 * 
	 * @param answer
	 * @return
	 */
	public Integer insertAnswer(Answer answer) {
		return answerMapper.insertAnswer(answer);

	}

	/**
	 * 根据名字获取成绩
	 * 
	 * @param name
	 * @return
	 */
	public List<Answer> getSAnswerByName(String name) {
		return answerMapper.getSAnswerByName(name);

	}


	public List<AnswerDataView> getAllAnswer() {
		// 获取答题记录
		List<AnswerDataView> allAnswer = answerMapper.getAllAnswer();
		return allAnswer;
	}

	public List<Answer> getAnswerByPhone(String phone) {
		// 获取答题记录
		List<Answer> answer = answerMapper.getAnswerByPhone(phone);
		return answer;
	}


	/**
	 * 导出Excel
	 *
	 * @return
	 */
	public boolean exportAllAnswer(HttpServletRequest request, HttpServletResponse response) {
		// 获取答题记录
		List<AnswerDataView> allAnswer = answerMapper.getAllAnswer();
		// 拼装Excel数据源
		List<List<Object>> dataList = new ArrayList<>();
		for (AnswerDataView dataView : allAnswer) {
			List<Object> data = new ArrayList<>();
			data.add(dataView.getName());
			data.add(dataView.getTel());
			data.add(dataView.getAge());
			data.add(dataView.getGender());
			data.add(dataView.getCreate_time());
			dataList.add(data);
		}
		// 导出Excel
		return ExcelUtils.exportExcel(request,response, excel.getFileName(), excel.getSheetName(),
				excel.getTitles(), dataList, null);
	}

}
