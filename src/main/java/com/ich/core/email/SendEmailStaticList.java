/**
 * 
 */
package com.ich.core.email;

import java.util.ArrayList;
import java.util.List;

/**
 * email发送实例
 * @author 霍俊
 *
 */
public class SendEmailStaticList {
	public static List<SimpleMailSender> emailEntities = new ArrayList<SimpleMailSender>();
	
	//构建发送邮件实体对象
	SendEmailStaticList(){
		emailEntities.add(new SimpleMailSender("system@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-a@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-b@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-c@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-d@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-e@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-f@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-g@weicai310.com", "Cprt1234"));
		emailEntities.add(new SimpleMailSender("system-h@weicai310.com", "Cprt1234"));
	}
	
	/**
	 * 随机返回一个邮箱实体
	 * @return
	 */
	public static SimpleMailSender suiJISendEmail() {
		SendEmailStaticList emailStaticList = new  SendEmailStaticList();
		int i = (int) (Math.random()*9);
		return emailEntities.get(i);
	}
	
}
