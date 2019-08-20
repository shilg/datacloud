package com.fpcloud.service;

import com.fpcloud.entity.ImportRecord;
import com.fpcloud.entity.LiveMsg;
import com.fpcloud.entity.PoorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PoorInfoService {
    public Page<PoorInfo> getAllPoorInfo(String tenantId,String h, String i, Pageable pageable);
    public Page<ImportRecord> getAllImportRecord(String tenantId, Pageable pageable);
    public ImportRecord saveImportRecord(ImportRecord importRecord);
    public int saveRowimportDate(String tenatId,String fileId,String b,String c,String d,String e,String f,String g,String h,String i,String j,
                                 String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,
                                 String w,String x,String y,String z,String aa,String ab);
    public void batchSavePoorInfo(List<PoorInfo> poorInfoList);

    public int deleteImportedRecord(String fileId);
    public List<PoorInfo>  poorManageRepository(String tenantId,String ii);
    public boolean checkPoorInfoExistByIi(String tenantId,String ii);
    public Map getTenantIdCardMap(String tenantId);
    public List<PoorInfo>  getPoorInfoById(Long id,String tenantId);

}
