package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.tensquare.base.pojo.Label;
import com.tensquare.recruit.util.IdWorker;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>标签业务逻辑类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 17:02
 */
@Service
public class LabelService {

    @Resource
    private LabelDao labelDao;
    @Resource
    private IdWorker idWorker;

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LabelService.class);

    /**
     * 查询全部的标签
     *
     * @return list
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据id查询
     *
     * @param id long
     * @return com.tensquare.base.pojo
     */
    public Label findById(String id) {
        Optional<Label> labelOptional = labelDao.findById(id);
        Label label = labelOptional.get();
        return label;
    }

    /**
     * 添加标签
     *
     * @param label com.tensquare.base.pojo
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");//设置id
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label com.tensquare.base.pojo
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 根据id删除标签
     *
     * @param id Long
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Specification<Label> createSpecification(Map searchMap){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if (searchMap.get("labelname")!=null&&!"".equals(searchMap.get("labelname"))){
                    predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class),"%"+(String)searchMap.get("labelname")+"%"));
                }
                if (searchMap.get("state")!=null&&!"".equals(searchMap.get("state"))){
                    predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class),(String)searchMap.get("state")));
                }
                if (searchMap.get("recommend")!=null&&!"".equals(searchMap.get("recommend"))){
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class),(String)searchMap.get("recommend")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Label> findBySearchMap(Map searchMap){
        Specification<Label> specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * 带分页的条件查询
     * @param searchMap
     * @return
     */
    public Page<Label> findBySearchMap(Map searchMap, int page, int size){
        Specification<Label> specification = createSpecification(searchMap);
        //这个对象是0代表第一页,所以要-1
        PageRequest pageRequest= PageRequest.of(page-1,size);
        return labelDao.findAll(specification,pageRequest);
    }




}
