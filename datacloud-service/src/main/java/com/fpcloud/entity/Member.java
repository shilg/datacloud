package com.fpcloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "member_info")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name="car_id")
    public String  carId;
    @Column(name="is_use_password")
    public String  isUsePassword;
    @Column
    public String  pssword;
    @Column
    public String name;
    @Column(name="birthday")
    public Date birthday;
    @Column
    public String sex;
    @Column
    public String level;
    @Column(name="is_never_end")
    public String isNeverEnd;
    @Column(name="end_date")
    public Date endDate;
    @Column(name="main_id")
    public Long mainId;
    @Column
    public String phone;
    @Column
    public String qq;
    @Column(name="create_date")
    public Date createDate;
    @Column
    public String remark;
    @Column(name="data_id")
    public String dataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getIsUsePassword() {
        return isUsePassword;
    }

    public void setIsUsePassword(String isUsePassword) {
        this.isUsePassword = isUsePassword;
    }

    public String getPssword() {
        return pssword;
    }

    public void setPssword(String pssword) {
        this.pssword = pssword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsNeverEnd() {
        return isNeverEnd;
    }

    public void setIsNeverEnd(String isNeverEnd) {
        this.isNeverEnd = isNeverEnd;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
