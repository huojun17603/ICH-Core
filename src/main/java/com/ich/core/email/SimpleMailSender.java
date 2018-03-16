package com.ich.core.email;

import com.ich.core.base.ObjectHelper;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * 邮件发送器
 * 功能：提供一套简单和一套复杂的邮件发送方法
 * @author 霍俊
 *
 */
public class SimpleMailSender {
		/**
	     * 发送邮件的props文件
	     */
	    private final transient Properties props = System.getProperties();
	    /**
	     * 邮件服务器登录验证
	     */
	    private transient MailAuthenticator authenticator;
	 
	    /**
	     * 邮箱session
	     */
	    private transient Session session;
	 
	    /**
	     * 初始化邮件发送器
	     * @param smtpHostName SMTP邮件服务器地址
	     * @param username 发送邮件的用户名(地址)
	     * @param password 发送邮件的密码
	     */
	    public SimpleMailSender(final String smtpHostName, final String username,
	        final String password) {
	    	init(username, password, smtpHostName);
	    }
	 
	    /**
	     * 初始化邮件发送器
	     * @param username 发送邮件的用户名(地址)，并以此解析SMTP服务器地址
	     * @param password 发送邮件的密码
	     */
	    public SimpleMailSender(final String username, final String password) {
		    //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		    final String smtpHostName = "smtp." + username.split("@")[1];
		    init(username, password, smtpHostName);
	 
	    }
	 
	    /**
	     * 初始化
	     * @param username 发送邮件的用户名(地址)
	     * @param password 密码
	     * @param smtpHostName SMTP主机地址
	     */
	    private void init(String username, String password, String smtpHostName) {
		    System.out.println(username);
	    	// 初始化props
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", smtpHostName);
		    props.put("mail.smtp.port", "25");
		    //props.put("", value)
		    // 验证
		    authenticator = new MailAuthenticator(username, password);
		    // 创建session
		    session = Session.getInstance(props, authenticator);
	    }
	    
	    
	    /**
	     * 发送HTML格式邮件
	     * @param recipients 收件人们（可单发可群发）
	     * @param subject 主题
	     * @param content 类容（HTML格式）
	     * @param resources <key>:资源ID；<value>资源地址
	     * @param attchs 附件
	     * @throws MessagingException 
	     * @throws AddressException 
	     * @throws UnsupportedEncodingException 
	     */
	    public void sendComplexEmail(List<String> recipients, String subject, 
	    		Object content,Map<String,String> resources,List<String> attchs) throws AddressException, MessagingException, UnsupportedEncodingException{
	    	// 创建mime类型邮件
		    final MimeMessage message = new MimeMessage(session);
		    // 设置发信人
		    message.setFrom(new InternetAddress(authenticator.getUsername()));
		    // 设置收件人们
		    final int num = recipients.size();
		    if(num==1){
		    	message.setRecipient(RecipientType.TO, new InternetAddress(recipients.get(0)));	
		    }else{
			    InternetAddress[] addresses = new InternetAddress[num];
			    for (int i = 0; i < num; i++) {
			        addresses[i] = new InternetAddress(recipients.get(i));
			    }
			    message.setRecipients(RecipientType.TO, addresses);
		    }
		    // 设置主题
		    message.setSubject(subject);
		    //设置正文关联
		    MimeMultipart mimeMultipart1 = new MimeMultipart("related");
		    MimeMultipart mimeMultipart2 = new MimeMultipart("mixed");
		    //设置正文
		    MimeBodyPart text = new MimeBodyPart();
		    mimeMultipart1.addBodyPart(text);
		    // setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
		    text.setContent(content,"text/html;charset=utf-8");
		    //设置资源
		    if(ObjectHelper.isNotEmpty(resources)){
		    	for (Map.Entry<String, String> entry : resources.entrySet()) {
		 	        MimeBodyPart resource = new MimeBodyPart();
		 	        /*JavaMail API不限制信息只为文本,任何形式的信息都可能作茧自缚MimeMessage的一部分.
		 	        * 除了文本信息,作为文件附件包含在电子邮件信息的一部分是很普遍的.
		 	        * JavaMail API通过使用DataHandler对象,提供一个允许我们包含非文本BodyPart对象的简便方法.*/
		 	        DataHandler dh = new DataHandler(new FileDataSource(entry.getValue()));
		 	        resource.setDataHandler(dh);
		 	        //创建图片的一个表示用于显示在邮件中显示
		 	        resource.setContentID(entry.getKey());
		 	        mimeMultipart1.addBodyPart(resource);
		    	}
		    }
		    //综合Html格式正文
		    MimeBodyPart htmlBody = new MimeBodyPart();
		    htmlBody.setContent(mimeMultipart1);
		    mimeMultipart2.addBodyPart(htmlBody);
		    //设置附件
		    if(ObjectHelper.isNotEmpty(attchs)){
		    	for(String fileName:attchs){
		    		MimeBodyPart attch = new MimeBodyPart();
		    		DataHandler dataHandler = new DataHandler(new FileDataSource(fileName));
		    		attch.setDataHandler(dataHandler);
		    		// MimeUtility 是一个工具类，encodeText（）用于处理附件字，防止中文乱码问题
				    attch.setFileName(MimeUtility.encodeText(dataHandler.getName()));
				    mimeMultipart2.addBodyPart(attch);
		    	}
		    }
		    message.setContent(mimeMultipart2);
		    message.saveChanges(); //保存修改
		    // 发送
		    Transport.send(message);
		    
	    }
	 
	    /**
	     * 发送邮件(简单的邮箱信息，不包括图片与附件)
	     * @param recipients 收件人们（可单发可群发）
	     * @param subject  邮件主题
	     * @param content  邮件内容
	     * @throws AddressException
	     * @throws MessagingException
	     * @throws UnsupportedEncodingException 
	     */
	    public void sendEasyEmail(List<String> recipients, String subject, Object content)
	        throws AddressException, MessagingException {
	    // 创建mime类型邮件
	    final MimeMessage message = new MimeMessage(session);
	    // 设置发信人
	    message.setFrom(new InternetAddress(authenticator.getUsername()));
	    // 设置收件人们
	    final int num = recipients.size();
	    if(num==1){
	    	message.setRecipient(RecipientType.TO, new InternetAddress(recipients.get(0)));	
	    }else{
		    InternetAddress[] addresses = new InternetAddress[num];
		    for (int i = 0; i < num; i++) {
		        addresses[i] = new InternetAddress(recipients.get(i));
		    }
		    message.setRecipients(RecipientType.TO, addresses);
	    }
	    // 设置主题
	    message.setSubject(subject);
	    // 设置邮件内容
	    message.setContent(content.toString(), "text/html;charset=utf-8");
	    // 发送
	    message.saveChanges(); //保存修改
	    Transport.send(message);
	    }
	    
	    

//	public static void main(String[] args) {
//		SimpleMailSender simpleMailSender = new SimpleMailSender("system@weicai310.com", "Cprt1234");
//		try {
//			Map<String,String> map = new HashMap<String,String>();
//			List<String> list = new ArrayList<String>();
//			List<String> list2 = new ArrayList<String>();
//			list2.add("1046255018@qq.com");
////			simpleMailSender.sendEasyEmail(list2,"JavaHTML邮箱测试", "<h1>大标题</h1><p>世界上最复杂的邮件</p><a href='#'>请点击</a><img src='cid:a'>");
//			simpleMailSender.sendComplexEmail(list2, "JavaHTML邮箱测试", "<h1>大标题</h1><p>世界上最复杂的邮件</p><a href='#'>请点击</a><img src='cid:a'>", map, list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}

