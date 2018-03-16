package com.ich.extend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.extend.dao.IWitnessesMapper;
import com.ich.extend.pojo.IWitnesses;
import com.ich.extend.service.IWitnessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IWitnessesServiceImpl implements IWitnessesService {

    @Autowired
    private IWitnessesMapper witnessesMapper;

    @Override
    public HttpResponse addWitnesses(IWitnesses witnesses) {
        //在管理者确认处理后才可以再次对同一用户举报
        List<IWitnesses> list = witnessesMapper.selectByRepeat(witnesses.getAid(),witnesses.getWid());
        if(ObjectHelper.isNotEmpty(list)){
            return new HttpResponse(HttpResponse.HTTP_ERROR, "不可重复举报");
        }
        witnesses.setId(IDUtils.createUUId());
        witnesses.setCreatetime(new Date());
        witnesses.setStatus(0);
        int result=witnessesMapper.insert(witnesses);
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);

    }

    @Override
    public HttpResponse updateWitnessesOfHandle(String wid) {
        witnessesMapper.updateWitnessesOfHandle(wid);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public List<IWitnesses> queryWitnessesGourpByWid(PageView view, Integer status) {
        PageHelper.startPage(view.getPage(),view.getRows());
        List<IWitnesses> list = witnessesMapper.selectWitnessesGourpByWid(status);
        PageInfo<IWitnesses> pageInfo = new PageInfo<>(list);
        view.setRowCount(pageInfo.getTotal());
        return list;
    }

    @Override
    public List<IWitnesses> queryWitnessesWidList(PageView view, String wid) {
        PageHelper.startPage(view.getPage(),view.getRows());
        List<IWitnesses> list = witnessesMapper.selectWitnessesWidList(wid);
        PageInfo<IWitnesses> pageInfo = new PageInfo<>(list);
        view.setRowCount(pageInfo.getTotal());
        return list;
    }
}
