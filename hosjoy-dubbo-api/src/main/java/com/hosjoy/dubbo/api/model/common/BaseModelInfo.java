package com.hosjoy.dubbo.api.model.common;

import java.io.Serializable;

import com.hosjoy.core.page.PageInfo;

public class BaseModelInfo<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;
	
	private T info;
	
	private PageInfo page;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "BaseModelInfo [code=" + code + ", msg=" + msg + ", info=" + info + ", page=" + page + "]";
	}


}
