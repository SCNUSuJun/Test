package com.entity.view;

import com.entity.XueyerukuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 血液入库
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-03-17 10:40:32
 */
@TableName("xueyeruku")
public class XueyerukuView  extends XueyerukuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XueyerukuView(){
	}
 
 	public XueyerukuView(XueyerukuEntity xueyerukuEntity){
 	try {
			BeanUtils.copyProperties(this, xueyerukuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
