package com.fpcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.fpcloud.common.Const;
import com.fpcloud.common.UuidUtill;
import com.fpcloud.entity.AccessInfo;
import com.fpcloud.entity.TelnantInfo;
import com.fpcloud.service.TelnantService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Controller
public class LoginController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    TelnantService telnantService;

    @RequestMapping("/toMainPage")
    public ModelAndView toMainPage(HttpServletRequest request) {
        TelnantInfo userInf = (TelnantInfo) request.getSession().getAttribute(Const.LOGIN_USER);
        String realName = userInf.realName==null||"".equals(userInf.realName)?userInf.userName:userInf.realName;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        modelAndView.addObject("userInf",userInf);
        modelAndView.addObject("realName",realName);
        if("1".equals(userInf.type)){
            modelAndView.setViewName("hyMainPage");
        }
        return  modelAndView;
    }
    @RequestMapping("/toHyMainPage")
    public ModelAndView toHyMainPage(HttpServletRequest request){
        TelnantInfo userInf = (TelnantInfo) request.getSession().getAttribute(Const.LOGIN_USER);
        String realName = userInf.realName==null||"".equals(userInf.realName)?userInf.userName:userInf.realName;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hyMainPage");
        modelAndView.addObject("userInf",userInf);
        modelAndView.addObject("realName",realName);
        return  modelAndView;
    }

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Object uObj = request.getSession().getAttribute(Const.LOGIN_USER);
        if(uObj!=null){
            modelAndView.addObject("isLogin",true);
        }else{
            modelAndView.addObject("isLogin",false);
        }
        logger.error("get index page once ............................................");
        return modelAndView;
    }
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }
    @RequestMapping("/tochangePwd")
    public String tochangePwd() {
        return "changePwd";
    }


    @RequestMapping("/toPortal")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        TelnantInfo userInf = (TelnantInfo) request.getSession().getAttribute(Const.LOGIN_USER);
        Map resultMap = telnantService.getCountInfo(userInf);
        modelAndView.addObject("hyUserNum",resultMap.get("hyUserNum"));
        modelAndView.addObject("importDataNum",resultMap.get("importDataNum"));
        modelAndView.addObject("endDate",resultMap.get("endDate"));
        modelAndView.setViewName("common/portal");
        return modelAndView;
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public JSONObject loginCheck(String userName,String password,HttpServletRequest request, HttpServletResponse response){
        JSONObject resObj = new JSONObject();
        TelnantInfo userInf = telnantService.checkUser(userName,password);
        if(userInf!=null&&userInf.telnantId!=null&&!"".equals(userInf.telnantId)){
            AccessInfo accessInfo = telnantService.getAccessInfoByTelnantId(userInf.telnantId);
            if(accessInfo!=null&&accessInfo.endDate!=null&&accessInfo.endDate.after(new Date())){
                    request.getSession().setAttribute(Const.LOGIN_USER,userInf);
                    request.getSession().setAttribute(Const.TENANT_ID,userInf.telnantId);
                    if("0".equals(userInf.type)){
                        resObj.put("code", "2001");
                    }else{
                        resObj.put("code", "2002");
                    }
            }else{
                resObj.put("code", "403");
            }

        }else{
            resObj.put("code", "401");
        }

        return resObj;
    }
    @RequestMapping("/register")
    @ResponseBody
    public JSONObject register(String userName,String password,String email,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code","200");
        boolean isExsit = telnantService.checkUserName(userName);
        if(isExsit){
            resObj.put("code","403");
            return resObj;
        }
        TelnantInfo registerUser = new TelnantInfo();
        registerUser.userName = userName;
        registerUser.password = password;
        registerUser.email = email;
        registerUser.telnantId = UuidUtill.getIdField();
        registerUser.type="0";
        try {
            telnantService.register(registerUser);
        }catch (Exception e){
            resObj.put("code","500");
            logger.error("register error",registerUser);
        }
        return resObj;
    }

    @RequestMapping("/changePwd")
    @ResponseBody
    public JSONObject changePwd(String oldPassword,String password,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code","200");
        TelnantInfo userInf = (TelnantInfo) request.getSession().getAttribute(Const.LOGIN_USER);
        TelnantInfo telnantInfo = telnantService.checkUser(userInf.userName,oldPassword);
        if(telnantInfo==null){
            resObj.put("code","403");
            return resObj;
        }
        telnantInfo.password = password;
        try {
            telnantService.changePwd(telnantInfo);
        }catch (Exception e){
            resObj.put("code","500");
            logger.error("changePwd error",telnantInfo);
        }
        return resObj;
    }
    @RequestMapping("logOut")
    public String  logOut(HttpServletRequest request){
        request.getSession().setAttribute(Const.LOGIN_USER,null);
        request.getSession().setAttribute(Const.TENANT_ID,null);
        return "login";
    }

    @RequestMapping("/verfyCode")
    public void verfyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strs = "asdfjklqweruioptyghzxcvbnm1234567890一二三四五六七八九零";
        BufferedImage bufferImg = new BufferedImage(80, 40, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferImg.getGraphics();
        graphics.setColor(Color.gray);
        graphics.setFont(new Font("宋体",Font.PLAIN , 24));
        graphics.fillRect(0, 0, 80, 40);
        char codes[] = new char[4];
        Random rand = new Random();
        for(int i=0;i<4;i++){
            int index = rand.nextInt(strs.length());
            Character c = strs.charAt(index);
            codes[i] = c;
            graphics.setColor(Color.blue);
            graphics.drawString(c.toString(), i*20, 30);
        }
        request.getServletContext().setAttribute("code", new String(codes));
        graphics.dispose();
        ImageIO.write(bufferImg, "png", response.getOutputStream());
    }
}

