package com.fpcloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "import_record")
public class ImportRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name="tenant_id")
    public String tenantId;
    @Column(name="file_id")
    public String fileId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="import_date")
    public Date importDate;
    @Column(name="import_excel")
    public String importExcel;
    @Column(name="import_num")
    public int  importNum;
    @Column
    public String remark;

}
