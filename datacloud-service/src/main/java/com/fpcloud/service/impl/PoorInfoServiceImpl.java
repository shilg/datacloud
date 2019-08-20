package com.fpcloud.service.impl;

import com.fpcloud.entity.ImportRecord;
import com.fpcloud.entity.PoorInfo;
import com.fpcloud.repository.ImportRecordRepository;
import com.fpcloud.repository.PoorManageRepository;
import com.fpcloud.service.PoorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoorInfoServiceImpl implements PoorInfoService {

    @Autowired
    PoorManageRepository poorManageRepository;
    @Autowired
    ImportRecordRepository importRecordRepository;

    public Page<PoorInfo> getAllPoorInfo(String tenantId, String h, String i, Pageable pageable){
        if(h!=null&&!"".equals(h)&&i!=null&&!"".equals(i)){
            return poorManageRepository.findAllByTenantIdAndHContainsAndIContains(tenantId,h, i, pageable);
        }else if(h!=null&&!"".equals(h)){
            return poorManageRepository.findAllByTenantIdAndHContains(tenantId,h, pageable);
        }else if(i!=null&&!"".equals(i)){
            return poorManageRepository.findAllByTenantIdAndIContains(tenantId, i, pageable);
        }
        return poorManageRepository.findAllByTenantId(tenantId,pageable);
    }

    @Override
    public Page<ImportRecord> getAllImportRecord(String tenantId, Pageable pageable) {
        return importRecordRepository.findAllByTenantId(tenantId,pageable);
    }

    @Override
    public ImportRecord saveImportRecord(ImportRecord importRecord){
        return importRecordRepository.save(importRecord);
    }

    public int saveRowimportDate(String tenatId,String fileId,String b,String c,String d,String e,String f,String g,String h,String i,String j,
                                 String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,
                                 String w,String x,String y,String z,String aa,String ab){
        return poorManageRepository.insertPoorInfo(tenatId, fileId, b, c, d, e, f, g, h, i, j,
                 k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa, ab);
    }

    @Override
    public void batchSavePoorInfo(List<PoorInfo> poorInfoList) {
         poorManageRepository.save(poorInfoList);
    }

    @Transactional
    @Override
    public int deleteImportedRecord(String fileId){
        try {
            int result = importRecordRepository.deleteImportRecordByFileId(fileId);
            if(result>0){
                return poorManageRepository.deletePoorInfoByFileId(fileId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<PoorInfo>  poorManageRepository(String tenantId,String ii){
        return poorManageRepository.findPoorInfoByTenantIdAndIi(tenantId,ii);
    }
    @Override
    public boolean checkPoorInfoExistByIi(String tenantId,String ii){
        return poorManageRepository.findPoorInfoByTenantIdAndIi(tenantId,ii).size()>0;
    }
    public Map getTenantIdCardMap(String tenantId){
        System.gc();
        Long num = poorManageRepository.countPoorInfoByTenantId(tenantId);
        Map<String,String> idcardMap = new HashMap<String,String>();
        for(int i=0;num>0;i++) {
            Pageable pageable = new PageRequest(i, 5000);
            Page<PoorInfo>  pagedata =  poorManageRepository.findAllByTenantId(tenantId,pageable);
            for (int j = 0; j < pagedata.getContent().size(); j++) {
                PoorInfo poorInfo = pagedata.getContent().get(j);
                idcardMap.put(poorInfo.ii,poorInfo.t);
            }
            num-=5000;
        }
        return idcardMap;
    }
    public List<PoorInfo>  getPoorInfoById(Long id,String tenantId){
        return poorManageRepository.findPoorInfoByIdAndTenantId(id,tenantId);
    }
}
