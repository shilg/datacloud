package com.fpcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.fpcloud.common.Const;
import com.fpcloud.common.SessionUtill;
import com.fpcloud.common.UuidUtill;
import com.fpcloud.entity.LayPage;
import com.fpcloud.entity.LiveMsg;
import com.fpcloud.entity.PoorInfo;
import com.fpcloud.entity.TelnantInfo;
import com.fpcloud.service.TelnantService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/telnant")
public class TelnantCotroller {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TelnantCotroller.class);

    @Autowired
    TelnantService telnantService;

    @RequestMapping("/toLiveMsgEdit")
    public String toLiveMsg(){
        return "poorInfo/liveMsgEdit";
    }
    @RequestMapping("/toHyAccountList")
    public String toHyAccountList(){return "poorInfo/hyAccountList";}
    @RequestMapping("/toHyAccountEdit")
    public String toHyAccountEdit(){return "poorInfo/hyAccountEdit";}
    @RequestMapping("/saveLiveMsg")
    @ResponseBody
    public JSONObject saveLiveMsg(String type, String contact, String message, HttpServletRequest request, HttpServletResponse response){
        JSONObject resObj = new JSONObject();
        resObj.put("code", "200");
        LiveMsg liveMsg = new LiveMsg();

        try{
            Object uObj = request.getSession().getAttribute(Const.LOGIN_USER);
            if(uObj!=null){
                liveMsg.loginUser=((TelnantInfo)uObj).userName;
            }
            liveMsg.type = type;
            liveMsg.contact = contact;
            liveMsg.message = message;
            telnantService.saveLiveMsg(liveMsg);
            logger.info("",liveMsg);
           return resObj;
        }catch (Exception e){
            e.printStackTrace();
            resObj.put("code", "500");
            return resObj;
        }
    }

    @GetMapping("/hyAccountList")
    @ResponseBody
    public LayPage<TelnantInfo> hyAccountList(HttpServletRequest request, int page, int limit, String key) {

        Page<TelnantInfo> pagedata = null;
        LayPage<TelnantInfo> layPagedata = new LayPage<TelnantInfo>();
        if (limit > 0) {

            String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
            if (tenantId != null && !"".equals(tenantId)) {
                pagedata = telnantService.getAllHyAccount(tenantId,  new PageRequest(page-1, limit));
            }
        }
        if (pagedata != null) {
            layPagedata.setCode(0);
            layPagedata.setCount(pagedata.getTotalElements());
            layPagedata.setData(pagedata.getContent());
        }

        return layPagedata;
    }

    @RequestMapping("/addHyAccount")
    @ResponseBody
    public JSONObject addHyAccount(String id,String userName,String password,String unit,String realName,HttpServletRequest request){

        JSONObject resObj = new JSONObject();
        resObj.put("code","200");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
            return resObj;
        }


        TelnantInfo registerUser = new TelnantInfo();
        if(id==null||"".equals(id)){
            registerUser.id=null;
            boolean isExsit = telnantService.checkHyAccountName(userName,tenantId);
            if(isExsit){
                resObj.put("code","403");
                return resObj;
            }
        }else{
            resObj.put("code","202");
            registerUser.id = Long.parseLong(id);
        }

        registerUser.userName = userName;
        registerUser.password = password;
        registerUser.unit = unit;
        registerUser.realName = realName==null?"":realName;
        registerUser.createTime = new Date();
        registerUser.telnantId = tenantId;
        registerUser.type="1";
        try {
            telnantService.addHyAccount(registerUser);
        }catch (Exception e){
            resObj.put("code","500");
        }
        return resObj;
    }
    @RequestMapping("/delHyAccount")
    @ResponseBody
    public JSONObject delHyAccount(String userName,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code","200");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
            return resObj;
        }
        try{
            telnantService.delHyAccount(tenantId,userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resObj;
    }
}
