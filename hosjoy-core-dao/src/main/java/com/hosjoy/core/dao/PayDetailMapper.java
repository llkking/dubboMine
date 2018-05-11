package com.hosjoy.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hosjoy.core.model.PayDetail;
import com.hosjoy.core.page.PageInfo;

public interface PayDetailMapper {
    int deleteById(Long id);

    int insert(PayDetail record);

    int insertSelective(PayDetail record);

    PayDetail selectById(Long id);

    int updateByIdSelective(PayDetail record);

    int updateById(PayDetail record);
    
    List<PayDetail> getSomeRdsListPage(@Param("num") Integer num,@Param("page")PageInfo pageIfo);
}