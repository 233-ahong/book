package com.ah.book.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ah.book.common.core.domain.BaseEntity;

/**
 * 用户信息对象 user_inf
 *
 * @author 陈祥
 * @date 2022-12-25
 */
public class UserInf extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long userInfId;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 密码 */
    private String password;

    /** 用户状态 */
    private Integer userStats;

    /** 头像地址 */
    private String userAvatar;

    /** 手机号 */
    private String mobilePhone;

    /** 邮箱 */
    private String userEmail;

    /** 性别 */
    private String gender;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 用户角色 */
    private String userRole;

    public void setUserInfId(Long userInfId)
    {
        this.userInfId = userInfId;
    }

    public Long getUserInfId()
    {
        return userInfId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setUserStats(Integer userStats)
    {
        this.userStats = userStats;
    }

    public Integer getUserStats()
    {
        return userStats;
    }
    public void setUserAvatar(String userAvatar)
    {
        this.userAvatar = userAvatar;
    }

    public String getUserAvatar()
    {
        return userAvatar;
    }
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }
    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserEmail()
    {
        return userEmail;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
    public void setRegisterTime(Date registerTime)
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime()
    {
        return registerTime;
    }
    public void setModifiedTime(Date modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime()
    {
        return modifiedTime;
    }
    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
    }

    public String getUserRole()
    {
        return userRole;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("userInfId", getUserInfId())
                .append("userName", getUserName())
                .append("nickName", getNickName())
                .append("password", getPassword())
                .append("userStats", getUserStats())
                .append("userAvatar", getUserAvatar())
                .append("mobilePhone", getMobilePhone())
                .append("userEmail", getUserEmail())
                .append("gender", getGender())
                .append("registerTime", getRegisterTime())
                .append("modifiedTime", getModifiedTime())
                .append("userRole", getUserRole())
                .toString();
    }
}
