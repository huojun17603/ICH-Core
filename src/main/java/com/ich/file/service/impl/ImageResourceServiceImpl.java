package com.ich.file.service.impl;

import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.listener.SystemConfig;
import com.ich.file.dao.ImageResourceMapper;
import com.ich.file.pojo.ImageResource;
import com.ich.file.service.ImageResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageResourceServiceImpl implements ImageResourceService {

    @Autowired
    private ImageResourceMapper imageResourceMapper;

    @Override
    public HttpResponse addImage(ImageResource image) {
//        if(ObjectHelper.isEmpty(image.getSource())) return new HttpResponse(HttpResponse.HTTP_ERROR,"来源信息不完整");
//        if(ObjectHelper.isEmpty(image.getSourceid())) return new HttpResponse(HttpResponse.HTTP_ERROR,"来源信息不完整");
        image.setId(IDUtils.createUUId());
        Integer onum = imageResourceMapper.selectMaxNumOfSource(image.getSource(), image.getSourceid());
        image.setOnum(onum+1);
        image.setCreatetime(new Date());
        imageResourceMapper.insert(image);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse editImage(ImageResource image) {
        ImageResource entity = imageResourceMapper.selectById(image.getId());
        if(ObjectHelper.isEmpty(entity)) return new HttpResponse(HttpResponse.HTTP_ERROR,"未找到图片信息");
        imageResourceMapper.update(image);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse editImageOnumOfReplace(String id, String tagertid) {
        ImageResource entity = imageResourceMapper.selectById(id);
        ImageResource tagert_entity = imageResourceMapper.selectById(tagertid);
        if(!entity.getSource().equals(tagert_entity.getSource()))
            return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
        if(!entity.getSourceid().equals(tagert_entity.getSourceid()))
            return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
        Integer tmp = entity.getOnum();
        entity.setOnum(tagert_entity.getOnum());
        tagert_entity.setOnum(tmp);
        imageResourceMapper.updateOnum(entity);
        imageResourceMapper.updateOnum(tagert_entity);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse editImageOnumOfExtended(String id, String tagertid) {
        ImageResource entity = imageResourceMapper.selectById(id);
        ImageResource tagert_entity = imageResourceMapper.selectById(tagertid);
        if(!entity.getSource().equals(tagert_entity.getSource()))
            return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
        if(!entity.getSourceid().equals(tagert_entity.getSourceid()))
            return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
        List<ImageResource> list = imageResourceMapper.selectBySource(entity.getSource(),entity.getSourceid());
        boolean sxflag = entity.getOnum() > tagert_entity.getOnum();
        Integer tmp = tagert_entity.getOnum();
        for(ImageResource img : list){
            if(sxflag){
                //源图片序号 大于 目标图片 = 源图片前移，目标图片及关联图片后移
                if(img.getOnum()>=tagert_entity.getOnum()&&img.getOnum()<entity.getOnum()){
                    img.setOnum(img.getOnum()+1);
                }
            }else{
                //源图片序号 小于 目标图片 = 源图片后移，目标图片及关联图片前移
                if(img.getOnum()<=tagert_entity.getOnum()&&img.getOnum()>entity.getOnum()){
                    img.setOnum(img.getOnum()-1);
                }
            }
        }
        entity.setOnum(tmp);
        for(ImageResource img : list){
            imageResourceMapper.updateOnum(img);
        }
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse deleteImageByIds(String ids) {
        String id_array[] = ids.split(",");
        for (String id : id_array){
            imageResourceMapper.deleteById(id);
        }
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse deleteImageBySource(Integer source, String sourceid) {
        imageResourceMapper.deleteBySource(source,sourceid);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public ImageResource findById(String id) {
        return imageResourceMapper.selectById(id);
    }

    @Override
    public List<ImageResource> findBySource(Integer source, String sourceid) {
        return imageResourceMapper.selectBySource(source,sourceid);
    }

    @Override
    public void clearGar() {
        //图片文件根目录，例：D:/project/picture/
        //管理并清理其中图片
        //谨慎配置此选项不然会导致其他文件被删除
//        String IM_CLEAR_ROOT = "C:\\Users\\Administrator\\Desktop\\文档";
//        String IM_MIME_TYPE = "PNG,JPG,JPGE";
        String IM_CLEAR_ROOT = SystemConfig.getParams("IM_CLEAR_ROOT");
        String IM_MIME_TYPE = SystemConfig.getParams("IM_MIME_TYPE");
        if(ObjectHelper.isEmpty(IM_CLEAR_ROOT)) {
            System.out.println("IM_CLEAR_ROOT IS NULL");
            return;
        }
        if(ObjectHelper.isEmpty(IM_MIME_TYPE)) {
            System.out.println("IM_CLEAR_ROOT IS NULL");
            return;
        }
        String IM_MIME_TYPE_ARRAY[] = IM_MIME_TYPE.split(",");
        List<String> filelist = new ArrayList<>();
        traverseFolder(filelist,IM_CLEAR_ROOT);
        for(String AbsolutePath : filelist){
            String suffix = AbsolutePath.substring(AbsolutePath.lastIndexOf(".")+1);
            String randomname = AbsolutePath.substring(AbsolutePath.lastIndexOf(File.separator)+1);
            boolean flag = false;
            for(String mime : IM_MIME_TYPE_ARRAY){
                if(mime.equals(suffix)) flag = true;
            }
            if(flag){
                if(randomname.indexOf("_")!=-1){
                    randomname = randomname.substring(0,randomname.indexOf("_"))+"."+suffix;//扩展的根据原图处理
                    System.out.println("KZ:"+randomname);
                }
                List<ImageResource> list = imageResourceMapper.selectByName(randomname);
                flag = false;
                for(ImageResource img : list){
                    String path = img.getPath().substring(0,(img.getPath().length()-suffix.length()));
                    System.out.println("KZ:"+path);
                    if(AbsolutePath.lastIndexOf(path)!=-1) flag = true;
                }
                if(!flag){
                    File file = new File(AbsolutePath);
                    file.delete();
                }
            }
        }
    }

    private static void traverseFolder(List<String> filelist,String path){
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(filelist,file2.getAbsolutePath());
                    } else {
                        filelist.add(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            return;
        }
    }


}
