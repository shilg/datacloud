package com.fpcloud.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="live_msg")
public class LiveMsg implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name="type")
    public String type;
    @Column(name="contact")
    public String contact;
    @Column(name="message")
    public String message;
    @Column(name="login_user")
    public String loginUser;

}
