package com.hosjoy.dubbo.api.service;

import java.util.List;

import com.hosjoy.core.model.PayDetail;
import com.hosjoy.dubbo.api.model.common.BaseModel;
import com.hosjoy.dubbo.api.model.common.BaseModelInfo;

public interface IPayDetailService {
	
	BaseModelInfo<PayDetail> selectpdById(long id);
	
	BaseModelInfo<List<PayDetail>> selectNumList(Integer num);
	
	BaseModel insertLogPd(PayDetail pd);

}
