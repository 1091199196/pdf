package com.example.controller;

import com.example.entity.ServiceMaterial;
import com.example.service.ServiceMaterialService;
import com.example.util.ChangePdfUtil;
import com.example.util.ParamS;
import com.example.util.httpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/serviceMaterial")
public class ServiceMaterialController {
    @Autowired
    private ServiceMaterialService serviceMaterialService;

    private static Logger log = LoggerFactory.getLogger(ServiceMaterialController.class);

    @RequestMapping("/queryAll")
    public void queryAllServiceMaterial() {
        List<ServiceMaterial> serviceMaterials = serviceMaterialService.queryAllServiceMaterial();
        int i = 0;
        for (ServiceMaterial serviceMaterial : serviceMaterials) {
            i++;
            log.info("当前正在执行第" + i + "个");
            log.info("剩余" + (serviceMaterials.size() - i) + "个 ");
            if (!serviceMaterial.getDocPath().isEmpty()) {
                String nullPdfPath = "";
                String pdfPath = "";
                String docPath = serviceMaterial.getDocPath();
                String execlPath = serviceMaterial.getImgPath();
                //判断打印空表的
                if (docPath.contains(ParamS.WORD_END_NAME)) {
                    nullPdfPath = ChangePdfUtil.downloadHttpUrl(docPath, "D:\\材料pdf\\test\\", UUID.randomUUID().toString());
                } else if (docPath.contains(ParamS.EXECL_END_NAME)) {
                    nullPdfPath = ChangePdfUtil.downloadHttpUrl(docPath, "D:\\材料pdf\\test\\", UUID.randomUUID().toString());
                }
                //判断打印模板的
                if (execlPath != null && execlPath != "") {
                    if (execlPath.contains(ParamS.WORD_END_NAME)) {
                        pdfPath = ChangePdfUtil.downloadHttpUrl(execlPath, "D:\\材料pdf\\test\\", UUID.randomUUID().toString());
                    } else if (execlPath.contains(ParamS.EXECL_END_NAME)) {
                        pdfPath = ChangePdfUtil.downloadHttpUrl(execlPath, "D:\\材料pdf\\test\\", UUID.randomUUID().toString());
                    }
                }
                try {
                    //null 表
                    String attrOid = "";
                    // 模板
                    String attrOid1 = "";
                    if (nullPdfPath != null && nullPdfPath!="") {
                        attrOid = getAttrOid(nullPdfPath);
                    }
                    // 模板
                    if (pdfPath != null && pdfPath !="") {
                        attrOid1 = getAttrOid(pdfPath);
                    }
                    if (attrOid != null && attrOid != "") {
                        serviceMaterial.setNullTableAttaOid(attrOid);
                    }
                    if (attrOid1 != null && attrOid1 != "") {
                        serviceMaterial.setSampleTableAttaOid(attrOid1);
                    }
                    serviceMaterialService.updateServiceMaterial(serviceMaterial);
                    log.info("保存pdf路径是{},保存的空表attroid是{}，保存模板的attroid是{}", nullPdfPath, attrOid,attrOid1 );
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }
        }
    }

    public static String getAttrOid(String pdfPath) throws FileNotFoundException, UnsupportedEncodingException {
        InputStream is = new FileInputStream(new File(pdfPath));
        String result = new httpUtil().uploadFile("http://192.168.1.37:8080/file/atta/uploadSingleFile.do", UUID.randomUUID().toString() + ".pdf", "", is);
        result = URLDecoder.decode(result, "UTF-8");
        Map<String, Object> relMap = com.alibaba.fastjson.JSON.parseObject(result);
        Map<String, Object> resultMap = (Map<String, Object>) relMap.get("result");
        String attrOid = (String) resultMap.get("oid");
        return attrOid;
    }


}
