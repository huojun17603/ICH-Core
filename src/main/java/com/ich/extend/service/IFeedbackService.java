package com.ich.extend.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.extend.pojo.IFeedback;

import java.util.List;

public interface IFeedbackService {
    /**
     * 添加反馈信息，不被允许修改
     * @param feedback 数据
     * @return 是否完成
     */
    public HttpResponse addFeedback(IFeedback feedback);

    /**
     * 改变状态
     * @param id ID
     * @param status 状态
     * @return 是否完成
     */
    public HttpResponse editFeedbackStatus(String id,Integer status);

    /**
     * 删除反馈信息，这能数据应该是无需页面上做删除的，所有就不提供批量删除了
     * @param id ID
     * @return 是否完成
     */
    public HttpResponse deleteFeedback(String id);

    /**
     * 分页搜索
     * @param view 分页数据，包含总记录数的返回
     * @param searchkey 搜索内容，可以是名字、联系方式、内容
     * @return 列表
     */
    public List<IFeedback> query(PageView view,String searchkey);
}
