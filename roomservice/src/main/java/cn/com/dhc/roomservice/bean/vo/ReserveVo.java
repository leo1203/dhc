package cn.com.dhc.roomservice.bean.vo;

public class ReserveVo {
    private String reserveDate;
    private String reserveStartTime;
    private String reserveEndTime;
    private String contactName;
    private String contactPhone;
    private String areaOrderStatus;
    private String areAddress;
    private String areaName;
    private String areaNumber;
    private String useType;
    private Integer areaOrderId;

    public Integer getAreaOrderId() {
        return areaOrderId;
    }

    public void setAreaOrderId(Integer areaOrderId) {
        this.areaOrderId = areaOrderId;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAreaOrderStatus() {
        return areaOrderStatus;
    }

    public void setAreaOrderStatus(String areaOrderStatus) {
        this.areaOrderStatus = areaOrderStatus;
    }

    public String getAreAddress() {
        return areAddress;
    }

    public void setAreAddress(String areAddress) {
        this.areAddress = areAddress;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }
}
