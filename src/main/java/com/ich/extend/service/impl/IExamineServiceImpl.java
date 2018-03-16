package com.ich.extend.service.impl;


import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.extend.dao.IExamineMapper;
import com.ich.extend.pojo.IExamine;
import com.ich.extend.service.IExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IExamineServiceImpl implements IExamineService {

    @Autowired
    private IExamineMapper examineMapper;

    @Override
    public HttpResponse addExamine(IExamine examine, boolean isnew) {
        if(ObjectHelper.isEmpty(examine.getSource())) return new HttpResponse(HttpResponse.HTTP_ERROR,"来源信息不完整");
        if(ObjectHelper.isEmpty(examine.getSourceid())) return new HttpResponse(HttpResponse.HTTP_ERROR,"来源信息不完整");

        if(ObjectHelper.isEmpty(examine.getHandlername())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入审核人员名称");
        if(ObjectHelper.isEmpty(examine.getHandleresult())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入审核结果");
        if(ObjectHelper.isEmpty(examine.getHandletime()))  examine.setHandletime(new Date());
        examine.setId(IDUtils.createUUId());
        Integer sourcegroup = examineMapper.selectMaxGroup(examine.getSource(),examine.getSourceid());
        if(ObjectHelper.isEmpty(sourcegroup)) sourcegroup = 1;
        if(isnew){
            examine.setSourcegroup(sourcegroup+1);
        }else{
            examine.setSourcegroup(sourcegroup);
        }
        examineMapper.insert(examine);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public HttpResponse addExamines(IExamine examine, String sourceids, boolean isnew) {
        if(ObjectHelper.isEmpty(examine.getSource())) return new HttpResponse(HttpResponse.HTTP_ERROR,"来源信息不完整");
        if(ObjectHelper.isEmpty(sourceids)) return new HttpResponse(HttpResponse.HTTP_ERROR,"来源信息不完整");
        if(ObjectHelper.isEmpty(examine.getHandlername())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入审核人员名称");
        if(ObjectHelper.isEmpty(examine.getHandleresult())) return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入审核结果");
        if(ObjectHelper.isEmpty(examine.getHandletime()))  examine.setHandletime(new Date());
        String arr[] = sourceids.split(",");
        for(String sourceid : arr){
            examine.setId(IDUtils.createUUId());
            examine.setSourceid(sourceid);
            Integer sourcegroup = examineMapper.selectMaxGroup(examine.getSource(),sourceid);
            if(ObjectHelper.isEmpty(sourcegroup)) sourcegroup = 1;
            if(isnew){
                examine.setSourcegroup(sourcegroup+1);
            }else{
                examine.setSourcegroup(sourcegroup);
            }
            examineMapper.insert(examine);
        }
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public List<IExamine> findNewList(Integer source, String sourceid) {
        Integer sourcegroup = examineMapper.selectMaxGroup(source,sourceid);
        return findList(source,sourceid,sourcegroup);
    }

    @Override
    public List<List<IExamine>> findHisList(Integer source, String sourceid) {
        Integer sourcegroup = examineMapper.selectMaxGroup(source,sourceid);
        List<List<IExamine>> result = new ArrayList<>();
        for(int i=sourcegroup;i>=0;i--){
            result.add(findList(source,sourceid,i));
        }
        return result;
    }

    public List<IExamine> findList(Integer source, String sourceid,Integer sourcegroup) {
        return examineMapper.selectListOfGroup(source,sourceid,sourcegroup);
    }

}
