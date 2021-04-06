package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *@Author fengzhilong
 *@Date 2021/4/6 17:25
 *@Desc
 **/
@Repository
public interface ExamTestResultDao extends JpaRepository<ExamTestResult, Integer>,JpaSpecificationExecutor<ExamTestResult> {
}
