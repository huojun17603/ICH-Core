package com.ich.core.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件MimeType工具类
 * @since  2016-2-23
 * @author 霍俊
 */
public class MimeType {
	
	
//	JPEG (jpg)，文件头：FFD8FF
	public static final String JPG = "FFD8FF";
//	PNG (png)，文件头：89504E47
	public static final String PNG = "89504E47";
//	GIF (gif)，文件头：47494638
	public static final String GIF = "47494638";
//	TIFF (tif)，文件头：49492A00
	public static final String TIF = "49492A00";
//	Windows Bitmap (bmp)，文件头：424D
	public static final String BMP = "424D";
//	CAD (dwg)，文件头：41433130
	public static final String DWG = "41433130";
//	Adobe Photoshop (psd)，文件头：38425053
	public static final String PSD = "38425053";
//	Rich Text Format (rtf)，文件头：7B5C727466
	public static final String RTF = "7B5C727466";
//	XML (xml)，文件头：3C3F786D6C
	public static final String XML = "3C3F786D6C";
//	HTML (html)，文件头：68746D6C3E
	public static final String HTML = "68746D6C3E";
//	Email [thorough only] (eml)，文件头：44656C69766572792D646174653A
	public static final String EML = "44656C69766572792D646174653A";
//	Outlook Express (dbx)，文件头：CFAD12FEC5FD746F
	public static final String DBX = "CFAD12FEC5FD746F";
//	Outlook (pst)，文件头：2142444E 
	public static final String PST = "2142444E";
//	MS Word/Excel (xls.or.doc)，文件头：D0CF11E0
	public static final String XLS = "D0CF11E0";
	public static final String DOC = "D0CF11E0";
//	MS Access (mdb)，文件头：5374616E64617264204A
	public static final String MDB = "5374616E64617264204A";
//	WordPerfect (wpd)，文件头：FF575043
	public static final String WPD = "FF575043";
//	Postscript. (eps.or.ps)，文件头：252150532D4164
	public static final String EPS = "252150532D4164";
	public static final String PS = "252150532D4164";
//	Adobe Acrobat (pdf)，文件头：255044462D312E
	public static final String PDF = "255044462D312E";
//	Quicken (qdf)，文件头：AC9EBD8F 
	public static final String QDF = "AC9EBD8F";
//	Windows Password (pwl)，文件头：E3828596 
	public static final String PWL = "E3828596";
//	ZIP Archive (zip)，文件头：504B0304 
	public static final String ZIP = "504B0304";
//	RAR Archive (rar)，文件头：52617221 
	public static final String RAR = "52617221";
//	Wave (wav)，文件头：57415645 
	public static final String WAV = "57415645";
//	AVI (avi)，文件头：41564920 
	public static final String AVI = "41564920";
//	Real Audio (ram)，文件头：2E7261FD 
	public static final String RAM = "2E7261FD";
//	Real Media (rm)，文件头：2E524D46 
	public static final String RM = "2E524D46";
//	MPEG (mpg)，文件头：000001B
	public static final String MPG = "000001B";
//	Quicktime (mov)，文件头：6D6F6F76 
	public static final String MOV = "6D6F6F76";
//	Windows Media (asf)，文件头：3026B2758E66CF11 
	public static final String ASF = "3026B2758E66CF11";
//	MIDI (mid)，文件头：4D546864
	public static final String MID = "4D546864";

//	public static void main(String[] agrs) throws Exception{
//		File file = new File("C:/1.png");
//		InputStream in = new FileInputStream(file);
//		byte[] data = toByteArray(in);
//		in.close();
//		System.out.println(filter(data,"png"));
//	}

	private static byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}
	/** 
	 * 文件类型及头编码 
	 * key:<文件后缀>,value:<类容编码>
	 *  */
	private static Map<String,String> MimeHeadMap = new HashMap<String, String>();
	
	static{ //注入数据
		MimeHeadMap.put("JPG", JPG);
		MimeHeadMap.put("PNG", PNG);
		MimeHeadMap.put("GIF", GIF);
		MimeHeadMap.put("TIF", TIF);
		MimeHeadMap.put("BMP", BMP);
		MimeHeadMap.put("DWG", DWG);
		MimeHeadMap.put("PSD", PSD);
		MimeHeadMap.put("RTF", RTF);
		MimeHeadMap.put("XML", XML);
		MimeHeadMap.put("HTML", HTML);
		MimeHeadMap.put("EML", EML);
		MimeHeadMap.put("DBX", DBX);
		MimeHeadMap.put("PST", PST);
		MimeHeadMap.put("XLS", XLS);
		MimeHeadMap.put("DOC", DOC);
		MimeHeadMap.put("MDB", MDB);
		MimeHeadMap.put("WPD", WPD);
		MimeHeadMap.put("EPS", EPS);
		MimeHeadMap.put("PS", PS);
		MimeHeadMap.put("PDF", PDF);
		MimeHeadMap.put("QDF", QDF);
		MimeHeadMap.put("ZIP", ZIP);
		MimeHeadMap.put("RAR", RAR);
		MimeHeadMap.put("WAV", WAV);
		MimeHeadMap.put("AVI", AVI);
		MimeHeadMap.put("RAM", RAM);
		MimeHeadMap.put("RM", RM);
		MimeHeadMap.put("MPG", MPG);
		MimeHeadMap.put("MOV", MOV);
		MimeHeadMap.put("ASF", ASF);
		MimeHeadMap.put("MID", MID);
	}

	private static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();     
        if (src == null || src.length <= 0) {     
            return null;     
        }     
        for (int i = 0; i < 30; i++) {     
            int v = src[i] & 0xFF;     
            String hv = Integer.toHexString(v);     
            if (hv.length() < 2) {     
                stringBuilder.append(0);     
            }     
            stringBuilder.append(hv);     
        }     
        return stringBuilder.toString();     
    }

	/**
	 * 验证文件头信息是否符合上述规定
	 * @param src 源文件byte
	 * @param ext 源文件后缀
	 * @return 验证结果
	 */
	public static boolean filter(byte[] src,String ext){
        if(MimeHeadMap.containsKey(ext.toUpperCase())){
        	String type = bytesToHexString(src).toUpperCase();
        	if(type.contains(MimeHeadMap.get(ext.toUpperCase())))return true;
        }
        return false;
    }

}
