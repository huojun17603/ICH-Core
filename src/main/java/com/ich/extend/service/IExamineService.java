package com.ich.extend.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.extend.pojo.IExamine;

import java.util.List;

/**
 * 通用审核（处理）业务层
 */
public interface IExamineService {

    /** 新增 */
    public HttpResponse addExamine(IExamine examine,boolean isnew);

    /** 新增，针对批量处理 */
    public HttpResponse addExamines(IExamine examine,String sourceids,boolean isnew);

    public List<IExamine> findNewList(Integer source, String sourceid);

    public List<List<IExamine>> findHisList(Integer source,String sourceid);

}
