package com.controller;

import com.response.ServerResponse;
import com.response.UploadResponse;
import com.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${path.pic}")
    String hostName;

    @RequestMapping("/upload/pic")
    public @ResponseBody UploadResponse uploadPic(MultipartFile  file) throws IOException {
        if (file==null){
            throw  new RuntimeException("上传图片失败");
        }
        //文件名
        String str= UUID.randomUUID().toString();
        String suff = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
        String newPath = str+"."+suff;

        file.transferTo(new File(hostName+newPath));

        return new UploadResponse(0,"成功","/static/"+newPath);
    }
}
