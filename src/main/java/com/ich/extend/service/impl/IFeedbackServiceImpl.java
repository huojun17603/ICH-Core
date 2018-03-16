package com.ich.extend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ich.core.base.IDUtils;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.extend.dao.IFeedbackMapper;
import com.ich.extend.pojo.IFeedback;
import com.ich.extend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IFeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private IFeedbackMapper feedbackMapper;

    @Override
    public HttpResponse addFeedback(IFeedback feedback) {
        feedback.setId(IDUtils.createUUId());
        feedback.setCreatetime(new Date());
        feedback.setStatus(0);
        int result = feedbackMapper.insert(feedback);
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }

    @Override
    public HttpResponse editFeedbackStatus(String id, Integer status) {
        int result = feedbackMapper.updateStatus(id,status);
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }

    @Override
    public HttpResponse deleteFeedback(String id) {
        int result = feedbackMapper.delete(id);
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }

    @Override
    public List<IFeedback> query(PageView view, String searchkey) {
        PageHelper.startPage(view.getPage(),view.getRows());
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("searchkey",searchkey);
        List<IFeedback> result = feedbackMapper.selectOfList(searchkey);
        PageInfo<IFeedback> pageInfo = new PageInfo<>(result);
        view.setRowCount(pageInfo.getTotal());
        return result;
    }
}
