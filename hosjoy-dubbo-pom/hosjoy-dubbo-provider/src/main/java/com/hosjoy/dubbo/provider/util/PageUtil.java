package com.hosjoy.dubbo.provider.util;

import com.hosjoy.core.page.PageInfo;

public class PageUtil {
	public static PageInfo getPage(Integer pno,Integer psize){
		PageInfo page = new PageInfo();//分页信息
		int currentPage = pno == null ? 1 : pno;
		int pageSize = psize == null? 10 : psize;
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		int currentResult = (currentPage - 1) * pageSize;
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		page.setCurrentPage(currentPage);
		return page;
	}
	
	public static PageInfo setPager(PageInfo page) {
		int pageSize = page.getShowCount();
		int totalCount = page.getTotalResult();
		int totalPage = 0;
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = 1 + totalCount / pageSize;
		}
		page.setTotalPage(totalPage);
		return page;

	}
	
}
