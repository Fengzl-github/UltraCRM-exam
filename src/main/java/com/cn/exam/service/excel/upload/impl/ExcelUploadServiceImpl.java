package com.cn.exam.service.excel.upload.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONObject;
import com.cn.common.utils.DateTime;
import com.cn.common.utils.MyString;
import com.cn.exam.dao.exam.ExamTopicDao;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.service.excel.upload.ExcelUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author fengzhilong
 *@Date 2021/1/29 17:08
 *@Desc
 **/
@Service
@Slf4j
public class ExcelUploadServiceImpl implements ExcelUploadService {

    @Resource
    private ExamTopicDao examTopicDao;

    @Override
    public void uploadTopic(MultipartFile file, String ghid, String operator) {
        log.info("====开始导入试题=====文件名【" + file.getOriginalFilename() + "】");

        OutputStream os = null;
        InputStream in = null;
        String path = "";
        File tempFile = null;
        // 1. 先把文件保存在项目classpath下
        try {
            path = ResourceUtils.getURL("classpath:").getPath() + "webapp/app_data/";
            log.info("====把文件存入到===路径【" + path + "】");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            in = file.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + "/" + file.getOriginalFilename());
            while ((len = in.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 2. 解析文件获取excel所有数据
        try {
            ExcelReader reader = ExcelUtil.getReader(FileUtil.file(tempFile.getPath() + "/" + file.getOriginalFilename()));

            List<Map<String, Object>> list = reader.readAll();
            // 3. 对excel数据进行处理 存库
            if (list.size() == 0) {
                log.info("excel 没有有效数据！");
                return;
            }

            Map<String, String> mTopicContent = null;
            Map<String, Object> mOption = null;
            List<Map<String, Object>> lTopicQueContent = null;
            String[] content = {"选项A", "选项B", "选项C", "选项D", "选项E", "选项F", "选项G"};
            for (Map<String, Object> map : list) {
                mTopicContent = new HashMap<>();
                lTopicQueContent = new ArrayList<Map<String, Object>>();

                ExamTopic examTopic = new ExamTopic();
                examTopic.setTopicId("TP" + DateTime.Now().ToString("yyyyMMddHHmmss") + MyString.getRandom(4));
                //todo
                examTopic.setGhid("8600");
                examTopic.setOperator("管理员");
                examTopic.setTopicScore(5);
                examTopic.setTopicDes(strValOf(map.get("题目题干")));
                examTopic.setTopicType(intValOf(map.get("题目类型")));
                examTopic.setDifficultyLevel(intValOf(map.get("难度等级")));
                examTopic.setCorrectAnswer(strValOf(map.get("正确答案")));
                // 3.1 合成选项内容
                for (int i = 0; i < content.length; i++) {
                    mOption = new HashMap<>();
                    if (MyString.isNotEmpty(strValOf(map.get(content[i])))) {
                        mTopicContent.put(content[i].substring(2), strValOf(map.get(content[i])));
                        mOption.put("K", content[i].substring(2));
                        mOption.put("V", strValOf(map.get(content[i])));
                        if (examTopic.getTopicType() == 1 || examTopic.getTopicType() == 2) {
                            mOption.put("CH", false);
                        }
                        lTopicQueContent.add(i, mOption);
                    }
                }
                examTopic.setTopicContent(JSONObject.toJSONString(mTopicContent));
                examTopic.setTopicQueContent(JSONObject.toJSONString(lTopicQueContent));
                System.out.println(examTopic);
                examTopicDao.saveAndFlush(examTopic);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static String strValOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    public static Integer intValOf(Object obj) {
        return (obj == null) ? null : Integer.valueOf(obj.toString());
    }
}
