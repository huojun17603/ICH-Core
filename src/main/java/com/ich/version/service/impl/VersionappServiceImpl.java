package com.ich.version.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.version.dao.VersionappMapper;
import com.ich.version.pojo.Versionapp;
import com.ich.version.service.VersionappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionappServiceImpl implements VersionappService {

    @Autowired
    private VersionappMapper versionappMapper;


    @Override
    public HttpResponse addOrEdit(Versionapp versionapp) {
        if(ObjectHelper.isEmpty(versionapp.getAppname())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入应用名称！");
        if(ObjectHelper.isEmpty(versionapp.getEquipment())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入设备信息！");
        if(ObjectHelper.isEmpty(versionapp.getVersion())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入版本信息！");
        if(ObjectHelper.isEmpty(versionapp.getId())){
            Versionapp entity = this.versionappMapper.selectByVersion(versionapp.getEquipment(),versionapp.getAppname(),versionapp.getVersion());
            if(ObjectHelper.isNotEmpty(entity)) return new HttpResponse(HttpResponse.HTTP_ERROR,"重复的版本信息！");
            this.versionappMapper.insert(versionapp);
        }else{
            Versionapp entity = this.versionappMapper.selectById(versionapp.getId());
            if(ObjectHelper.isEmpty(entity)) return new HttpResponse(HttpResponse.HTTP_ERROR,"无效的版本信息！");
            entity.setFilezise(versionapp.getFilezise());
            entity.setForce(versionapp.getForce());
            entity.setHttp(versionapp.getHttp());
            entity.setRemark(versionapp.getRemark());
            this.versionappMapper.update(entity);
        }
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse editToNewest(Long id) {
        Versionapp entity = this.versionappMapper.selectById(id);
        if(ObjectHelper.isEmpty(entity))return new HttpResponse(HttpResponse.HTTP_ERROR,"无效的版本信息！");
        if(entity.getStatus()!=1)return new HttpResponse(HttpResponse.HTTP_ERROR,"只允许发布待发布的版本信息！");
        this.versionappMapper.updateAllToHistory(entity.getEquipment(), entity.getAppname());
        this.versionappMapper.updateToNewest(id);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse deleteVersion(Long id) {
        Versionapp entity = this.versionappMapper.selectById(id);
        if(ObjectHelper.isEmpty(entity))return new HttpResponse(HttpResponse.HTTP_ERROR,"无效的版本信息！");
        if(entity.getStatus()!=1)return new HttpResponse(HttpResponse.HTTP_ERROR,"只允许删除未发布的版本信息！");
        this.versionappMapper.delete(id);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public Versionapp findById(Long id) {
        return this.versionappMapper.selectById(id);
    }

    @Override
    public Versionapp findByNewest(String equipment, String appname) {
        return this.versionappMapper.selectByNewest(equipment,appname);
    }

    @Override
    public List<Versionapp> findByHistory(String equipment, String appname) {
        return this.versionappMapper.selectByHistory(equipment,appname);
    }

    @Override
    public List<Versionapp> queryVersion(PageView view, String equipment, String appname) {
        PageHelper.startPage(view.getPage(),view.getRows());
        List<Versionapp> result = versionappMapper.selectByExample(equipment,appname);
        PageInfo<Versionapp> pageInfo = new PageInfo<>(result);
        view.setRowCount(pageInfo.getTotal());
        return result;
    }
}
