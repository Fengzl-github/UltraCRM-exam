package com.cn.exam.restful.exam;

import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.MyString;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.dto.exam.*;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTestPaper;
import com.cn.exam.entity.exam.ExamTestPerson;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.entity.user.User;
import com.cn.exam.service.exam.*;
import com.cn.exam.service.excel.down.ExcelDownService;
import com.cn.exam.service.excel.upload.ExcelUploadService;
import com.cn.exam.service.user.UserService;
import com.cn.exam.vo.exam.EditTestPersonVO;
import com.cn.exam.vo.exam.ExamTopicVO;
import com.cn.exam.vo.exam.TestPersonVO;
import com.cn.exam.vo.exam.TestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Validated
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
    @Autowired
    private UserService userService;
    @Autowired
    private ExamTestService examTestService;
    @Autowired
    private ExamResultService examResultService;


    /**
     * @Author fengzhilong
     * @Desc 题库列表
     * @Date 2021/1/8 17:48
     * @param examTopicDTO 参数
     * @param pageable 分页参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listExamTopicPage")
    public ResResult listExamTopicPage(@RequestBody ExamTopicDTO examTopicDTO, JsonPage pageable) {

        Page<ExamTopic> page = examTopicService.listExamTopicPage(examTopicDTO, pageable);

        return ResCode.OK.msg("查询成功")
                .putData("content", page.getContent())
                .putData("total", page.getTotalElements());
    }


    /**
     * @Author fengzhilong
     * @Desc 保存和修改题目
     * @Date 2021/1/8 17:57
     * @param examTopicVO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveExamTopic")
    public ResResult saveExamTopic(@Validated @RequestBody ExamTopicVO examTopicVO) {

        examTopicService.saveExamTopic(examTopicVO);

        return ResCode.OK.msg("操作成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 禁用/启用题目
     * @Date 2021/1/8 17:57
     * @param topicId 试题id
     * @return ResResult
     **/
    @GetMapping("/removeOrRebootTopic")
    public ResResult removeOrRebootTopic(@NotBlank(message = "缺少topicId") String topicId, @NotNull(message = "缺少禁用状态") Integer isDel) {

        examTopicService.removeOrRebootTopic(topicId, isDel);

        return ResCode.OK.msg("操作成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 批量导入试题
     * @Date 2021/2/1 16:51
     * @param file 文件
     * @param ghid ghid
     * @param operator 操作人
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("uploadTopic")
    public ResResult uploadTopic(@RequestParam(value = "fileU") MultipartFile file, String ghid, String operator) {

        excelUploadService.uploadTopic(file, ghid, operator);

        return ResCode.OK.msg("上传成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 下载试题导入说明模板
     * @Date 2021/2/3 11:31
     * @param response 响应参数
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
     * @param selfTrainingDTO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getSelfExamInfo")
    public ResResult getSelfExamInfo(@Validated @RequestBody SelfTrainingDTO selfTrainingDTO) {
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
     * @param pageable 分页参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listExamPlanPage")
    public ResResult listExamPlanPage(@RequestBody ExamPlanDTO examPlanDTO, JsonPage pageable) {

        Page<ExamPlan> page = examPlanService.listExamPlanPage(examPlanDTO, pageable);

        return ResCode.OK.msg("查询成功")
                .putData("content", page.getContent())
                .putData("total", page.getTotalElements());
    }


    /**
     * @Author fengzhilong
     * @Desc 保存和修改考试计划
     * @Date 2021/1/15 10:18
     * @param examPlan 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveExamPlan")
    public ResResult saveExamPlan(@RequestBody ExamPlan examPlan) {

        examPlanService.saveExamPlan(examPlan);

        return ResCode.OK.msg("操作成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 发布考试计划/公布成绩
     * @Date 2021/1/15 10:39
     * @param planId 计划id
     * @param status 状态
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/updatePlanStatus")
    public ResResult updatePlanStatus(String planId, Integer status) {

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
     * @param planId 计划id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/removeExamPlan")
    public ResResult removeExamPlan(String planId) {

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
     * @param examProdPaperDTO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/prodThePaperTemp")
    public ResResult prodThePaperTemp(@RequestBody ExamProdPaperDTO examProdPaperDTO) {

        examTempService.prodThePaperTemp(examProdPaperDTO);

        return ResCode.OK.msg("生成试卷成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 查看该计划下有几套试卷
     * @Date 2021/2/20 11:34
     * @param planId 计划id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/testPaperList")
    public ResResult testPaperList(String planId) {
        if (MyString.isNotEmpty(planId)) {
            List<ExamTestPaper> list = examTempService.testPaperList(planId);
            return ResCode.OK.msg("查询成功")
                    .putData("content", list);
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 设置试卷是否有效
     * @Date 2021/2/20 11:34
     * @param paperId 试卷id
     * @param isUsed 是否可用
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/updateIsUsed")
    public ResResult updateIsUsed(String paperId, Integer isUsed) {
        examTempService.updateIsUsed(paperId, isUsed);

        return ResCode.OK.msg("操作成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 添加考试人员
     * @Date 2021/2/22 13:50
     * @param planId 计划id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/findTestUserList")
    public ResResult findTestUserList(String planId) {

        List<User> list = userService.findTestUserList(planId);

        return ResCode.OK.setData(list);
    }

    /**
     * @Author fengzhilong
     * @Desc 参考人员列表
     * @Date 2021/3/12 15:01
     * @param planId 计划id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getTestPerson")
    public ResResult getTestPerson(String planId) {

        List<ExamTestPerson> list = examTempService.getTestPerson(planId);

        return ResCode.OK.setData(list);
    }

    /**
     * @Author fengzhilong
     * @Desc 试卷预览
     * @Date 2021/3/4 16:38
     * @param planId 计划id
     * @param paperId 试卷id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/previewPaperData")
    public ResResult previewPaperData(String planId, String paperId) {
        if (MyString.isNotEmpty(paperId) && MyString.isNotEmpty(planId)) {

            return examTempService.previewPaperData(planId, paperId);


        } else {
            return ResCode.ERROR.msg("缺少参数");
        }

    }

    /**
     * @Author fengzhilong
     * @Desc 获取考生信息
     * @Date 2021/3/18 16:21
     * @param testPersonVO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getTestPersonInfo")
    public ResResult getTestPersonInfo(@Validated @RequestBody TestPersonVO testPersonVO) {

        TestPersonDTO testPersonDTO = examTestService.getTestPersonInfo(testPersonVO);

        return ResCode.OK.msg("操作成功")
                .setData(testPersonDTO);
    }

    /**
     * @Author fengzhilong
     * @Desc 编辑参考人员(移入 / 移出)
     * @Date 2021/3/18 16:21
     * @param editTestPersonVO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/editTestPaperToPlan")
    public ResResult editTestPaperToPlan(@Validated @RequestBody EditTestPersonVO editTestPersonVO) {
        examTestService.editTestPaperToPlan(editTestPersonVO);

        return ResCode.OK.msg("添加成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 分配试卷
     * @Date 2021/3/18 16:22
     * @param planId 计划id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/allotPaper")
    public ResResult allotPaper(String planId) {

        examTempService.allotPaper(planId);

        return ResCode.OK.msg("操作成功");
    }


    /*获取试卷信息 - 同试卷预览*/

    /**
     * @Author fengzhilong
     * @Desc 获取考试信息
     * @Date 2021/3/30 10:56
     * @param planId 计划id
     * @param ghid 工号
     * @return ResResult
     **/
    @GetMapping("/getTestInfo")
    public ResResult getTestInfo(@NotBlank(message = "缺少计划id") String planId, @NotBlank(message = "缺少工号") String ghid) {

        TestInfoDTO testInfo = examTestService.getTestInfo(planId, ghid);

        return ResCode.OK.setData(testInfo);
    }


    /**
     * @Author fengzhilong
     * @Desc 提交试卷
     * @Date 2021/4/13 15:48
     * @param personTestingDTO 参数
     * @return ResResult
     **/
    @PostMapping("/submitPage")
    public ResResult submitPage(@Validated @RequestBody PersonTestingDTO personTestingDTO) {

        examTestService.submitPage(personTestingDTO);

        return ResCode.OK;
    }


    /**
     * @Author fengzhilong
     * @Desc 考试结果 - 阅卷和查看成绩共用
     * @Date 2021/4/14 10:27
     * @param testResultVO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/testResultPage")
    public ResResult testResultPage(@Valid @RequestBody TestResultVO testResultVO) {

        //默认10条
        JsonPage pageable = new JsonPage();
        pageable.setSize(10);

        List<TestResultDTO> list = examResultService.testResultPage(testResultVO, pageable);

        return ResCode.OK.setData(list);
    }


    /**
     * @Author fengzhilong
     * @Desc 提交阅卷结果
     * @Date 2021/4/14 10:52
     * @param submitScoringDTO 参数
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/submitScoring")
    public ResResult submitScoring(@Valid @RequestBody SubmitScoringDTO submitScoringDTO) {

        examResultService.submitScoring(submitScoringDTO);

        return ResCode.OK.msg("提交成功");
    }


    /*如果没有提交直接关闭，则把是否阅卷中状态改回*/
    @PostMapping("/updateIsScoring")
    public void updateIsScoring(List<Integer> ids) {

        System.out.println(ids.toString());
    }


}
