package com.ich.core.file;

import com.ich.core.base.ObjectHelper;
import com.ich.core.base.RandomInt;
import com.ich.core.base.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 文件操作工具类
 * @author 霍俊
 */
public class FileUtil {
	
	public static ClassLoader loader = Thread.currentThread().getContextClassLoader();

	/**
	 * 依据原始文件名生成新的随机文件名称
	 * 生成规则：当前时间戳 + 五位随机数 + 文件后缀
	 * @return 随机文件名称（全小写）
	 */
	public static String createRandomFileName( String fileName ) {
		return System.currentTimeMillis() + RandomInt.findNum(5) + getFileExt(fileName);
	}
	/**
	 * 获取文件后缀名
	 * @return 后缀名
	 */
	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	}
	/**
	 * 检查文件格式，仅后缀
	 * 2015-6-16-下午2:48:57
	 * @param fileName 文件名
	 * @param filePatten 文件类型
	 * @return
	 */
	@Deprecated
	public static boolean checkFileType(String fileName, String[] filePatten) {
		Iterator<String> type = Arrays.asList( filePatten ).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据根目录，创建不同时间的文件夹
	 * 2015-6-19-下午5:30:21
	 * @param rootPath 根目录
	 * @return 创建的文件全路径
	 */
	public static String createTimesDirs(String rootPath){
		String yyyyMMdd = TimeUtil.format("yyyyMMdd");
		rootPath = rootPath + "/" + yyyyMMdd ;
		File dir = new File( rootPath );
		if(!dir.exists()) dir.mkdirs();
		return rootPath;
	}

	/**
	 * 创建路径
	 * @param path 路径
	 * @return 路径
	 */
	public static String createDirs(String path){
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs();
		return path;
	}

	/**
	 * 获取匹配到的所有资源列表（只匹配当前路径下的，不取子级）
	 * @param path 路径,jar,file路径
	 * @param suffix 后缀
	 * @throws IOException
	 */
	public static List<String> getResourceFile(String path, String suffix)
			throws IOException {
		List<String> myClassName = new ArrayList<String>();
		String packagePath = path.replace(".", "/");
		Enumeration<URL> urls = loader.getResources(packagePath);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			String type = url.getProtocol();
			if (type.equals("file")) {
				myClassName.addAll(getClassNameByFile(url.getPath(), suffix));
			} else if (type.equals("jar")) {
				myClassName.addAll(getClassNameByJar(url.getPath(), suffix));
			}
		}
		return myClassName;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByFile(String filePath,
			String suffix) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (!childFile.isDirectory()) {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(suffix)) {
					myClassName.add(childFilePath);
				}
			}
		}
		return myClassName;
	}

	/**
	 * 从jar获取某包下所有类
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByJar(String jarPath, String suffix) {
		List<String> myClassName = new ArrayList<String>();
		String[] jarInfo = jarPath.split("!");
		String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
		String packagePath = jarInfo[1].substring(1);
		try {
			JarFile jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				String entryName = jarEntry.getName();
				if (entryName.endsWith(suffix)) {
					int index = entryName.lastIndexOf("/");
					String myPackagePath;
					if (index != -1) {
						myPackagePath = entryName.substring(0, index);
					} else {
						myPackagePath = entryName;
					}
					if (myPackagePath.equals(packagePath)) {
						myClassName.add(entryName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClassName;
	}
	

}
