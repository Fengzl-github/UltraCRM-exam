package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Author fengzhilong
 *@Date 2021/4/6 17:25
 *@Desc
 **/
@Repository
public interface ExamTestResultDao extends JpaRepository<ExamTestResult, Integer>,JpaSpecificationExecutor<ExamTestResult> {

    /**
     * @Author fengzhilong
     * @Desc  更新试题分数
     * @Date 2021/4/14 11:06
     * @param id 主键
	 * @param epScore 得分
     * @return java.lang.Integer
     **/
    @Transactional
    @Modifying
    @Query("update ExamTestResult tr set tr.epScore = :epScore where tr.id = :id")
    Integer updateEpScore(@Param("id") Integer id, @Param("epScore") double epScore);

    /**
     * @Author fengzhilong
     * @Desc  计算总分
     * @Date 2021/4/14 11:19
     * @param paperId 试卷编号
	 * @param ghid ghid
     * @return double
     **/
    @Query(value = "select sum(tr.ep_score) from t_exam_test_result tr where tr.paper_id = :paperId and tr.ghid = :ghid ", nativeQuery = true)
    double countTotalScore(String paperId, String ghid);
}
