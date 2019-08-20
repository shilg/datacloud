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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/excelCompar")
public class ExcelComparController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelComparController.class);

    @Autowired
    PoorInfoService poorInfoService;

    @Autowired
    Environment environment;

    @RequestMapping("/toexcelCompar")
    public String poorListPage() {
        return "poorInfo/excleCompar";
    }


    @ResponseBody
    @RequestMapping("/excleUpload")
    public JSONObject importDataByExcel(@RequestParam("file") MultipartFile file, String idcardCol,String dataStartRow, HttpServletRequest request, HttpServletResponse response){
        JSONObject resObj = new JSONObject();
        int startRowNum=0; int idcardColNum=0;
        resObj.put("code", "0");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
        }
        if(idcardCol==null||"".equals(idcardCol)||dataStartRow==null||"".equals(dataStartRow)){
            resObj.put("code", "500");
            return resObj;
        }else{
            if(StringUtils.isNumeric(dataStartRow)){
                startRowNum = Integer.parseInt(dataStartRow);
            }else{
                resObj.put("code", "501");
                return resObj;
            }
            if(Const.alphabetNumMap.containsKey(idcardCol.toLowerCase())){
            idcardColNum = Const.alphabetNumMap.get(idcardCol.toLowerCase());
            }else{
                resObj.put("code", "501");
                return resObj;
            }
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        String filePath = environment.getProperty("comparExcelPath");
        String fileId = UuidUtill.getIdField();
        try {
            UploadUtill.uploadFile(file.getBytes(), filePath, fileId);
        } catch (Exception e) {
            e.printStackTrace();
            resObj.put("code", "1");
        }
        resObj.put("startRowNum", startRowNum);
        resObj.put("idcardColNum", idcardColNum);
        resObj.put("fileId", fileId);
        resObj.put("suffix", suffix);
        resObj.put("fileName", fileName);

        return resObj;
    }
    @ResponseBody
    @RequestMapping("/analyzeExcel")
    public JSONObject analyzeExcel(String fileId,int startRowNum,String suffix,String fileName,int idcardColNum,HttpServletRequest request){
        JSONObject resObj = new JSONObject();
        resObj.put("code", "0");
        String tenantId = SessionUtill.getSessionStringAttr(request, Const.TENANT_ID);
        if(tenantId==null||"".equals(tenantId)){
            resObj.put("code", "401");
            return resObj;
        }else if(fileId==null||"".equals(fileId)){
            resObj.put("code", "500");
            return resObj;
        }
        int importNum = 0;
        try{
            InputStream is = null;
            java.io.File sourcefile = new  java.io.File(environment.getProperty("comparExcelPath")+fileId);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook) {
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                importNum =  comparExcelInfo(tenantId,xWb,startRowNum,idcardColNum,fileId,suffix);
            }else if(wb instanceof HSSFWorkbook){
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                importNum = comparExcelInfo(tenantId,hWb,startRowNum,idcardColNum,fileId,suffix);
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
        resObj.put("importNum", importNum);
        resObj.put("fileId", fileId);
        resObj.put("fileName", fileName);
        resObj.put("suffix", suffix);
        return resObj;
    }
    public int comparExcelInfo(String tenantId,Workbook wb,int startRowNum,int idcardColNum,String fileId,String suffix){
        Sheet sheet = wb.getSheetAt(0);//获取第一个Sheet的内容
        int lastRowNum = sheet.getLastRowNum();
            Row startRow = sheet.getRow(startRowNum);
            String idcard = ExcelUtill.getCellValue(idcardColNum,startRow);
            if(idcard.length()>14) {
                idcard = idcard.substring(0, 15);
                if (!StringUtils.isNumeric(idcard)) {
                    return 999996;
                }
            }
        try {
            File targetFile = new File(environment.getProperty("comparResultPath"));
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            Map<String,String> idcardAndPoorResonMap = poorInfoService.getTenantIdCardMap(tenantId);
            FileOutputStream out = new FileOutputStream(targetFile+"/"+fileId+"."+suffix);
           //创建工作
            Workbook outWorkBook = new SXSSFWorkbook();
            Sheet outsheet = outWorkBook.createSheet("比对结果");
            int outRowNum=0;
            for (int rowNum = startRowNum; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                String cellvalue = ExcelUtill.getCellValue(idcardColNum, row).toLowerCase();
                if(cellvalue.length()>18){
                    cellvalue = cellvalue.substring(0,18);
                }
                if(idcardAndPoorResonMap.containsKey(cellvalue)){
                    Row outRow = outsheet.createRow(outRowNum);
                    ExcelUtill.copyRow(row,outRow,idcardAndPoorResonMap.get(cellvalue));
                    outRowNum++;
                }
            }
            idcardAndPoorResonMap=null;
            outWorkBook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
            return 999999;
        }
        return lastRowNum-startRowNum+1;
    }

    @GetMapping("/downloadResult")
    public ResponseEntity<byte[]> downloadResult(String fileId,String fileName,String suffix) throws IOException {
        try {
            if(suffix==null){
                    suffix="xls";
            }
            File file=new File(environment.getProperty("comparResultPath")+"/"+fileId+"."+suffix);
            if(!file.exists()){
                suffix="xlsx";
                file=new File(environment.getProperty("comparResultPath")+"/"+fileId+"."+suffix);
            }
            return this.buildResponseEntity(file,fileName+".xlsx");
        }catch (Exception e){
            logger.error("文件下载异常 "+fileId+"."+suffix);
            return null;
        }

    }
    public static ResponseEntity<byte[]> buildResponseEntity(File file,String fileName) throws IOException {
        fileName = "比对结果_"+fileName;
        byte[] body = null;
        //获取文件
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        //设置文件类型+ new String( fileName.getBytes("gb2312"), "ISO8859-1" ) );
        headers.add("Content-Disposition", "attchement;filename=" +  new String( fileName.getBytes("gb2312"), "ISO8859-1" ) );
        //设置Http状态码
        HttpStatus statusCode = HttpStatus.OK;
        //返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }
}
