package com.ich.core.file;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

/**
 * 二维码生成工具
 * @author 霍俊
 */
public class QRCode {

    /**
     * 生成二维码（无LOGO）
     * @param url 二维码链接
     * @param qrcode_width 图片宽度
     * @param qrcode_height 图片高度
     * @return BufferedImage
     * @throws Exception
     */
    public static BufferedImage createQR(String url, int qrcode_width, int qrcode_height) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
                BarcodeFormat.QR_CODE, qrcode_width, qrcode_height, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     * 生成二维码（有LOGO）
     * @param url 二维码链接
     * @param logoFile LOGO文件
     * @param qrcode_width 图片宽度
     * @param qrcode_height 图片高度
     * @return BufferedImage
     * @throws Exception
     */
    public static BufferedImage createLogoQR(String url, File logoFile, int qrcode_width, int qrcode_height) throws Exception {
        BufferedImage image = createQR(url,qrcode_width,qrcode_height);
        Graphics2D gs = image.createGraphics();
        //载入logo
        BufferedImage bimage = ImageIO.read(logoFile);
        int width = (qrcode_width/2)-(bimage.getWidth()/2);
        int height = (qrcode_height/2)-(bimage.getHeight()/2);
        Image img = ImageIO.read(logoFile);
        gs.drawImage(img, width, height, null);
        gs.dispose();
        img.flush();
        return image;
    }

//    public static void main(String[] args) throws IOException {
//        OutputStream outXlsx = new FileOutputStream("E://b1.jpg");
//        try {
//            //BufferedImage image =  createQR("http://www.baidu.com", 300, 300);
//            File logoFile = new File("D://32.png");
//            BufferedImage image =  createLogoQR("http://www.uqudmall.com/jump.html",logoFile, 115, 115);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outXlsx);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
//            param.setQuality(1.0f, false);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(image);
//        }catch (Exception e){
//
//        }
//    }

}
