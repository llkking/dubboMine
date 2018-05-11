package com.hosjoy.dubbo.provider.util.interFace;

import java.util.List;

/**
 *  Utils Service 基础服务接口
 * @author Administrator
 *
 */
public interface IUtilsService {
	
	 
	 /**
	 * 发送邮件(list.size>1为群发)Util类
	 * @return
	 */
	 boolean sendEmailService(List<String> receiverEmails,String context,String topic) throws Exception ;

}
