package cn.com.dhc.epq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.dhc.epq.bean.Answer;
import cn.com.dhc.epq.bean.AnswerDataView;
import cn.com.dhc.epq.mapper.AnswerMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

import cn.com.dhc.epq.bean.Excel;
import cn.com.dhc.epq.bean.Score;
import cn.com.dhc.epq.bean.vo.PageVo;
import cn.com.dhc.epq.mapper.ScoreMapper;
import cn.com.dhc.epq.utils.ExcelUtils;

@Service
public class ScoreService {

	@Autowired
	private ScoreMapper scoreMapper;
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private Excel excel;

	/**
	 * 添加成绩记录
	 * 
	 * @param score
	 * @return
	 */
	public Integer insertScore(Score score) {
		return scoreMapper.insertScore(score);

	}

	/**
	 * 根据名字获取成绩
	 * 
	 * @param name
	 * @return
	 */
	public List<Score> getScoreByName(String name) {
		return scoreMapper.getScoreByName(name);

	}
	

	/**
	 * 根据时间获取成绩
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PageVo<Score> getScoreByTime(String pageIndex, String pageSize,String startTime,String endTime) {	
		PageVo<Score> pageVo=new PageVo<Score>();
	    pageVo.setPageSize(Integer.parseInt(pageSize));
	    pageVo.setPageIndex(Integer.parseInt(pageIndex));
	    Integer count = scoreMapper.getScoreCountByTime(startTime, endTime);	    
		PageHelper.startPage(Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
		List<Score> scores = scoreMapper.getScoreByTime(startTime, endTime);
		scores.forEach(score->score.setAnswersMap(JSON.parseObject(score.getAnswers())));
		Map<Integer, String> questions = questionService.getQuestion();
		pageVo.setCount(count);
		pageVo.setData(scores);
		pageVo.setQuestions(questions);

		return pageVo;
	}

	public PageVo<AnswerDataView> getAllAnswerByTime(String pageIndex, String pageSize,String startTime,String endTime) {
		PageVo<AnswerDataView> pageVo=new PageVo<AnswerDataView>();
		pageVo.setPageSize(Integer.parseInt(pageSize));
		pageVo.setPageIndex(Integer.parseInt(pageIndex));
		Integer count = scoreMapper.getScoreCountByTime(startTime, endTime);
		PageHelper.startPage(Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
		List<AnswerDataView> answerDataViews = scoreMapper.getAllAnswerByTime(startTime, endTime);
		answerDataViews.forEach(score->score.setAnswersMap(JSON.parseObject(score.getAnswers())));
		Map<Integer, String> questions = questionService.getQuestion();
		pageVo.setCount(count);
		pageVo.setData(answerDataViews);
		pageVo.setQuestions(questions);

		return pageVo;
	}

	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean exportScoreByTime(HttpServletRequest request, HttpServletResponse response, String startTime, String endTime) {
		// 根据时间获取成绩
		List<Score> scores = scoreMapper.getScoreByTime(startTime, endTime);
		// 拼装Excel数据源
		List<List<Object>> datas = new ArrayList<>();
		for (Score score : scores) {
			List<Object> data = new ArrayList<>();
			data.add(score.getName());
			data.add(score.getTel());
			data.add(score.getAge());
			data.add(score.getGender());
			data.add(score.getCreateTime());

			datas.add(data);
		}
		// 导出Excel
		return ExcelUtils.exportExcel(request,response, excel.getFileName(), excel.getSheetName(),
				excel.getTitles(), datas, null);
	}

}
