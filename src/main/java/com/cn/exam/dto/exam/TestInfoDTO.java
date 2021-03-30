package com.cn.exam.dto.exam;

import com.cn.common.utils.DateTime;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTestPaper;
import com.cn.exam.entity.exam.ExamTestPerson;
import lombok.Data;

import java.util.Date;

/**
 *@Author fengzhilong
 *@Date 2021/3/29 10:14
 *@Desc 考试信息
 **/
@Data
public class TestInfoDTO {

    /*考生工号*/
    private String ghid;
    /*考生姓名*/
    private String name;
    /*试卷id*/
    private String paperId;
    /*试卷名称*/
    private String paperName;
    /*计划id*/
    private String planId;
    /*计划名称*/
    private String planName;
    /*考试时长*/
    private Integer testTime;
    /*考试形式  0:闭卷,1:开卷*/
    private String testForm;
    /*考试状态*/
    private Integer testStatus;
    /*是否可以考试 0- 可以考试,1-未开始,2-已结束*/
    private Integer sDateType = 0;


    public TestInfoDTO(ExamTestPerson ps, ExamPlan ep ,ExamTestPaper tp) {
        if (ps != null){
            this.ghid = ps.getGhid();
            this.name = ps.getName();
            this.paperId = ps.getPaperId();
            this.planId = ps.getPlanId();
            this.testStatus = ps.getTestStatus();
        }
        if (ep != null){
            this.planName = ep.getPlanName();
            this.testTime = ep.getTestTime();
            this.testForm = ep.getTestForm() == 0 ? "闭卷" : "开卷";
            this.sDateType =findDataOverdue(ep.getTestStartTime(), ep.getTestEndTime());
        }
        if (tp != null){
            this.paperName = tp.getPaperName();
        }

    }


    public TestInfoDTO() {
    }


    public static Integer findDataOverdue(String startDate, String endDate){
        Integer ret = 0;

        Date date = new Date();
        Date bign = DateTime.parse(startDate, "");
        Date end = DateTime.parse(endDate, "");
        if (date.before(bign)){
            ret = 1;
        }else if (date.after(end)){
            ret = 2;
        }

        return ret;
    }
}
