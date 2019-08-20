package com.fpcloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="access_info")
public class AccessInfo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name="telnant_id")
    public String telnantId;
    @Column(name="end_date")
    public Date endDate;
    @Column(name="app_id")
    public String appId;
    @Column(name="update_time")
    public Date updateTime;

}
