package com.fpcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.fpcloud.common.*;
import com.fpcloud.entity.ImportRecord;
import com.fpcloud.entity.LayPage;
import com.fpcloud.entity.PoorInfo;
import com.fpcloud.service.PoorInfoService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/poorInfo")
public class poorInfoController {
    private static final Logger logger = LoggerFactory.getLogger(poorInfoController.class);

    @Autowired
    PoorInfoService poorInfoService;

    @Autowired
    Environment environment;

    @RequestMapping("/toPoorInfoList")
    public String poorListPage() {
        return "poorInfo/poorInfoList";
    }

    @RequestMapping("/toPoorInfoEdit")
    public String memberEditPage() {
        return "poorInfo/poorInfoEdit";
    }

    @RequestMapping("/toImportPage")
    public String toImportPag(){
        return "poorInfo/importRecordList";
    }

    @RequestMapping("/toImportDataEdit")
    public String toImportDateEdit(){
        return "poorInfo/importDataEdit";
    }

    @RequestMapping(value = "/toSerchResult", method = RequestMethod.GET)
    public ModelAndView toSerchResult(String kayWords , HttpServletRequest request) {
        String name="",idcard="", html="";
        Page<PoorInfo> pagedata = null;
        ModelAndView modelAndView = new ModelAndView();
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if (tenantId != null && !"".equals(tenantId)) {
            if (kayWords == null) {
                name = idcard = "";
            } else if (StringUtils.isNumeric(kayWords)) {
                idcard = kayWords;
            } else {
                name = kayWords;
            }
            pagedata = poorInfoService.getAllPoorInfo(tenantId, name, idcard, new PageRequest(0, 15));
            //modelAndView.addObject("poorInfos",pagedata.getContent());
            List<PoorInfo> poorInfos = pagedata.getContent();
            if (poorInfos.size() > 0) {
                for (int i = 0; i < pagedata.getContent().size(); i++) {
                    PoorInfo poorInfo = poorInfos.get(i);
                    html += "<div class=\"tx1\" onclick=\"parent.poorInfDetail('" + poorInfo.id + "')\">\n" +
                            "         <table cellpadding=\"0\" cellspacing=\"0\" class=\"tab1\" >\n" +
                            "             <tr>\n" +
                            "                 <td>\n" +
                            "                     <a href=\"\">" + poorInfo.h + "</a>\n" +
                            "                 </td>\n" +
                            "                 <td colspan=\"3\">" + poorInfo.i + "</td>\n" +
                            "             </tr>\n" +
                            "             <tr>\n" +
                            "                 <td>主要致贫原因：" + poorInfo.w + "</td>\n" +
                            "                 <td>家庭成员数：" + poorInfo.j + "人</td>\n" +
                            "                 <td>联系电话：" + poorInfo.ab + "</td>\n" +
                            "                 <td>" + poorInfo.t + "</td>\n" +
                            "             </tr>\n" +
                            "             <tr>\n" +
                            "                 <td class=\"dz\" colspan=\"4\">所在地：" + poorInfo.b + poorInfo.c + poorInfo.d + poorInfo.e + "</td>\n" +
                            "             </tr>\n" +
                            "         </table>\n" +
                            "     </div>";
                }
            } else {
                html += "<div style=\"width:100%;text-align:center;\"><span style=\"\">未查询到数据</span><div>";
            }
        }
        modelAndView.addObject("html",html);
        modelAndView.setViewName("poorInfo/searchResult");
        return modelAndView;
    }

    @GetMapping("/poorInfoList")
    @ResponseBody
    public LayPage<PoorInfo> memberList(HttpServletRequest request, int page, int limit, String key) {

        Page<PoorInfo> pagedata = null;
        LayPage<PoorInfo> layPagedata = new LayPage<PoorInfo>();
        String name="",idcard="";
        if (limit > 0) {
            if (key == null) {
                name = idcard = "";
            }else if(StringUtils.isNumeric(key)){
                idcard = key;
            }else{
                name = key;
            }
            String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
            if (tenantId != null && !"".equals(tenantId)) {
                pagedata = poorInfoService.getAllPoorInfo(tenantId, name, idcard, new PageRequest(page-1, limit));
            }
        }
        if (pagedata != null) {
            layPagedata.setCode(0);
            layPagedata.setCount(pagedata.getTotalElements());
            layPagedata.setData(pagedata.getContent());
        }

        return layPagedata;
    }
    @GetMapping("/importRecordList")
    @ResponseBody
    public LayPage<ImportRecord> importRecordList(HttpServletRequest request, int page, int limit) {

        Page<ImportRecord> pagedata = null;
        LayPage<ImportRecord> layPagedata = new LayPage<ImportRecord>();
        if (limit > 0) {
            String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
            if (tenantId != null && !"".equals(tenantId)) {
                pagedata = poorInfoService.getAllImportRecord(tenantId, new PageRequest(page-1, limit));
            }
        }
        if (pagedata != null) {
            layPagedata.setCode(0);
            layPagedata.setCount(pagedata.getTotalElements());
            layPagedata.setData(pagedata.getContent());
        }

        return layPagedata;
    }
    @ResponseBody
    @RequestMapping("/getPoorInfoById")
    public JSONObject getPoorInfoById(String id,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code", "0");
        try{
            String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
            if(tenantId==null||"".equals(tenantId)){
                resObj.put("code", "401");
                return resObj;
            }
            Long idLongType = id==null?0:Long.parseLong(id);
            List<PoorInfo> poorInfos = poorInfoService.getPoorInfoById(idLongType,tenantId);
            if(poorInfos.size()>0){
                resObj.put("poorInfo",poorInfos.get(0));
            }
        }catch (Exception e){
            e.printStackTrace();
            resObj.put("code", "500");
        }
        return resObj;
    }
    @ResponseBody
    @RequestMapping("/importDataByExcel")
    public JSONObject importDataByExcel(@RequestParam("file") MultipartFile file, String remark, HttpServletRequest request, HttpServletResponse response){
        JSONObject resObj = new JSONObject();
        resObj.put("code", "0");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
            return resObj;
        }
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String filePath = environment.getProperty("importExcelPath");
        String fileId = UuidUtill.getIdField();
        try {
            UploadUtill.uploadFile(file.getBytes(), filePath, fileId);
            ImportRecord importRecord = new ImportRecord();
            importRecord.importExcel = fileName;
            importRecord.fileId=fileId;
            importRecord.remark = remark;
            resObj.put("data",importRecord);
        } catch (Exception e) {
            e.printStackTrace();
            resObj.put("code", "1");
        }
       // return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
        return resObj;
    }

    @ResponseBody
    @RequestMapping("/deleteImportRecord")
    public JSONObject deleteImportRecord(String fileId,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code", "0");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
            return resObj;
        }
        if(null==fileId||"".equals(fileId)){
            resObj.put("code", "500");
            return resObj;
        }
        int num = poorInfoService.deleteImportedRecord(fileId);
        resObj.put("code", "0");
        resObj.put("num", num);
        return resObj;

    }
    @ResponseBody
    @RequestMapping("/analyzeExcel")
    public JSONObject analyzeExcel(String fileId,String remark,String importExcel,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code", "0");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
            return resObj;
        }
        int importNum = 0;
        try{
            InputStream is = null;
            java.io.File sourcefile = new  java.io.File(environment.getProperty("importExcelPath")+fileId);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook) {
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                importNum =  importExcelInfo(xWb,tenantId,false,fileId);
            }else if(wb instanceof HSSFWorkbook){
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                importNum = importExcelInfo(hWb,tenantId,false,fileId);
            }
            if(importNum==999999){
                resObj.put("code", "999999");
                return resObj;
            }

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            resObj.put("code", "999998");
        }catch (InvalidFormatException e) {
            e.printStackTrace();
            resObj.put("code", "999998");
        } catch (IOException e) {
            e.printStackTrace();
            resObj.put("code", "999997");
        }

        ImportRecord importRecord = new ImportRecord();
        importRecord.fileId = fileId;
        importRecord.remark = remark;
        importRecord.importExcel = importExcel;
        importRecord.importDate=new Date();
        importRecord.importNum=importNum;
        importRecord.tenantId = tenantId;
        poorInfoService.saveImportRecord(importRecord);
        return resObj;
    }

    /***
     *
     * @param wb
     * @param tenantId
     * @return 999999 格式异常  其他表示返回数据条数
     * @throws Exception
     */
    public int importExcelInfo(Workbook wb,String tenantId,boolean isUseAI,String fileId){
        Sheet sheet = wb.getSheetAt(0);//获取第一个Sheet的内容
        int startRowNum = 0;
        int lastRowNum = sheet.getLastRowNum();
        //=====智能预读，格式检查=======
        if(isUseAI) {
            Row firstRow = sheet.getRow(0);
            int firstCellNum = firstRow.getLastCellNum();
            Row secendRow = sheet.getRow(1);
            int secendCellNum = secendRow.getLastCellNum();
            Row thirdRow = sheet.getRow(2);
            int thirdCellNum = thirdRow.getLastCellNum();
            Row fourRow = sheet.getRow(3);
            int fourCellNum = fourRow.getLastCellNum();
            startRowNum = getStartRowNum(firstCellNum, secendCellNum, thirdCellNum, fourCellNum,
                    firstRow, secendRow, thirdRow, fourRow);
        }else{
            startRowNum = 3;
            Row startRow = sheet.getRow(3);
            String idcard = this.getCellValue(8,startRow);
            if(idcard.length()>14) {
                idcard = idcard.substring(0, 15);
                if (!StringUtils.isNumeric(idcard)) {
                    return 999999;
                }
            }
        }
        //====================================================
        try {

            List<PoorInfo> poorInfoList  = new ArrayList<PoorInfo>();
            for (int rowNum = startRowNum; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                //原生sql执行
            /*    poorInfoService.saveRowimportDate(tenantId,fileId,getCellValue(1, row), getCellValue(2, row),getCellValue(3, row),
                        getCellValue(4, row), getCellValue(5, row), getCellValue(6, row), getCellValue(7, row), getCellValue(8, row),
                        getCellValue(9, row), getCellValue(10, row), getCellValue(11, row), getCellValue(12, row), getCellValue(13, row),
                        getCellValue(14, row), getCellValue(15, row), getCellValue(16, row), getCellValue(17, row), getCellValue(18, row),
                        getCellValue(19, row), getCellValue(20, row), getCellValue(21, row), getCellValue(22, row), getCellValue(23, row),
                        getCellValue(24, row), getCellValue(25, row), getCellValue(26, row), getCellValue(27, row)
                        );*/
                //批量插入

                PoorInfo poorInfo = new PoorInfo();
                poorInfo.tenantId = tenantId;poorInfo.fileId=fileId;poorInfo.b=getCellValue(1, row);poorInfo.c=getCellValue(2, row);
                poorInfo.d=getCellValue(3, row);poorInfo.e=getCellValue(4, row);poorInfo.f=getCellValue(5, row);poorInfo.g=getCellValue(6, row);
                poorInfo.h=getCellValue(7, row);poorInfo.i=getCellValue(8, row);poorInfo.j=getCellValue(9, row);poorInfo.k=getCellValue(10, row);
                poorInfo.l=getCellValue(11, row);poorInfo.m=getCellValue(12, row);poorInfo.n=getCellValue(13, row);poorInfo.o=getCellValue(14, row);
                poorInfo.p=getCellValue(15, row);poorInfo.q=getCellValue(16, row);poorInfo.r=getCellValue(17, row);poorInfo.s=getCellValue(18, row);
                poorInfo.t=getCellValue(19, row);poorInfo.u=getCellValue(20, row);poorInfo.v=getCellValue(21, row);poorInfo.w=getCellValue(22, row);
                poorInfo.x=getCellValue(23, row);poorInfo.y=getCellValue(24, row);poorInfo.z=getCellValue(25, row);poorInfo.aa=getCellValue(26, row);
                poorInfo.ab=getCellValue(27, row);
                String iival = getCellValue(8, row);
                if(iival.length()>18){
                    iival = iival.substring(0,18);
                }
                poorInfo.ii=iival.toUpperCase();
                poorInfoList.add(poorInfo);
                //最后一次必须提交
                if(rowNum == lastRowNum){
                    poorInfoService.batchSavePoorInfo(poorInfoList);
                    poorInfoList.clear();
                }
                //够400条做一次提交
                if(poorInfoList.size()>400){
                    poorInfoService.batchSavePoorInfo(poorInfoList);
                    poorInfoList.clear();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return 999999;
        }
        return lastRowNum-startRowNum+1;
    }
    public String getCellValue(int colNum,Row row){
        Cell cell =  row.getCell(colNum);
            return  cell!=null?ExcelUtill.getCellValue(cell):"";
    }
    public int getStartRowNum(int firstCellNum,int secendCellNum,int thirdCellNum,int fourCellNum,
    Row firstRow,Row secendRow,Row thirdRow,Row fourRow) {
        int startRowNum=-1;
        startRowNum = getDataStartRow(firstCellNum, secendCellNum, firstRow, secendRow,0);
        if(startRowNum==-1){
            startRowNum = getDataStartRow(secendCellNum, thirdCellNum, secendRow, thirdRow,1);
        }else if(startRowNum==-1){
            startRowNum = getDataStartRow(thirdCellNum, fourCellNum, thirdRow, fourRow,1);
        }else{
            return 9999;
        }
        return startRowNum;
    }

    private int getDataStartRow(int firstCellNum, int secendCellNum, Row firstRow, Row secendRow,int startRowNum) {
        if(firstCellNum==secendCellNum&&firstCellNum>10){
            String firstidcard = this.getCellValue(8,firstRow);
            String secendidcard = this.getCellValue(8,secendRow);
            if(firstidcard.length()>14){
                firstidcard = firstidcard.substring(0,15);
                if(StringUtils.isNumeric(firstidcard)){
                    return  startRowNum;
                }
            }else if(secendidcard.length()>14){
                secendidcard = secendidcard.substring(0,15);
                if(StringUtils.isNumeric(secendidcard)){
                    return  startRowNum+1;
                }
            }else{
                return -1;
            }
        }
        return -1;
    }
}
