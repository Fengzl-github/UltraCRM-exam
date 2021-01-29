package com.cn.exam.service.excel.upload;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

/**
 *@Author fengzhilong
 *@Date 2021/1/29 17:06
 *@Desc
 **/
@Validated
public interface ExcelUploadService {

    void uploadTopic(MultipartFile file,String ghid, String operator);
}
