package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamTestQues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 16:53
 *@Desc
 **/
@Repository
public interface ExamTestQuesDao extends JpaRepository<ExamTestQues, Integer>, JpaSpecificationExecutor<ExamTestQues> {


    List<ExamTestQues> findByPaperId(String paperId);
}
