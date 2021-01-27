package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Author fengzhilong
 *@Date 2021/1/14 15:35
 *@Desc
 **/
@Repository
public interface ExamPlanDao extends JpaRepository<ExamPlan, Integer>, JpaSpecificationExecutor<ExamPlan> {


    @Transactional
    @Modifying
    @Query("update ExamPlan ep set ep.status = :status where ep.planId = :planId")
    void updatePlanStatus(@Param("planId") String planId, @Param("status") Integer status);


    @Transactional
    @Modifying
    @Query("update ExamPlan ep set ep.isDel = 1 where ep.planId = :planId")
    void removeExamPlan(@Param("planId") String planId);

    /*根据计划id获取考试计划*/
    @Query("select ep from ExamPlan ep where ep.planId = :planId and ep.isDel = 0")
    ExamPlan findByPlanId(@Param("planId") String planId);
}
