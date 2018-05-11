package com.hosjoy.dubbo.provider.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.dubbo.config.annotation.Service;
import com.hosjoy.core.dao.PayDetailMapper;
import com.hosjoy.core.model.PayDetail;
import com.hosjoy.core.page.PageInfo;
import com.hosjoy.dubbo.api.model.common.BaseModel;
import com.hosjoy.dubbo.api.model.common.BaseModelInfo;
import com.hosjoy.dubbo.api.service.IPayDetailService;
import com.hosjoy.dubbo.provider.util.PageUtil;

@Service
@Transactional
public class PayDetailServiceImpl implements IPayDetailService {

	@Autowired 
	PayDetailMapper payDetailMapper;
	
	@Override
	public BaseModelInfo<PayDetail> selectpdById(long id) {
		BaseModelInfo<PayDetail> result = new BaseModelInfo<PayDetail>();
		result.setCode("200");
		result.setMsg("success");
		result.setInfo(payDetailMapper.selectById(id));
		return result;
	}

	@Override
	public BaseModelInfo<List<PayDetail>> selectNumList(Integer num) {
		BaseModelInfo<List<PayDetail>> res = new BaseModelInfo<>();
		PageInfo pi = PageUtil.getPage(1, 5);
		List<PayDetail> list = payDetailMapper.getSomeRdsListPage(num, pi);
		pi = PageUtil.setPager(pi);
		res.setCode("200");
		res.setMsg("success");
		res.setInfo(list);
		res.setPage(pi);
		return res;
	}

	@Override
	public BaseModel insertLogPd(PayDetail pd) {
		BaseModel result = new BaseModel();
		try {
			result.setCode("200");
			result.setMsg("success");
			int  a = payDetailMapper.insert(pd);
			int n = a/0;
			System.out.println(n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//catch中需要手动回滚
			result.setCode("300");
			result.setMsg("fail");
		}
		return result;
	}

}
