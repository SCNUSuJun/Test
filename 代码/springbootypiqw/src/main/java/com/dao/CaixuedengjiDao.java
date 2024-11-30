package com.dao;

import com.entity.CaixuedengjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaixuedengjiVO;
import com.entity.view.CaixuedengjiView;


/**
 * 采血登记Dao接口
 * 继承基类BaseMapper,提供基本的CRUD操作
 */
public interface CaixuedengjiDao extends BaseMapper<CaixuedengjiEntity> {
	
	// 查询采血登记VO列表,并封装到CaixuedengjiVO对象中
	List<CaixuedengjiVO> selectListVO(@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);
	
	// 查询单个采血登记VO对象
	CaixuedengjiVO selectVO(@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);
	
	// 查询采血登记视图列表
	List<CaixuedengjiView> selectListView(@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);

	// 分页查询采血登记视图列表
	List<CaixuedengjiView> selectListView(Pagination page,@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);
	
	// 查询单个采血登记视图对象
	CaixuedengjiView selectView(@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);
	

}
