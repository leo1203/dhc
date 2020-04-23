package cn.com.dhc.roomservice.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meeting_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id")
    private String userId;//用户id

    @Column(name = "user_name", nullable = false, length = 40)
    private String userName;//姓名

    @Column(name = "nickname", nullable = true, length = 60)
    private String nickName;//昵称

    @Column(name = "password", nullable = false, length = 40)
    private String password;//密码

    @Column(name = "gender", length = 1)
    private String gender;//性别： 0女 1男 2保密

    @Column(name = "mobile", nullable = false, length = 11, unique = true)
    private String mobile;//手机号

    @Column(name = "email", nullable = true, length = 20)
    private String email;//电子邮件

    @Column(name = "card_type", nullable = true, length = 2)
    private String cardType;//证件类型 0：身份证

    @Column(name = "card_number", nullable = true, length = 20)
    private String cardNumber;//证件号码

    @Column(name = "head_portrait", nullable = true, length = 50)
    private String headPortrait;//头像

    @Column(name = "detail_address", nullable = true, length = 50)
    private String detailAddress;//详细地址

    @Column(name = "del_flag", nullable = true, length = 2)
    private boolean delFlag = false;//逻辑删除标识  0-未删除 1-已删除 默认0
    /**
     * 该用户所属角色
     */
    @Column(name = "role_id", nullable = false)
    @NotNull(message = "请确认该用户所属角色")
    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickname() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public boolean getDelFlag() {
        return delFlag;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNickname(String nickname) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }
}


