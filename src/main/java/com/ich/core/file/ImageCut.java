package com.ich.core.file;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

/**
 * 图片处理工具
 * @author 霍俊
 */
public class ImageCut {
	
//	public static void main(String[] args) {
//		String srcImageFile = "F:\\BaiduYunDownload\\Chrysanthemum.jpg";
//		String dirImageFile = "F:\\BaiduYunDownload\\Chrysanthemum_100.jpg";
////		ImageCut.abscut(srcImageFile,dirImageFile,50,0,50,50);//图片裁剪
//
//		scale(srcImageFile, dirImageFile, 100, 100, 0, 0);//图片压缩
//	}
	
	/**
	 * 图片裁剪
	 * @param srcImageFile 原图
	 * @param dirImageFile 结果图
	 * @param x 开始裁剪X轴
	 * @param y 开始裁剪Y轴
	 * @param destWidth 裁剪宽度
	 * @param destHeight 裁剪高度
	 */
	public static void abscut(String srcImageFile, String dirImageFile, int x,
			int y, int destWidth, int destHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getWidth();
			int srcHeight = bi.getHeight();
			if (srcWidth >= destWidth && srcHeight >= destHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
				img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				ImageIO.write(tag, "JPEG", new File(dirImageFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按比例缩小、放大图片
	 * @param srcImageFile 源图路径
	 * @param result 目标路径
	 * @param scale 比例
	 * @param flag 1：放大；2：缩小
	 */
	public static void scale(String srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile));
			int width = src.getWidth();
			int height = src.getHeight(); 
			if (flag) {
				width = width * scale;
				height = height * scale;
			} else {
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void scale(String srcImageFile, String result, int _width,
			int _height) {
		scale(srcImageFile, result, _width, _height, 0, 0);
	}

	public static void scale(String srcImageFile, String result, int _width,
			int _height, int x, int y) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); 
			int width = src.getWidth(); 
			int height = src.getHeight();
			if (width > _width) {
				width = _width;
			}
			if (height > _height) {
				height = _height;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, x, y, null); 
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void convert(String source, String result) {
		try {
			File f = new File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gray(String source, String result) {
		try {
			BufferedImage src = ImageIO.read(new File(source));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}