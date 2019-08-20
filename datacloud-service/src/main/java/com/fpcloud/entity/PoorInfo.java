package com.fpcloud.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "poor_info")
public class PoorInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name="tenant_id")
    public String tenantId;
    @Column(name="file_id")
    public String fileId;
    @Column
    public String b;
    @Column
    public String c;
    @Column
    public String d;
    @Column
    public String e;
    @Column
    public String f;
    @Column
    public String g;
    @Column
    public String h;
    @Column
    public String i;
    @Column
    public String j;
    @Column
    public String k;
    @Column
    public String l;
    @Column
    public String m;
    @Column
    public String n;
    @Column
    public String o;
    @Column
    public String p;
    @Column
    public String q;
    @Column
    public String r;
    @Column
    public String s;
    @Column
    public String t;
    @Column
    public String u;
    @Column
    public String v;
    @Column
    public String w;
    @Column
    public String x;
    @Column
    public String y;
    @Column
    public String z;
    @Column
    public String aa;
    @Column
    public String ab;
    @Column(name="ii")
    public String ii;

}
