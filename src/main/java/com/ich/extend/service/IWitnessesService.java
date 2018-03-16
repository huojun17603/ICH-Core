package com.ich.extend.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.extend.pojo.IWitnesses;

import java.util.List;

public interface IWitnessesService {
    /**
     * 添加举报信息，同一举报人在同一时间内，仅允许有一条针对同一被举报人的数据
     * @param witnesses 数据
     * @return 是否完成
     */
    public HttpResponse addWitnesses(IWitnesses witnesses);

    /**
     * 处理完成，针对被举报人一次全部处理
     * @param wid 被举报人ID
     * @return 是否完成
     */
    public HttpResponse updateWitnessesOfHandle(String wid);

    /**
     * 查询，针对被举报人显示其统计数量
     * @param view 分页数据
     * @param status 状态
     * @return 列表
     */
    public List<IWitnesses> queryWitnessesGourpByWid(PageView view, Integer status);

    /**
     * 查询，针对被举报人显示其被举报的明细
     * @param view 分页数据
     * @param wid 被举报人ID
     * @return 列表
     */
    public List<IWitnesses> queryWitnessesWidList(PageView view,String wid);

}
