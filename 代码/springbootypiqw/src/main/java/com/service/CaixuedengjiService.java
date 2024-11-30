package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaixuedengjiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaixuedengjiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaixuedengjiView;


/**
 * 采血登记服务接口
 * 继承IService接口,提供基础的CRUD方法
 */
public interface CaixuedengjiService extends IService<CaixuedengjiEntity> {

    // 分页查询方法
    PageUtils queryPage(Map<String, Object> params);
    
   	// 查询VO列表方法
   	List<CaixuedengjiVO> selectListVO(Wrapper<CaixuedengjiEntity> wrapper);
   	
   	// 查询单个VO对象方法
   	CaixuedengjiVO selectVO(@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);
   	
   	// 查询视图列表方法
   	List<CaixuedengjiView> selectListView(Wrapper<CaixuedengjiEntity> wrapper);
   	
   	// 查询单个视图对象方法
   	CaixuedengjiView selectView(@Param("ew") Wrapper<CaixuedengjiEntity> wrapper);
   	
   	// 分页查询方法,使用自定义的Wrapper
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaixuedengjiEntity> wrapper);
   	

}

