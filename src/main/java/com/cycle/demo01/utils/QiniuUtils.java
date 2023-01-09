package com.cycle.demo01.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.storage.UploadManager;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



@Component
public class QiniuUtils {
    public static final String url = "http://ro7jh3iaw.hd-bkt.clouddn.com";

    public boolean upload(MultipartFile file,String fileName){
        Configuration cfg = new Configuration(Region.huadong());
        UploadManager uploadManager = new UploadManager(cfg);
        String bucket = "clairecycle";
        try{
            byte[] uploadBytes =file.getBytes();
            String accessSecretKey = "zBKDqXsTxONPg1dwWszhr-aYaojPRX9OwSBkYwFp";
            String accessKey = "AgtKQLqaU4MBtgv3xJdC-EjXEG0xHfNSr0kHaatj";
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(),DefaultPutRet.class);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
