package com.fpcloud.service;

import com.fpcloud.entity.AccessInfo;
import com.fpcloud.entity.LiveMsg;
import com.fpcloud.entity.TelnantInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TelnantService {
    public TelnantInfo checkUser(String userName,String password);
    public AccessInfo getAccessInfoByTelnantId(String telnantId);
    public LiveMsg saveLiveMsg(LiveMsg liveMsg);
    public boolean checkUserName(String userName);
    public boolean register(TelnantInfo telnantInfo);
    public boolean addHyAccount(TelnantInfo telnantInfo);
    public Page<TelnantInfo> getAllHyAccount(String tenantId, Pageable pageable);
    public boolean checkHyAccountName(String userName,String tenantId);
    public void delHyAccount(String tenantId, String userName);

    public Map getCountInfo(TelnantInfo userInf);

    public void changePwd(TelnantInfo telnantInfo);
}
