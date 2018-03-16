package com.ich.file.service.impl;

import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.file.dao.FileResourceMapper;
import com.ich.file.pojo.FileResource;
import com.ich.file.service.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileResourceServiceImpl implements FileResourceService {

    @Autowired
    private FileResourceMapper fileResourceMapper;

    @Override
    public HttpResponse addFileResource(FileResource fileResource) {
        fileResource.setId(IDUtils.createUUId());
        fileResource.setCreatetime(new Date());
        fileResourceMapper.insert(fileResource);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse editFileResource(FileResource fileResource) {
        FileResource entity = fileResourceMapper.selectById(fileResource.getId());
        if(ObjectHelper.isEmpty(entity)) return new HttpResponse(HttpResponse.HTTP_ERROR,"未找到文件信息");
        fileResourceMapper.update(fileResource);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse deleteFileByIds(String ids) {
        String id_array[] = ids.split(",");
        for (String id : id_array){
            fileResourceMapper.deleteById(id);
        }
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse deleteFileBySource(Integer source, String sourceid) {
        fileResourceMapper.deleteBySource(source,sourceid);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public FileResource findById(String id) {
        return fileResourceMapper.selectById(id);
    }

    @Override
    public List<FileResource> findBySource(Integer source, String sourceid) {
        return fileResourceMapper.selectBySource(source,sourceid);
    }
}
