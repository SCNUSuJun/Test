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

import com.entity.GongzuorenyuanEntity;
import com.entity.view.GongzuorenyuanView;

import com.service.GongzuorenyuanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 工作人员控制器
 * 提供工作人员相关的接口,包括登录、注册、列表查询、详情查询、保存、修改、删除等
 */
@RestController
@RequestMapping("/gongzuorenyuan")
public class GongzuorenyuanController {
    @Autowired
    private GongzuorenyuanService gongzuorenyuanService; // 注入工作人员服务,用于调用工作人员相关的业务逻辑方法

    @Autowired
	private TokenService tokenService; // 注入token服务,用于生成和验证token
	
	/**
	 * 登录接口
	 * 通过工号和密码进行登录,登录成功返回token
	 */
	@IgnoreAuth // 忽略登录鉴权,允许未登录访问
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据工号查询工作人员信息
		GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("gonghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
		// 生成token
		String token = tokenService.generateToken(u.getId(), username,"gongzuorenyuan",  "工作人员" );
		return R.ok().put("token", token);
	}

	
	/**
     * 注册接口
     * 通过传入工作人员实体进行注册
     */
	@IgnoreAuth // 忽略登录鉴权,允许未登录访问
    @RequestMapping("/register")
    public R register(@RequestBody GongzuorenyuanEntity gongzuorenyuan){
    	// 根据工号查询是否已存在该工作人员
    	GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("gonghao", gongzuorenyuan.getGonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		// 新增工作人员
		Long uId = new Date().getTime();
		gongzuorenyuan.setId(uId);
        gongzuorenyuanService.insert(gongzuorenyuan);
        return R.ok();
    }

	
	/**
	 * 退出接口
	 * 销毁session中的用户信息
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取当前登录用户信息接口
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        GongzuorenyuanEntity u = gongzuorenyuanService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置接口
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("gonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        gongzuorenyuanService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表查询接口
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GongzuorenyuanEntity gongzuorenyuan,
		HttpServletRequest request){
        EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();
		PageUtils page = gongzuorenyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzuorenyuan), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表查询接口
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,GongzuorenyuanEntity gongzuorenyuan, 
		HttpServletRequest request){
        EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();
		PageUtils page = gongzuorenyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzuorenyuan), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 查询所有列表
     */
    @RequestMapping("/lists")
    public R list( GongzuorenyuanEntity gongzuorenyuan){
       	EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gongzuorenyuan, "gongzuorenyuan")); 
        return R.ok().put("data", gongzuorenyuanService.selectListView(ew));
    }

	 /**
     * 查询详情
     */
    @RequestMapping("/query")
    public R query(GongzuorenyuanEntity gongzuorenyuan){
        EntityWrapper< GongzuorenyuanEntity> ew = new EntityWrapper< GongzuorenyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gongzuorenyuan, "gongzuorenyuan")); 
		GongzuorenyuanView gongzuorenyuanView =  gongzuorenyuanService.selectView(ew);
		return R.ok("查询工作人员成功").put("data", gongzuorenyuanView);
    }
	
    /**
     * 后端详情接口
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GongzuorenyuanEntity gongzuorenyuan = gongzuorenyuanService.selectById(id);
        return R.ok().put("data", gongzuorenyuan);
    }

    /**
     * 前端详情接口
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GongzuorenyuanEntity gongzuorenyuan = gongzuorenyuanService.selectById(id);
        return R.ok().put("data", gongzuorenyuan);
    }
    

    /**
     * 后端保存接口
     */
    @RequestMapping("/save")
    public R save(@RequestBody GongzuorenyuanEntity gongzuorenyuan, HttpServletRequest request){
    	gongzuorenyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(gongzuorenyuan);
    	GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("gonghao", gongzuorenyuan.getGonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		gongzuorenyuan.setId(new Date().getTime());
        gongzuorenyuanService.insert(gongzuorenyuan);
        return R.ok();
    }
    
    /**
     * 前端保存接口
     */
    @RequestMapping("/add")
    public R add(@RequestBody GongzuorenyuanEntity gongzuorenyuan, HttpServletRequest request){
    	gongzuorenyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(gongzuorenyuan);
    	GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("gonghao", gongzuorenyuan.getGonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		gongzuorenyuan.setId(new Date().getTime());
        gongzuorenyuanService.insert(gongzuorenyuan);
        return R.ok();
    }

    /**
     * 修改接口
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody GongzuorenyuanEntity gongzuorenyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongzuorenyuan);
        gongzuorenyuanService.updateById(gongzuorenyuan);//全部更新
        return R.ok();
    }

    /**
     * 删除接口
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        gongzuorenyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
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
		
		Wrapper<GongzuorenyuanEntity> wrapper = new EntityWrapper<GongzuorenyuanEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		int count = gongzuorenyuanService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
}
