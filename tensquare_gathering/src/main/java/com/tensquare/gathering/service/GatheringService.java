package com.tensquare.gathering.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.tensquare.gathering.pojo.Gathering;

/**
 * 服务层
 * Spring Cache使用方法与Spring对事务管理的配置相似。Spring Cache的核心就是对某个方法进行缓存，
 * 其实质就是缓存该方法的返回结果，并把方法参数和结果用键值对的 方式存放到缓存中，
 * 当再次调用该方法使用相应的参数时，就会直接从缓存里面取出指 定的结果进行返回
 * @author shangjiapeng
 */
@Service
public class 	GatheringService {

	@Autowired
	private GatheringDao gatheringDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Gathering> findAll() {
		return gatheringDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Gathering> findSearch(Map whereMap, int page, int size) {
		Specification<Gathering> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return gatheringDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Gathering> findSearch(Map whereMap) {
		Specification<Gathering> specification = createSpecification(whereMap);
		return gatheringDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @Cacheable-------使用这个注解的方法在执行后会缓存其返回结果。
	 * @CacheEvict--------使用这个注解的方法在其执行前或执行后移除Spring Cache中的某些元素。
	 * @return
	 */
	@Cacheable(value = "gathering",key = "#id")  //当此方法第一次运行，在 缓存中没有找到对应的value和key，则将查询结果放入缓存
	public Gathering findById(String id) {
		return gatheringDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param gathering
	 */
	public void add(Gathering gathering) {
		gathering.setId( idWorker.nextId()+"" );
		gatheringDao.save(gathering);
	}

	/**
	 * 修改
	 * @param gathering
	 * 当我们对数据进行修改,删改的时候，需要更新缓存。其实更新缓存也就是清除缓存
	 */
	@CacheEvict(value = "gathering",key = "#gathering.id")
	public void update(Gathering gathering) {
		gatheringDao.save(gathering);
	}

	/**
	 * 删除
	 * @param id
	 */
	@CacheEvict(value="gathering",key="#id")
	public void deleteById(String id) {
		gatheringDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Gathering> createSpecification(Map searchMap) {
		return new Specification<Gathering>() {

			@Override
			public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 编号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 活动名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 大会简介
                if (searchMap.get("summary")!=null && !"".equals(searchMap.get("summary"))) {
                	predicateList.add(cb.like(root.get("summary").as(String.class), "%"+(String)searchMap.get("summary")+"%"));
                }
                // 详细说明
                if (searchMap.get("detail")!=null && !"".equals(searchMap.get("detail"))) {
                	predicateList.add(cb.like(root.get("detail").as(String.class), "%"+(String)searchMap.get("detail")+"%"));
                }
                // 主办方
                if (searchMap.get("sponsor")!=null && !"".equals(searchMap.get("sponsor"))) {
                	predicateList.add(cb.like(root.get("sponsor").as(String.class), "%"+(String)searchMap.get("sponsor")+"%"));
                }
                // 活动图片
                if (searchMap.get("image")!=null && !"".equals(searchMap.get("image"))) {
                	predicateList.add(cb.like(root.get("image").as(String.class), "%"+(String)searchMap.get("image")+"%"));
                }
                // 举办地点
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                	predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
                // 是否可见
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                // 城市
                if (searchMap.get("city")!=null && !"".equals(searchMap.get("city"))) {
                	predicateList.add(cb.like(root.get("city").as(String.class), "%"+(String)searchMap.get("city")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};
	}



}
