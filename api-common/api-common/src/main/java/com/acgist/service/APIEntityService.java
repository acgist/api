package com.acgist.service;

import com.acgist.api.APICode;
import com.acgist.pojo.dto.ResultDTO;
import com.acgist.pojo.entity.BaseEntity;
import com.acgist.utils.ValidatorUtils;

/**
 * entity服务类
 */
public interface APIEntityService {

	/**
	 * 验证数据格式是否正确
	 */
	default boolean verifyEntity(BaseEntity entity, ResultDTO resultDTO) {
		final String message = ValidatorUtils.verify(entity);
		if(message == null) {
			if(resultDTO != null) {
				resultDTO.buildSuccess();
			}
			return true;
		} else {
			if(resultDTO != null) {
				resultDTO.buildMessage(APICode.CODE_3000, message);
			}
			return false;
		}
	}
	
}
