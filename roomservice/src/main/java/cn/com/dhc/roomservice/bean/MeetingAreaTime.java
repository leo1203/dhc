package cn.com.dhc.roomservice.bean;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meeting_area_time")
public class MeetingAreaTime extends BaseEntity{


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "area_time_id")
    private String areaTimeId;//场地时间ID

    @Column(name = "area_id", nullable = true, length = 111)
    private Integer areaId;//场地ID

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "area_date", nullable = true, length = 111)
    private Date areaDate;//日期


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "area_start_ime ", nullable = true, length = 111)
    private Date areaStartTime;//开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "area_end_time", nullable = true, length = 111)
    private Date areaEndTime;//结束时间

    @Column(name = "area_week", nullable = true, length = 111)
    private String areaWeek;//星期

    @Column(name = "time_status", nullable = true, length = 111)
    private char timeStatus;//时间状态（10：已预订 20：不开放）

    @Column(name = "rel_project_id ", nullable = true, length = 111)
    private Integer relProjectId;//项目ID

    @Column(name = "del_flag", nullable = true, length = 111)
    private boolean delFlag = false;//逻辑删除标识  0-未删除 1-已删除 默认0


    public String getAreaTimeId() {
        return areaTimeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public Date getAreaDate() {
        return areaDate;
    }

    public Date getAreaStartTime() {
        return areaStartTime;
    }

    public Date getAreaEndTime() {
        return areaEndTime;
    }

    public String getAreaWeek() {
        return areaWeek;
    }

    public char getTimeStatus() {
        return timeStatus;
    }

    public Integer getRelProjectId() {
        return relProjectId;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setAreaTimeId(String areaTimeId) {
        this.areaTimeId = areaTimeId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public void setAreaDate(Date areaDate) {
        this.areaDate = areaDate;
    }

    public void setAreaStartTime(Date areaStartTime) {
        this.areaStartTime = areaStartTime;
    }

    public void setAreaEndTime(Date areaEndTime) {
        this.areaEndTime = areaEndTime;
    }

    public void setAreaWeek(String areaWeek) {
        this.areaWeek = areaWeek;
    }

    public void setTimeStatus(char timeStatus) {
        this.timeStatus = timeStatus;
    }

    public void setRelProjectId(Integer relProjectId) {
        this.relProjectId = relProjectId;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }
}
