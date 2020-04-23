package cn.com.dhc.roomservice.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 会议室场地预定订单表实体类
 *
 * @author huleikai
 * @create 2019-04-29 11:45
 */
@Entity
@Table(name = "meeting_area_order")
public class MeetingAreaOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_order_id")
    private Integer areaOrderId;//主键id

    @Column(name = "area_id")
    private String areaId; //场地ID

    @Column(name = "contact_name", length = 20)
    @NotNull(message = "联系人姓名不能为空")
    @NotEmpty(message = "联系人姓名不能为Empty")
    private String contactName; //预定联系人

    @Column(name = "contact_phone", length = 11)
    @NotNull(message = "联系电话不能为空")
    @NotEmpty(message = "联系电话不能为Empty")
    //正则表达式：验证手机号
    private String contactPhone; //联系电话

    @Column(name = "meeting_topic", length = 100)
    private String meetingTopic; //会议主题

    @Column(name = "join_number")
    private int joinNumber; //参会人数

    @Column(name = "reserve_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "借用日期不能为空")
    private String reserveDate; //预订日期

    @Column(name = "reserve_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "预订开始时间不能为空")
    private String reserveStartTime; //预订开始时间

    @Column(name = "reserve_end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "预订结束时间不能为空")
    private String reserveEndTime; //预订结束时间

    @Column(name = "use_type", length = 100)
    private String useType; //场地用途类型

    @Column(name = "confirm_user", length = 32)
    private String confirmUser; //审核人

    @Column(name = "confirm_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime; //审核时间

    @Column(name = "area_order_status")
    private int areaOrderStatus; //场地订单状态 10:已预约 20:已使用

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }


    public String getContactName() {
        return contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public int getJoinNumber() {
        return joinNumber;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(String reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public String getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public void setAreaOrderStatus(int areaOrderStatus) {
        this.areaOrderStatus = areaOrderStatus;
    }

    public String getUseType() {
        return useType;
    }

    public String getConfirmUser() {
        return confirmUser;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public int getAreaOrderStatus() {
        return areaOrderStatus;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public void setJoinNumber(int joinNumber) {
        this.joinNumber = joinNumber;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public void setConfirmUser(String confirmUser) {
        this.confirmUser = confirmUser;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public void setAreaOrderStatus(char areaOrderStatus) {
        this.areaOrderStatus = areaOrderStatus;
    }


    public Integer getAreaOrderId() {
        return areaOrderId;
    }

    public void setAreaOrderId(Integer areaOrderId) {
        this.areaOrderId = areaOrderId;
    }

    @Override
    public String toString() {
        return "MeetingAreaOrder{" +
                "areaOrderId=" + areaOrderId +
                ", areaId='" + areaId + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", meetingTopic='" + meetingTopic + '\'' +
                ", joinNumber=" + joinNumber +
                ", reserveDate='" + reserveDate + '\'' +
                ", reserveStartTime='" + reserveStartTime + '\'' +
                ", reserveEndTime='" + reserveEndTime + '\'' +
                ", useType='" + useType + '\'' +
                ", confirmUser='" + confirmUser + '\'' +
                ", confirmTime=" + confirmTime +
                ", areaOrderStatus=" + areaOrderStatus +
                '}';
    }
}
