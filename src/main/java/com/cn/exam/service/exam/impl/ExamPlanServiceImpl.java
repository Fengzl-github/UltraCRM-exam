package com.cn.exam.service.exam.impl;

import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.DateTime;
import com.cn.common.utils.MyString;
import com.cn.exam.dao.exam.ExamPlanDao;
import com.cn.exam.dto.exam.ExamPlanDTO;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.service.exam.ExamPlanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/14 15:43
 *@Desc
 **/
@Service
public class ExamPlanServiceImpl implements ExamPlanService {

    @Resource
    private ExamPlanDao examPlanDao;


    /**
     * @Desc 考试计划列表
     * @param pageable 分页参数
     **/
    @Override
    public Page<ExamPlan> listExamPlanPage(ExamPlanDTO examPlanDTO, JsonPage pageable) {

        Specification<ExamPlan> specification = new Specification<ExamPlan>() {
            @Override
            public Predicate toPredicate(Root<ExamPlan> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (MyString.isNotEmpty(examPlanDTO.getPlanName())) {
                    predicateList.add(criteriaBuilder.like(root.get("planName"), "%" + examPlanDTO.getPlanName() + "%"));
                }
                if (examPlanDTO.getStatus() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), examPlanDTO.getStatus()));
                }
                if (examPlanDTO.getIsPaper() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("isPaper"), examPlanDTO.getIsPaper()));
                }
                predicateList.add(criteriaBuilder.notEqual(root.get("isDel"), 1));

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        Sort sort = Sort.by(Sort.Direction.ASC, new String[]{"status", "createTime"});
        Page<ExamPlan> page = examPlanDao.findAll(specification, pageable.getPageableSorted(sort));

        return page;
    }


    /**
     * @Desc 保存考试计划
     * @param examPlan 参数
     **/
    @Override
    public void saveExamPlan(ExamPlan examPlan) {

        if (examPlan.getId() == null) { //新增

            examPlan.setPlanId("PL" + DateTime.Now().ToString("yyyyMMddHHmmss") + MyString.getRandom(4));
        }

        examPlanDao.saveAndFlush(examPlan);
    }

    /**
     * @Desc 修改考试计划状态
     * @param planId 计划id
     * @param status 状态
     **/
    @Override
    public void updatePlanStatus(String planId, Integer status) {

        examPlanDao.updatePlanStatus(planId, status);

    }

    /**
     * @Desc 删除考试计划
     * @param planId 计划id
     **/
    @Override
    public void removeExamPlan(String planId) {

        examPlanDao.removeExamPlan(planId);

    }
}
