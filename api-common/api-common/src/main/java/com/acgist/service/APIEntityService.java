package com.acgist.service;

import com.acgist.api.APICode;
import com.acgist.pojo.dto.ServiceDTO;
import com.acgist.pojo.entity.BaseEntity;
import com.acgist.utils.ValidatorUtils;

/**
 * entity服务类
 */
public interface APIEntityService<T extends BaseEntity> {

	/**
	 * 验证数据格式是否正确
	 */
	default boolean verifyEntity(BaseEntity entity, ServiceDTO<T> serviceDTO) {
		final String message = ValidatorUtils.verify(entity);
		if(message == null) {
			if(serviceDTO != null) {
				serviceDTO.buildSuccess();
			}
			return true;
		} else {
			if(serviceDTO != null) {
				serviceDTO.buildMessage(APICode.CODE_3000, message);
			}
			return false;
		}
	}
	
}
