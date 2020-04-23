package cn.com.dhc.roomservice.controller;

import cn.com.dhc.roomservice.bean.MeetingArea;
import cn.com.dhc.roomservice.bean.MeetingAreaOrder;
import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.service.MailService;
import cn.com.dhc.roomservice.service.MeetingAreaOrderService;
import cn.com.dhc.roomservice.service.MeetingAreaService;
import cn.com.dhc.roomservice.utils.DateUtil;
import cn.com.dhc.roomservice.utils.ResultUtils;
import cn.com.dhc.roomservice.utils.VerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author huleikai
 * @create 2019-04-29 13:57
 */
@RestController
@RequestMapping("/order")
public class MeetingAreaOrderController {

    @Autowired
    private MeetingAreaOrderService meetingAreaOrderService;

    @Autowired
    private MeetingAreaService meetingAreaService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Value("${mail.fromMail.subject}")
    private String subject;

    /**
     * 根据areaOrderId场地订单ID查询订单预约记录
     *
     * @param areaOrderId 场地订单ID
     * @return
     */
    @GetMapping("/findOrderDetailsInfo/{areaOrderId}")
    public ResultBean findOrderDetailsInfo(@PathVariable Integer areaOrderId) {
        if (null == meetingAreaOrderService.findMeetingAreaOrderByAreaOrderId(areaOrderId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_ORDER_NOT_FOUND));
        }
        return ResultUtils.success(meetingAreaOrderService.findMeetingAreaOrderByAreaOrderId(areaOrderId));
    }

    /**
     * 插入一条场地订单记录
     *
     * @param meetingAreaOrder 新增的场地订单对象
     * @return
     */
    @PostMapping("/createRoomOrder")
    public ResultBean createRoomOrder(@Validated MeetingAreaOrder meetingAreaOrder, BindingResult result, @RequestParam(value = "contactEmails",required = false) String contactEmails) {
        if (result.hasErrors()) {
            return ResultUtils.failure(result.getFieldError().getDefaultMessage(), Constants.FAILURE);
        }

        System.out.println(meetingAreaOrder);
        if (!VerifyUtils.isPhoneNum(meetingAreaOrder.getContactPhone())) {
            return ResultUtils.failure(Constants.PHONE_NUM_IS_INCORRECT);
        }
        String startTime = meetingAreaOrder.getReserveDate() + " " + meetingAreaOrder.getReserveStartTime();
        String endTime = meetingAreaOrder.getReserveDate() + " " + meetingAreaOrder.getReserveEndTime();
        Date nowDate = DateUtil.addDate(new Date(), -1, Calendar.MINUTE);
        String currentTime = DateUtil.dateToString(nowDate, "");
        if (!VerifyUtils.compareDate(startTime, endTime)) {
            return ResultUtils.failure(Constants.ORDER_STARTTIME_LESS_ENDTIME);
        }
        if (!VerifyUtils.compareDate(currentTime, startTime)) {
            return ResultUtils.failure(Constants.ORDER_STARTTIME_IS_OUTDATE);
        }
        String areaId = meetingAreaOrder.getAreaId();
        List<MeetingAreaOrder> orderList = meetingAreaOrderService.findMeetingAreaOrderByDate(startTime, endTime, areaId);
        if (!orderList.isEmpty()) {
            return ResultUtils.failure(Constants.ORDER_TIME_IS_CONFLICT);
        }
        /******发送邮件*******/
        if (!StringUtils.isEmpty(contactEmails)) {
            // 根据 areaId查找场地信息
            MeetingArea meetingArea = meetingAreaService.findMeetingAreaByAreaId(meetingAreaOrder.getAreaId());
            // 创建邮件正文
            Context context = new Context();
            context.setVariable("meetingAreaOrder", meetingAreaOrder);
            context.setVariable("areaAddress", meetingArea.getAreaAddress());
            String emailContent = templateEngine.process("emailTemplate", context);
            // 发送邮件
            boolean flag = mailService.sendHtmlMail(contactEmails.split("\\|"), subject, emailContent);
            if (flag) {
                return ResultUtils.success(meetingAreaOrderService.insertMeetingAreaOrder(meetingAreaOrder));
            } else {
                // 发送失败
                return ResultUtils.failure(Constants.MAIL_SEND_FAIL);
            }
        } else {
            return ResultUtils.success(meetingAreaOrderService.insertMeetingAreaOrder(meetingAreaOrder));
        }


    }


    /**
     * 根据areaOrderId场地订单ID删除一条记录
     *
     * @param areaOrderId 场地订单ID
     * @return
     */
    @DeleteMapping("/deleteMeetingAreaOrderByAreaOrderId/{areaOrderId}")
    public ResultBean deleteMeetingAreaOrderByAreaOrderId(@PathVariable Integer areaOrderId) {
        if (null == meetingAreaOrderService.findMeetingAreaOrderByAreaOrderId(areaOrderId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_ORDER_NOT_FOUND));
        }
        return ResultUtils.success(meetingAreaOrderService.deleteMeetingAreaOrderByAreaOrderId(areaOrderId));
    }

    /**
     * 根据 Id更新场地信息
     *
     * @param meetingAreaOrder 要更新的场地订单信息对象
     * @return
     */
    @PutMapping("/updateMeetingAreaOrderById")
    public ResultBean updateMeetingAreaOrderById(MeetingAreaOrder meetingAreaOrder) {
        boolean flag = meetingAreaOrderService.updateMeetingAreaOrderById(meetingAreaOrder);
        if (flag) {
            return ResultUtils.success(meetingAreaOrderService.updateMeetingAreaOrderById(meetingAreaOrder));
        }
        return ResultUtils.failure(Constants.VALUES.get(Constants.MEETING_AREA_ORDER_NOT_FOUND));

    }

    /**
     * 分页查询所有预约订单信息
     *
     * @param searchContext 搜索的手机号或者姓名，不传则表示查询全部
     * @param page          当前页码
     * @param rows          每页显示条数
     * @return
     */
    @PostMapping("/queryMeetingOrderInfo")
    public Map<String, Object> queryMeetingOrderInfo(@RequestParam(value = "searchContext", required = false) String searchContext,
                                                     @NotNull Integer page, @NotNull Integer rows) {
        if (StringUtils.isEmpty(searchContext)) {
            return meetingAreaOrderService.queryMeetingOrderInfo(page, rows);
        }
        if (VerifyUtils.isPhoneNum(searchContext) || VerifyUtils.isNumeric(searchContext)) {
            return meetingAreaOrderService.findMeetingAreaOrderByPhone(searchContext, page, rows);
        }

        return meetingAreaOrderService.findMeetingAreaOrderByName(searchContext, page, rows);
    }


    /**
     * app移动端接口
     *
     * @param searchContext 搜索的手机号或者姓名，不传则表示查询全部
     * @param page          当前页码
     * @param rows          每页显示条数
     * @return
     */
    @PostMapping("/queryOrderInfo")
    public ResultBean queryOrderInfo(@RequestParam(value = "searchContext", required = false) String searchContext,
                                     @NotNull Integer page, @NotNull Integer rows) {
        if (StringUtils.isEmpty(searchContext)) {
            return ResultUtils.success(meetingAreaOrderService.queryMeetingOrderInfo(page, rows));
        }
        if (VerifyUtils.isPhoneNum(searchContext) || VerifyUtils.isNumeric(searchContext)) {
            return ResultUtils.success(meetingAreaOrderService.findMeetingAreaOrderByPhone(searchContext, page, rows));
        }

        return ResultUtils.success(meetingAreaOrderService.findMeetingAreaOrderByName(searchContext, page, rows));
    }
}
