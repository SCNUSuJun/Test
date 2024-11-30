package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.CaixuedengjiDao;
import com.entity.CaixuedengjiEntity;
import com.service.CaixuedengjiService;
import com.entity.vo.CaixuedengjiVO;
import com.entity.view.CaixuedengjiView;

@Service("caixuedengjiService")
public class CaixuedengjiServiceImpl extends ServiceImpl<CaixuedengjiDao, CaixuedengjiEntity> implements CaixuedengjiService {
	
	// 重写分页查询方法
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CaixuedengjiEntity> page = this.selectPage(
                new Query<CaixuedengjiEntity>(params).getPage(), // 获取分页对象
                new EntityWrapper<CaixuedengjiEntity>() // 创建实体包装器,用于构建查询条件
        );
        return new PageUtils(page); // 返回分页工具类对象
    }
    
    // 重写分页查询方法,使用自定义的Wrapper
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CaixuedengjiEntity> wrapper) {
		  Page<CaixuedengjiView> page =new Query<CaixuedengjiView>(params).getPage(); // 获取分页对象
	        page.setRecords(baseMapper.selectListView(page,wrapper)); // 调用Dao层的selectListView方法,传入分页对象和条件构造器
	    	PageUtils pageUtil = new PageUtils(page); // 创建分页工具类对象
	    	return pageUtil; // 返回分页工具类对象
 	}
    
    // 重写查询VO列表方法
    @Override
	public List<CaixuedengjiVO> selectListVO(Wrapper<CaixuedengjiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper); // 调用Dao层的selectListVO方法,传入条件构造器
	}
	
	// 重写查询单个VO对象方法
	@Override
	public CaixuedengjiVO selectVO(Wrapper<CaixuedengjiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper); // 调用Dao层的selectVO方法,传入条件构造器
	}
	
	// 重写查询视图列表方法
	@Override
	public List<CaixuedengjiView> selectListView(Wrapper<CaixuedengjiEntity> wrapper) {
		return baseMapper.selectListView(wrapper); // 调用Dao层的selectListView方法,传入条件构造器
	}

	// 重写查询单个视图对象方法
	@Override
	public CaixuedengjiView selectView(Wrapper<CaixuedengjiEntity> wrapper) {
		return baseMapper.selectView(wrapper); // 调用Dao层的selectView方法,传入条件构造器
	}

}
