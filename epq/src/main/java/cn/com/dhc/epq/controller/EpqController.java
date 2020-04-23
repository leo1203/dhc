package cn.com.dhc.epq.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.dhc.epq.bean.Answer;
import cn.com.dhc.epq.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.com.dhc.epq.bean.Question;
import cn.com.dhc.epq.bean.Score;
import cn.com.dhc.epq.bean.vo.ResultBean;
import cn.com.dhc.epq.service.QuestionService;
import cn.com.dhc.epq.service.ScoreService;
import cn.com.dhc.epq.utils.ResultUtils;

@RestController
@RequestMapping("/epqapi/epq")
public class EpqController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private AnswerService answerService;

    /**
     * 获取测试题
     *
     * @return
     */
    @GetMapping("/getQuestion")
    public ResultBean<?> getQuestion() {
        return ResultUtils.success(questionService.getQuestion());
    }

    /**
     * 增加测试题
     *
     * @param question
     * @return
     */
    @PostMapping("/addQuestion")
    public ResultBean<?> addQuestion(Question question) {
        return ResultUtils.success(questionService.addQuestion(question));
    }

//	/**
//	 * 提交测试
//	 * @param answers
//	 * @return
//	 */
//	@PostMapping("/submit")
//	public ResultBean<?> submit(
//			@RequestParam("answers") String answers,
//			@RequestParam("name") String name,
//			@RequestParam("tel") String tel,
//			@RequestParam("gender") String gender,
//			@RequestParam("age") Integer age) {
//		Map<String, Object> mapAnswers = JSON.parseObject(answers);
//		// 获取各维度题号和答案
//		Map<Integer, String> eType = questionService.getAnswerMapByType("E");
//		Map<Integer, String> nType = questionService.getAnswerMapByType("N");
//		Map<Integer, String> pType = questionService.getAnswerMapByType("P");
//		Map<Integer, String> lType = questionService.getAnswerMapByType("L");
//
//		int eScore = 0, nScore = 0, pScore = 0, lScore = 0;// 各个维度计分器
//
//		// 计算分数
//		for (String key : mapAnswers.keySet()) {
//			Integer no = Integer.parseInt(key);
//			String answer = mapAnswers.get(key).toString();
//
//			if (eType.containsKey(no)) {
//				if (answer.equals(eType.get(no))) {
//					eScore++;
//				}
//			} else if (nType.containsKey(no)) {
//				if (answer.equals(nType.get(no))) {
//					nScore++;
//				}
//			} else if (pType.containsKey(no)) {
//				if (answer.equals(pType.get(no))) {
//					pScore++;
//				}
//			} else if (lType.containsKey(no)) {
//				if (answer.equals(lType.get(no))) {
//					lScore++;
//				}
//			}
//		}
//
//		String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
//		Score score = new Score(name, tel, age, gender, eScore, nScore, pScore, lScore, answers, createTime);
//		// 添加成绩记录
//		scoreService.insertScore(score);
//
//		return ResultUtils.success(score);
//	}

    /**
     * 提交测试
     *
     * @param answers
     * @return
     */
    @PostMapping("/submit")
    public ResultBean<?> submit(
            @RequestParam("answers") String answers,
            @RequestParam("name") String name,
            @RequestParam("tel") String tel,
            @RequestParam("gender") String gender,
            @RequestParam("age") Integer age) {
        Map<String, Object> mapAnswers = JSON.parseObject(answers);
        // 获取各维度题号和答案

        Map<Integer, String> aType = questionService.getAnswerMapByType("A");
        Map<Integer, String> bType = questionService.getAnswerMapByType("B");
        Map<Integer, String> cType = questionService.getAnswerMapByType("C");
        Map<Integer, String> dType = questionService.getAnswerMapByType("D");
        Map<Integer, String> eType = questionService.getAnswerMapByType("E");
        Map<Integer, String> fType = questionService.getAnswerMapByType("F");
        Map<Integer, String> gType = questionService.getAnswerMapByType("G");
        Map<Integer, String> hType = questionService.getAnswerMapByType("H");
        Map<Integer, String> iType = questionService.getAnswerMapByType("I");
        Map<Integer, String> jType = questionService.getAnswerMapByType("J");

        int aScore = 0, bScore = 0, cScore = 0, dScore = 0, eScore = 0, fScore = 0, gScore = 0, hScore = 0,
                iScore = 0, jScore = 0;// 各个维度计分器

        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
//        Score score = new Score(name, tel, age, gender, answers, createTime);
//        // 添加成绩记录
//        scoreService.insertScore(score);

        for (String key : mapAnswers.keySet()) {
            Integer no = Integer.parseInt(key);
            String answer = mapAnswers.get(key).toString();
            if (aType.containsKey(no)) {
				aScore = getScore(aScore, answer);
			} else if (bType.containsKey(no)) {
				bScore = getScore(bScore, answer);
			}else if (cType.containsKey(no)) {
				cScore = getScore(cScore, answer);
			}else if (dType.containsKey(no)) {
				dScore = getScore(dScore, answer);
			}else if (eType.containsKey(no)) {
				eScore = getScore(eScore, answer);
			}else if (fType.containsKey(no)) {
				fScore = getScore(fScore, answer);
			}else if (gType.containsKey(no)) {
				gScore = getScore(gScore, answer);
			}else if (hType.containsKey(no)) {
				hScore = getScore(hScore, answer);
			}else if (iType.containsKey(no)) {
				iScore = getScore(iScore, answer);
			}else if (jType.containsKey(no)) {
				jScore = getScore(jScore, answer);
			}
        }

        Score score = new Score(name, tel, age, gender, aScore, bScore, cScore, dScore, eScore, fScore, gScore, hScore,
                iScore, jScore,answers, createTime);
        // 添加成绩记录
        scoreService.insertScore(score);

        Map<String, Object> answerMap = new HashMap<>();
        answerMap.put("A", aScore);
        answerMap.put("B", bScore);
        answerMap.put("C", cScore);
        answerMap.put("D", dScore);
        answerMap.put("E", eScore);
        answerMap.put("F", fScore);
        answerMap.put("G", gScore);
        answerMap.put("H", hScore);
        answerMap.put("I", iScore);
        answerMap.put("J", jScore);
        String dimensionAnswer = JSON.toJSONString(answerMap);
        Answer answer = new Answer(score.getTel(), dimensionAnswer);
        answerService.insertAnswer(answer);
        return ResultUtils.success(score);
    }

	private int getScore(int score, String answer) {
		if ("0".equals(answer)) {
			score++;
		} else if ("1".equals(answer)) {
			score += 2;
		} else if ("2".equals(answer)) {
			score += 3;
		} else if ("3".equals(answer)) {
			score += 4;
		} else if ("4".equals(answer)) {
			score += 5;
		}
		return score;
	}

	/**
     * 根据名字获取成绩
     *
     * @param name
     * @return
     */
    @GetMapping("/getScoreByName")
    public ResultBean<?> getScoreByName(@RequestParam String name) {
        return ResultUtils.success(scoreService.getScoreByName(name));
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
    @PostMapping("/getScoreByTime")
    public ResultBean<?> getScoreByTime(
            @RequestParam("pageIndex") String pageIndex,
            @RequestParam("pageSize") String pageSize,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime) {
        return ResultUtils.success(scoreService.getScoreByTime(pageIndex, pageSize, startTime, endTime));
    }

    @PostMapping("/getAllAnswerByTime")
    public ResultBean<?> getAllAnswerByTime(
            @RequestParam("pageIndex") String pageIndex,
            @RequestParam("pageSize") String pageSize,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime) {
        return ResultUtils.success(scoreService.getAllAnswerByTime(pageIndex, pageSize, startTime, endTime));
    }


    /**
     * 导出Excel
     *
     * @param request
     * @param response
     * @param startTime
     * @param endTime
     */
    @GetMapping(value = "/exportScore")
    public void exportScore(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime) {
        scoreService.exportScoreByTime(request, response, startTime, endTime);
    }

}
