package com.fpcloud.service.impl;

import com.fpcloud.common.Const;
import com.fpcloud.entity.AccessInfo;
import com.fpcloud.entity.LiveMsg;
import com.fpcloud.entity.TelnantInfo;
import com.fpcloud.repository.AccessInfoRepository;
import com.fpcloud.repository.LiveMsgRepository;
import com.fpcloud.repository.PoorManageRepository;
import com.fpcloud.repository.TelnantRepository;
import com.fpcloud.service.TelnantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TelnantServiceImpl implements TelnantService {

    @Autowired
    TelnantRepository telnantRepository;
    @Autowired
    AccessInfoRepository accessInfoRepository;
    @Autowired
    LiveMsgRepository liveMsgRepository;
    @Autowired
    PoorManageRepository poorManageRepository;

    @Override
    public TelnantInfo checkUser(String userName, String password) {
        return  telnantRepository.findTelnantInfoByUserNameAndPassword(userName,password);
    }
    @Override
    public AccessInfo getAccessInfoByTelnantId(String telnantId){
        return accessInfoRepository.findAccessInfoByTelnantId(telnantId);
    }
    @Override
    public LiveMsg saveLiveMsg(LiveMsg liveMsg){
        return liveMsgRepository.save(liveMsg);
    }
    @Override
    public boolean checkUserName(String userName){
        return  telnantRepository.findTelnantInfoByUserName(userName)!=null;
    }
    public boolean checkHyAccountName(String userName,String tenantId){
        return  telnantRepository.findTelnantInfoByUserNameAndTelnantId(userName,tenantId)!=null;
    }
    @Override
    public boolean register(TelnantInfo telnantInfo){
        try{
        telnantInfo =  telnantRepository.save(telnantInfo);
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.appId="1";
        accessInfo.telnantId=telnantInfo.telnantId;
        long time = System.currentTimeMillis();
        accessInfo.updateTime=new Date(time);
        Date date = new Date(time+ Const.TRY_USE_DAY);
        accessInfo.endDate= date;
        accessInfoRepository.save(accessInfo);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean addHyAccount(TelnantInfo telnantInfo){
        try{
                telnantInfo =  telnantRepository.save(telnantInfo);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return telnantInfo!=null;
    }
    @Override
    public Page<TelnantInfo> getAllHyAccount(String tenantId, Pageable pageable) {
        return telnantRepository.findTelnantInfoByTelnantIdAndType(tenantId,"1",pageable);
    }
    @Override
    public void delHyAccount(String tenantId, String userName) {
        TelnantInfo telnantInfo = telnantRepository.findTelnantInfoByUserNameAndTelnantId(userName,tenantId);
         telnantRepository.delete(telnantInfo.id);
    }

    @Override
    public Map getCountInfo(TelnantInfo userInf){
        Map resultMap = new HashMap();
        Long hyUserNum = telnantRepository.countTelnantInfoByTelnantIdAndType(userInf.telnantId,"1");
        Long importDataNum  = poorManageRepository.countPoorInfoByTenantId(userInf.telnantId);
        AccessInfo accessInfo = accessInfoRepository.findAccessInfoByTelnantId(userInf.telnantId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        resultMap.put("hyUserNum",hyUserNum);
        resultMap.put("importDataNum",importDataNum);
        resultMap.put("endDate",simpleDateFormat.format(accessInfo.endDate));
        return resultMap;
    }
    @Override
    public void changePwd(TelnantInfo telnantInfo){
        int result = telnantRepository.updateTelnantInfo(telnantInfo.password,telnantInfo.id);
    }
}
