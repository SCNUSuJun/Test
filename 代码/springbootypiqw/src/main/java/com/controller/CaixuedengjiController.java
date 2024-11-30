package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.CaixuedengjiEntity;
import com.entity.view.CaixuedengjiView;

import com.service.CaixuedengjiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 采血登记控制器
 * 处理采血登记相关的后端接口
 */
@RestController
@RequestMapping("/caixuedengji")
public class CaixuedengjiController {
    @Autowired
    private CaixuedengjiService caixuedengjiService; // 注入采血登记服务

    /**
     * 后端列表查询接口
     * 根据不同角色获取采血登记列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaixuedengjiEntity caixuedengji,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xianxuerenyuan")) { // 献血人员角色
			caixuedengji.setXianxuebianhao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongzuorenyuan")) { // 工作人员角色 
			caixuedengji.setGonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<CaixuedengjiEntity> ew = new EntityWrapper<CaixuedengjiEntity>();

		PageUtils page = caixuedengjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caixuedengji), params), params));
		// 调用Service层的分页查询方法,传入分页参数、查询条件等,最后将结果封装为PageUtils对象返回
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表查询接口
     * 无需登录鉴权
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CaixuedengjiEntity caixuedengji, 
		HttpServletRequest request){
        EntityWrapper<CaixuedengjiEntity> ew = new EntityWrapper<CaixuedengjiEntity>();

		PageUtils page = caixuedengjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caixuedengji), params), params));
		// 与后端列表查询类似,只是无需根据角色过滤数据
        return R.ok().put("data", page);
    }

	/**
     * 列表查询接口
     * 根据采血登记实体条件查询
     */
    @RequestMapping("/lists")
    public R list( CaixuedengjiEntity caixuedengji){
       	EntityWrapper<CaixuedengjiEntity> ew = new EntityWrapper<CaixuedengjiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caixuedengji, "caixuedengji")); 
      	// 根据传入的CaixuedengjiEntity实体条件构建查询条件
        return R.ok().put("data", caixuedengjiService.selectListView(ew));
        // 调用Service的查询方法,最后将结果封装为R对象返回
    }

	 /**
     * 查询单个采血登记详情
     */
    @RequestMapping("/query")
    public R query(CaixuedengjiEntity caixuedengji){
        EntityWrapper< CaixuedengjiEntity> ew = new EntityWrapper< CaixuedengjiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caixuedengji, "caixuedengji")); 
 		// 根据传入的CaixuedengjiEntity实体构建查询条件
		CaixuedengjiView caixuedengjiView =  caixuedengjiService.selectView(ew);
		// 调用Service的查询方法,得到CaixuedengjiView对象
		return R.ok("查询采血登记成功").put("data", caixuedengjiView);
    }
	
    /**
     * 后端详情接口
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaixuedengjiEntity caixuedengji = caixuedengjiService.selectById(id);
        // 根据id查询采血登记详情
        return R.ok().put("data", caixuedengji);
    }

    /**
     * 前端详情接口
     * 无需登录鉴权
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaixuedengjiEntity caixuedengji = caixuedengjiService.selectById(id);
        // 与后端详情接口类似
        return R.ok().put("data", caixuedengji);
    }

    /**
     * 后端保存接口
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaixuedengjiEntity caixuedengji, HttpServletRequest request){
    	caixuedengji.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	// 设置唯一id
        caixuedengjiService.insert(caixuedengji);
        // 调用Service保存方法
        return R.ok();
    }
    
    /**
     * 前端保存接口
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaixuedengjiEntity caixuedengji, HttpServletRequest request){
    	caixuedengji.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	// 设置唯一id
        caixuedengjiService.insert(caixuedengji);
        // 调用Service保存方法
        return R.ok();
    }

    /**
     * 修改接口
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaixuedengjiEntity caixuedengji, HttpServletRequest request){
        caixuedengjiService.updateById(caixuedengji);//全部更新
        // 根据id全部更新采血登记信息
        return R.ok();
    }

    /**
     * 删除接口
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caixuedengjiService.deleteBatchIds(Arrays.asList(ids));
        // 根据id列表批量删除采血登记
        return R.ok();
    }
    
    /**
     * 提醒接口
     * 根据条件统计提醒数量
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		// 根据提醒类型构建不同的查询条件
		
		Wrapper<CaixuedengjiEntity> wrapper = new EntityWrapper<CaixuedengjiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}
		// 根据提醒字段和提醒时间范围构建查询条件

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xianxuerenyuan")) {
			wrapper.eq("xianxuebianhao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongzuorenyuan")) {
			wrapper.eq("gonghao", (String)request.getSession().getAttribute("username"));
		}
		// 根据角色添加数据过滤条件

		int count = caixuedengjiService.selectCount(wrapper);
		// 调用Service的查询方法统计数量
		return R.ok().put("count", count);
	}
	
}
