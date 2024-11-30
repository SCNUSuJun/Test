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

import com.dao.XuekuxinxiDao;
import com.entity.XuekuxinxiEntity;
import com.service.XuekuxinxiService;
import com.entity.vo.XuekuxinxiVO;
import com.entity.view.XuekuxinxiView;

/**
 * 血库信息服务实现类
 * 继承了ServiceImpl类，提供了血库信息的具体服务实现
 */
@Service("xuekuxinxiService")
public class XuekuxinxiServiceImpl extends ServiceImpl<XuekuxinxiDao, XuekuxinxiEntity> implements XuekuxinxiService {

    /**
     * 分页查询血库信息
     * @param params 查询参数
     * @return 分页结果
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 创建分页对象，传入查询参数
        Page<XuekuxinxiEntity> page = this.selectPage(
                new Query<XuekuxinxiEntity>(params).getPage(),
                new EntityWrapper<XuekuxinxiEntity>()
        );
        // 返回分页结果
        return new PageUtils(page);
    }

    /**
     * 分页查询血库信息，带查询条件
     * @param params 查询参数
     * @param wrapper 查询条件包装器
     * @return 分页结果
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<XuekuxinxiEntity> wrapper) {
        // 创建分页对象，传入查询参数
        Page<XuekuxinxiView> page = new Query<XuekuxinxiView>(params).getPage();
        // 设置分页记录，调用DAO层的selectListView方法
        page.setRecords(baseMapper.selectListView(page, wrapper));
        // 创建分页工具对象并返回
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    /**
     * 查询血库信息列表，返回VO对象
     * @param wrapper 查询条件包装器
     * @return 血库信息VO对象列表
     */
    @Override
    public List<XuekuxinxiVO> selectListVO(Wrapper<XuekuxinxiEntity> wrapper) {
        // 调用DAO层的selectListVO方法，返回VO对象列表
        return baseMapper.selectListVO(wrapper);
    }

    /**
     * 查询单个血库信息，返回VO对象
     * @param wrapper 查询条件包装器
     * @return 血库信息VO对象
     */
    @Override
    public XuekuxinxiVO selectVO(Wrapper<XuekuxinxiEntity> wrapper) {
        // 调用DAO层的selectVO方法，返回单个VO对象
        return baseMapper.selectVO(wrapper);
    }

    /**
     * 查询血库信息列表，返回View对象
     * @param wrapper 查询条件包装器
     * @return 血库信息View对象列表
     */
    @Override
    public List<XuekuxinxiView> selectListView(Wrapper<XuekuxinxiEntity> wrapper) {
        // 调用DAO层的selectListView方法，返回View对象列表
        return baseMapper.selectListView(wrapper);
    }

    /**
     * 查询单个血库信息，返回View对象
     * @param wrapper 查询条件包装器
     * @return 血库信息View对象
     */
    @Override
    public XuekuxinxiView selectView(Wrapper<XuekuxinxiEntity> wrapper) {
        // 调用DAO层的selectView方法，返回单个View对象
        return baseMapper.selectView(wrapper);
    }
}