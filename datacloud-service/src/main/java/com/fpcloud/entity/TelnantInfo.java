package com.fpcloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "telnant_info")
public class TelnantInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name="telnant_id")
    public String telnantId;
    @Column(name="user_name")
    public String userName;
    @Column(name="real_name")
    public String realName;
    @Column(name="password")
    public String password;
    @Column(name="create_time")
    @Temporal(TemporalType.DATE)
    public Date createTime;
    @Column(name="unit")
    public String unit;
    @Column(name="birthday")
    public String birthday;
    @Column(name="sex")
    public String sex;
    @Column(name="phone")
    public String phone;
    @Column(name="email")
    public String email;
    @Column(name="qq")
    public String qq;
    @Column(name="active")
    public int  active;
    @Column(name="remark")
    public String remark;
    @Column(name="type")
    public String type;

}
