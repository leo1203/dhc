package cn.com.dhc.roomservice.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会议室场地信息表实体类
 *
 * @author huleikai
 * @create 2019-04-28 14:17
 */
@Entity
@Table(name = "meeting_area")
public class MeetingArea extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "area_id")
    private String areaId;//主键id

    @Column(name = "area_name", nullable = false, length = 50)
    @NotNull(message = "会议室地域信息不能为空")
    @NotEmpty(message = "会议室地域信息不能为空")
    private String areaName;  //场地名称

    @Column(name = "area_address", length = 100)
    @NotNull(message = "会议室地点信息不能为空")
    @NotEmpty(message = "会议室地点信息不能为空")
    private String areaAddress; //场地地址

    @Column(name = "use_type", length = 100)
    @NotNull(message = "会议室类型不能为空")
    @NotEmpty(message = "会议室类型不能为空")
    private String useType; //类型

    @Column(name = "site_area", columnDefinition = "decimal(5,2)")
    private BigDecimal siteArea; //场地面积

    @Column(name = "area_number")
    @NotNull(message = "会议室容纳人数不能为空")
    @NotEmpty(message = "会议室容纳人数不能为空")
    private String areaNumber; //容纳人数

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "open_start_time")
    private Date openStartTime; //开放开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "open_end_time")
    private Date openEndTime; //开放结束时间

    @Column(name = "area_description", length = 500)
    private String areaDescription; //场地描述

    @Column(name = "contact_phone", length = 20)
    private String contactPhone; //联系电话

    @Column(name = "area_status", columnDefinition = "char(2)")
    private char areaStatus; //场地状态 00:停用; 10:启用

    @Column(name = "del_flag", nullable = false, columnDefinition = "boolean")
    private boolean del_flag = false; //逻辑删除标识  0-未删除 1-已删除 默认0

    @Column(name = "room_mark")
    private String roomMark;

    public String getRoomMark() {
        return roomMark;
    }

    public void setRoomMark(String roomMark) {
        this.roomMark = roomMark;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public BigDecimal getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(BigDecimal siteArea) {
        this.siteArea = siteArea;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public Date getOpenStartTime() {
        return openStartTime;
    }

    public void setOpenStartTime(Date openStartTime) {
        this.openStartTime = openStartTime;
    }

    public Date getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(Date openEndTime) {
        this.openEndTime = openEndTime;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public char getAreaStatus() {
        return areaStatus;
    }

    public void setAreaStatus(char areaStatus) {
        this.areaStatus = areaStatus;
    }

    public boolean isDel_flag() {
        return del_flag;
    }

    public void setDel_flag(boolean del_flag) {
        this.del_flag = del_flag;
    }
}
