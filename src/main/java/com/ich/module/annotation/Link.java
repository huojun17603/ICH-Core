package com.ich.module.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统菜单定义注解<br/>
 * 系统启动时通过读取此注解生成菜单、链接信息<br/>
 * @author 霍俊
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Link {
	
	/**资源类别：目录【点击后仅打开下级菜单】:从资源文件<modular>中注册*/
	public final static int TYPE_CATALOG = 1;
	/**资源类别：菜单【点击后打开主页面】：由传输层定义 */
	public final static int TYPE_MENU = 2;
	/**资源类别：链接【附属于菜单的链接】：由传输层定义 */
	public final static int TYPE_LINK = 3;
	
	/**资源级别：无 */
	public final static int LEVEL_NONE = -1;
	/**资源级别：读取 */
	public final static int LEVEL_READ = 0;
	/**资源级别：新增 */
	public final static int LEVEL_WRITE = 1;
	/**资源级别：修改 */
	public final static int LEVEL_EDIT = 2;
	/**资源级别：审核 */
	public final static int LEVEL_AUDIT = 3;
	/**资源级别：删除 */
	public final static int LEVEL_DETELE = 4;
	
	/** 菜单名称 */
	String name();
	/** 菜单Code */
	String code();
	/** 父菜单Code */
	String parent();
	/** 菜单权限类型 */
	int level();
	/** 链接说明 */
	String doc() default "";
	
}
