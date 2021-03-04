package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamTestPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 16:19
 *@Desc
 **/
@Repository
public interface ExamTestPaperDao extends JpaRepository<ExamTestPaper, Integer>, JpaSpecificationExecutor<ExamTestPaper> {

    /*计划下试卷列表*/
    @Query("select etp from ExamTestPaper etp where etp.isDel = 0 and etp.planId = :planId order by etp.isUsed desc,etp.createTime desc")
    List<ExamTestPaper> findByPlanId(@Param("planId") String planId);


    /*设置试卷是否生效*/
    @Transactional
    @Modifying
    @Query("update ExamTestPaper etp set etp.isUsed = :isUsed where etp.paperId = :paperId ")
    void updateIsUsed(@Param("paperId") String paperId, @Param("isUsed") Integer isUsed);

    /*有效试卷列表*/
    @Query("select etp from ExamTestPaper etp where etp.isDel = 0 and etp.planId = :planId and etp.isUsed = :isUsed order by etp.isUsed desc,etp.createTime desc")
    List<ExamTestPaper> findByPlanIdAndIsUsed(@Param("planId") String planId, @Param("isUsed") Integer isUsed);
}
