package cn.com.dhc.roomservice.service;

import cn.com.dhc.roomservice.bean.MeetingArea;
import cn.com.dhc.roomservice.bean.MeetingAreaOrder;
import cn.com.dhc.roomservice.bean.vo.ReserveVo;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.repository.MeetingAreaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author huleikai
 * @create 2019-04-29 13:59
 */
@Service
public class MeetingAreaOrderService {

    @Autowired
    private MeetingAreaOrderRepository meetingAreaOrderRepository;

    /**
     * 插入一条场地订单记录
     *
     * @param meetingAreaOrder 新增的场地订单对象
     * @return
     */

    public MeetingAreaOrder insertMeetingAreaOrder(MeetingAreaOrder meetingAreaOrder) {
        meetingAreaOrder.setCreateTime(new Date(System.currentTimeMillis()));
        return meetingAreaOrderRepository.save(meetingAreaOrder);
    }

    /**
     * 根据areaOrderId场地订单ID查询订单预约记录
     *
     * @param areaOrderId 场地订单ID
     * @return
     */
    public MeetingAreaOrder findMeetingAreaOrderByAreaOrderId(Integer areaOrderId) {
        if (null == meetingAreaOrderRepository.findMeetingAreaOrderByAreaOrderId(areaOrderId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_ORDER_NOT_FOUND));
        }
        return meetingAreaOrderRepository.findMeetingAreaOrderByAreaOrderId(areaOrderId);
    }

    /**
     * 根据用户手机号查询订单预约记录
     *
     * @param phone 用户手机号
     * @return
     */
    public Map<String, Object> findMeetingAreaOrderByPhone(String phone, Integer page, Integer rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        Page<MeetingAreaOrder> allPage = meetingAreaOrderRepository.findMeetingAreaOrdersByPhone(phone, pageable);
        List<MeetingAreaOrder> list = allPage.getContent();
        List<ReserveVo> vos = new ArrayList<>();
        for (MeetingAreaOrder order : list) {
            MeetingArea area = areaService.findMeetingAreaByAreaId(order.getAreaId());
            vos.add(processVo(area, order));
        }
        long total = allPage.getTotalElements();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", vos);
        return map;
    }

    /**
     * 根据用户名查询订单预约记录
     *
     * @param contactName 用户手机号
     * @return
     */
    public Map<String, Object> findMeetingAreaOrderByName(String contactName, Integer page, Integer rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        Page<MeetingAreaOrder> allPage = meetingAreaOrderRepository.findMeetingAreaOrdersByName(contactName, pageable);
        List<MeetingAreaOrder> list = allPage.getContent();
        List<ReserveVo> vos = new ArrayList<>();
        for (MeetingAreaOrder order : list) {
            MeetingArea area = areaService.findMeetingAreaByAreaId(order.getAreaId());
            vos.add(processVo(area, order));
        }
        long total = allPage.getTotalElements();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", vos);
        return map;
    }

    /**
     * 根据areaOrderId场地订单ID删除一条记录
     *
     * @param areaOrderId 场地订单ID
     * @return
     */
    public int deleteMeetingAreaOrderByAreaOrderId(Integer areaOrderId) {

        if (null == meetingAreaOrderRepository.findMeetingAreaOrderByAreaOrderId(areaOrderId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_ORDER_NOT_FOUND));
        }
        return meetingAreaOrderRepository.deleteMeetingAreaOrderByAreaOrderId(areaOrderId);
    }

    /**
     * 根据 Id更新场地信息
     *
     * @param meetingAreaOrder 要更新的场地信息对象
     * @return
     */
    public boolean updateMeetingAreaOrderById(MeetingAreaOrder meetingAreaOrder) {
        boolean flag = meetingAreaOrderRepository.existsById(meetingAreaOrder.getAreaOrderId());
        if (flag) {
            //存在则更新
            meetingAreaOrder.setUpdateTime(new Date(System.currentTimeMillis()));
            meetingAreaOrderRepository.saveAndFlush(meetingAreaOrder);
            return true;
        }
        return false;
    }

    @Autowired
    private MeetingAreaService areaService;

    /**
     * 分页查询方法
     *
     * @param page 当前页数
     * @param rows 当前页显示的条数
     * @return
     */
    public Map<String, Object> queryMeetingOrderInfo(Integer page, Integer rows) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "areaOrderStatus"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "reserveDate"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "reserveStartTime"));
        Pageable pageable = PageRequest.of(page - 1, rows, Sort.by(orders));
        Page<MeetingAreaOrder> allPage = meetingAreaOrderRepository.findAll(pageable);
        List<MeetingAreaOrder> list = allPage.getContent();

        List<ReserveVo> vos = new ArrayList<>();
        for (MeetingAreaOrder order : list) {
            MeetingArea area = areaService.findMeetingAreaByAreaId(order.getAreaId());
            vos.add(processVo(area, order));
        }
        long total = allPage.getTotalElements();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", vos);
        return map;
    }

    private ReserveVo processVo(MeetingArea meetingArea, MeetingAreaOrder areaOrder) {
        ReserveVo vo = new ReserveVo();
        vo.setAreaOrderId(areaOrder.getAreaOrderId());
        vo.setAreAddress(meetingArea.getAreaAddress());
        vo.setAreaName(meetingArea.getAreaName());
        vo.setAreaNumber(meetingArea.getAreaNumber());
        vo.setUseType(meetingArea.getUseType());
        vo.setAreaOrderStatus(String.valueOf(areaOrder.getAreaOrderStatus()));
        vo.setContactName(areaOrder.getContactName());
        vo.setContactPhone(areaOrder.getContactPhone());
        vo.setReserveDate(areaOrder.getReserveDate());
        vo.setReserveEndTime(areaOrder.getReserveEndTime());
        vo.setReserveStartTime(areaOrder.getReserveStartTime());
        return vo;
    }

    /**
     * 定时刷新会议室使用状态信息
     *
     * @return
     */
    @Scheduled(cron = "0 00,30 7-23 * * *")
    public int updateMeetingAreaStatus() {
        return meetingAreaOrderRepository.updateMeetingAreaStatus();
    }

    /**
     * 查询指定预约时间内的所有预约订单
     *
     * @param startTime 预约起始时间
     * @param endTime   预约结束时间
     * @return
     */
    public List<MeetingAreaOrder> findMeetingAreaOrderByDate(String startTime, String endTime, String areaId) {
        List<MeetingAreaOrder> orders = meetingAreaOrderRepository.findMeetingAreaOrderByDate(startTime, endTime, areaId);
        return orders;
    }
}
