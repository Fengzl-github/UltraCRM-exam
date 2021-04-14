package com.cn.exam.service.exam.impl;

import com.cn.common.jpa.util.JpaUtil;
import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.DateTime;
import com.cn.common.utils.MyString;
import com.cn.exam.dao.exam.ExamTopicDao;
import com.cn.exam.dto.exam.ExamTopicDTO;
import com.cn.exam.dto.exam.SelfTrainingDTO;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.mapper.exam.ExamTopicMapper;
import com.cn.exam.service.exam.ExamTopicService;
import com.cn.exam.util.ExamUtil;
import com.cn.exam.vo.exam.ExamTopicVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *@Author fengzhilong
 *@Date 2021/1/8 13:42
 *@Desc
 **/
@Service
@Slf4j
public class ExamTopicServiceImpl implements ExamTopicService {

    @Resource
    private ExamTopicDao examTopicDao;
    @Resource
    private JpaUtil jpaUtil;


    /**
     * @Desc 题库列表
     * @param examTopicDTO 参数
     * @param pageable 分页参数
     **/
    @Override
    public Page<ExamTopic> listExamTopicPage(ExamTopicDTO examTopicDTO, JsonPage pageable) {

        Specification<ExamTopic> specification = new Specification<ExamTopic>() {
            @Override
            public Predicate toPredicate(Root<ExamTopic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (MyString.isNotEmpty(examTopicDTO.getTopicDes())) {
                    predicateList.add(criteriaBuilder.like(root.get("topicDes"), "%" + examTopicDTO.getTopicDes() + "%"));
                }
                if (examTopicDTO.getTopicType() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("topicType"), examTopicDTO.getTopicType()));
                }
                if (examTopicDTO.getDifficultyLevel() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("difficultyLevel"), examTopicDTO.getDifficultyLevel()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        return examTopicDao.findAll(specification, pageable.getPageable());
    }


    /**
     * @Desc 保存和修改题目
     * @param examTopicVO 参数
     **/
    @Override
    public void saveExamTopic(ExamTopicVO examTopicVO) {

        //新增
        if (MyString.isEmpty(examTopicVO.getTopicId())) {
            examTopicVO.setTopicId("TP" + DateTime.Now().ToString("yyyyMMddHHmmss") + MyString.getRandom(4));
            examTopicDao.saveAndFlush(ExamTopicMapper.INSTANCE.toExamTopic(examTopicVO));
        } else {
            ExamTopic examTopic = examTopicDao.findByTopicId(examTopicVO.getTopicId());
            ExamTopicMapper.INSTANCE.updateExamTopic(examTopicVO, examTopic);
            examTopicDao.saveAndFlush(examTopic);
        }
    }


    /**
     * @Desc 禁用或启用题目
     * @param topicId 试题id
     **/
    @Override
    public void removeOrRebootTopic(String topicId, Integer isDel) {

        examTopicDao.removeOrRebootTopic(topicId, isDel);
    }


    /**
     * @Author fengzhilong
     * @Desc 模拟考试
     * @Date 2021/1/13 11:18
     * @param selfTrainingDTO 参数
     * @return java.util.List<com.cn.exam.entity.exam.ExamTopic>
     **/
    @Override
    public List<ExamTopic> getSelfExamData(SelfTrainingDTO selfTrainingDTO) {
        Map<String, Object> params = new HashMap<>();
        String hql = "select et from ExamTopic et " +
                "where 1=1 ";
        if (MyString.isNotEmpty(selfTrainingDTO.getTTypeList())) {
            List<Integer> listOfType = ExamUtil.getListOfMultiple(selfTrainingDTO.getTTypeList());
            hql += "and et.topicType in (:listOfType) ";
            params.put("listOfType", listOfType);
        }
        if (MyString.isNotEmpty(selfTrainingDTO.getTDlList())) {
            List<Integer> listOfDl = ExamUtil.getListOfMultiple(selfTrainingDTO.getTDlList());
            hql += "and et.difficultyLevel in (:listOfDl) ";
            params.put("listOfDl", listOfDl);
        }

        hql += "order by rand() ";

        JsonPage jsonPage = new JsonPage();
        jsonPage.setPage(1);
        jsonPage.setSize(selfTrainingDTO.getTNum());

        Page<ExamTopic> page = jpaUtil.page(hql, params, jsonPage.getPageable(), ExamTopic.class);

        return page.getContent();
    }
}

