package com.cn.exam.service.excel.down.impl;

import com.cn.exam.service.excel.down.ExcelDownService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 *@Author fengzhilong
 *@Date 2021/2/2 14:07
 *@Desc
 **/
@Service
@Slf4j
public class ExcelDownServiceImpl implements ExcelDownService {

    /**
     * @Desc 下载试题导入模板
     * @param response
     * @return void
     **/
    @Override
    public void downTopicTemp(OutputStream out) {
        // 1. 文件名称
        String fileName = "试题导入模板.xlsx";
        // 2. 模板路径
        String tempPath = "";
        try {
            tempPath = ResourceUtils.getURL("classpath:").getPath() + "webapp/app_data/temp/" + File.separator + fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file = new File(tempPath);
        // 3. 检测文件是否存在
        if (!file.exists() || !file.isFile()) {
            log.info("文件不存在");
            return;
        }

        FileInputStream in = null;
        XSSFWorkbook workbook = null;

        try {
            // 4. 将文件转化为输入流
            in = new FileInputStream(file);
            // 5. 创建excel对象
            workbook = new XSSFWorkbook(in);
            // 6. 写出到浏览器
            workbook.write(out);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
