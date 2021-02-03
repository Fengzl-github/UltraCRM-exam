package com.cn.exam.restful.exam;

import com.cn.common.exception.FzlException;
import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.MyString;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.dto.exam.*;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.service.exam.ExamPlanService;
import com.cn.exam.service.exam.ExamTempService;
import com.cn.exam.service.exam.ExamTopicService;
import com.cn.exam.service.excel.down.ExcelDownService;
import com.cn.exam.service.excel.upload.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/8 13:44
 *@Desc 关于考试的接口控制器
 **/
@RestController
@RequestMapping("/exam")
public class ExamResful {

    @Autowired
    private ExamTopicService examTopicService;
    @Autowired
    private ExamPlanService examPlanService;
    @Autowired
    private ExamTempService examTempService;
    @Autowired
    private ExcelUploadService excelUploadService;
    @Autowired
    private ExcelDownService excelDownService;


    /**
     * @Author fengzhilong
     * @Desc 题库列表
     * @Date 2021/1/8 17:48
     * @param examTopicDTO
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listExamTopicPage")
    ResResult listExamTopicPage(@RequestBody ExamTopicDTO examTopicDTO, JsonPage pageable) throws FzlException {

        Page<ExamTopic> page = examTopicService.listExamTopicPage(examTopicDTO, pageable);

        return ResCode.OK.msg("查询成功")
                .putData("content", page.getContent())
                .putData("total", page.getTotalElements());
    }


    /**
     * @Author fengzhilong
     * @Desc 保存和修改题目
     * @Date 2021/1/8 17:57
     * @param examTopic
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveExamTopic")
    ResResult saveExamTopic(@RequestBody ExamTopic examTopic) throws FzlException {

        examTopicService.saveExamTopic(examTopic);

        return ResCode.OK.msg("操作成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 禁用/启用题目
     * @Date 2021/1/8 17:57
     * @param topicId 重启
     * @return
     **/
    @PostMapping("/removeOrRebootTopic")
    ResResult removeOrRebootTopic(String topicId, Integer isDel) throws FzlException {

        if (MyString.isNotEmpty(topicId)) {

            examTopicService.removeOrRebootTopic(topicId, isDel);

            return ResCode.OK.msg("操作成功");
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 批量导入试题
     * @Date 2021/2/1 16:51
     * @param file
     * @param ghid
     * @param operator
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("uploadTopic")
    ResResult uploadTopic(@RequestParam(value = "fileU") MultipartFile file, String ghid, String operator) {

        excelUploadService.uploadTopic(file, ghid, operator);

        return ResCode.OK.msg("上传成功");
    }

    
    /**
     * @Author fengzhilong 
     * @Desc  下载试题导入说明模板
     * @Date 2021/2/3 11:31
     * @param response
     * @return void
     **/
    @GetMapping("/downTopicTemp")
    public void downTopicTemp(HttpServletResponse response) throws IOException {
        // 1. 模板文件名称
        String fileName = "试题导入模板.xlsx";
        OutputStream out = response.getOutputStream();
        response.reset();

        String headStr = "attachment; filename=" + URLEncoder.encode(fileName, "utf-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", headStr);

        excelDownService.downTopicTemp(out);

    }


    /**
     * @Author fengzhilong
     * @Desc 自主练习获取题目内容
     * @Date 2021/1/13 15:03
     * @param selfTrainingDTO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getSelfExamInfo")
    ResResult getSelfExamInfo(@RequestBody SelfTrainingDTO selfTrainingDTO) {
        List<ExamTopic> examList = examTopicService.getSelfExamData(selfTrainingDTO);
//        System.out.println(examList);
        //转换为前端可解析的数据结构
        List<TestPaperDTO> list = new ArrayList<>();
        for (ExamTopic examTopic : examList) {
            list.add(new TestPaperDTO(examTopic));
        }
//        System.out.println(list);

        return ResCode.OK.putData("content", list);
    }


    /**
     * @Author fengzhilong
     * @Desc 考试计划列表
     * @Date 2021/1/14 17:12
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listExamPlanPage")
    ResResult listExamPlanPage(@RequestBody ExamPlanDTO examPlanDTO, JsonPage pageable) throws FzlException {

        Page<ExamPlan> page = examPlanService.listExamPlanPage(examPlanDTO, pageable);

        return ResCode.OK.msg("查询成功")
                .putData("content", page.getContent())
                .putData("total", page.getTotalElements());
    }


    /**
     * @Author fengzhilong
     * @Desc 保存和修改考试计划
     * @Date 2021/1/15 10:18
     * @param examPlan
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveExamPlan")
    ResResult saveExamPlan(@RequestBody ExamPlan examPlan) throws FzlException {

        examPlanService.saveExamPlan(examPlan);

        return ResCode.OK.msg("操作成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 发布考试计划/公布成绩
     * @Date 2021/1/15 10:39
     * @param planId
     * @param status
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/updatePlanStatus")
    ResResult updatePlanStatus(String planId, Integer status) throws FzlException {

        if (MyString.isNotEmpty(planId)) {

            examPlanService.updatePlanStatus(planId, status);

            return ResCode.OK.msg("操作成功");
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 删除考试计划
     * @Date 2021/1/15 10:21
     * @param planId
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/removeExamPlan")
    ResResult removeExamPlan(String planId) throws FzlException {

        if (MyString.isNotEmpty(planId)) {

            examPlanService.removeExamPlan(planId);

            return ResCode.OK.msg("操作成功");
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 生成几套试卷模板 - 新生成的全部不生效，需要单独操作使它们生效
     * @Date 2021/1/27 16:55
     * @param examProdPaperDTO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/prodThePaperTemp")
    ResResult prodThePaperTemp(@RequestBody ExamProdPaperDTO examProdPaperDTO) throws FzlException {

        examTempService.prodThePaperTemp(examProdPaperDTO);

        return ResCode.OK.msg("生成试卷成功");
    }
}
