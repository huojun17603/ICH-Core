package com.ich.extend.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.extend.dto.ICategoryTree;
import com.ich.extend.pojo.ICategory;

import java.util.List;

public interface ICategoryService {

    /**
     * 新增修改数据
     * @param category 类目数据
     * @return 是否完成
     */
    public HttpResponse addOrEditCategory(ICategory category);

    /**
     * 修改状态
     * @param id ID
     * @param status 状态
     * @return 是否完成
     */
    public HttpResponse editCategoryStatus(Long id,Boolean status);

    /**
     * 删除类目【建议不使用】
     * 注意：如果为树形则无法再有子集的情况下删除，如果有其他表使用此类目，请谨慎删除。
     * @param id ID
     * @return 是否完成
     */
    public HttpResponse deleteCategory(Long id);

    /**
     * 根据类型取TREE列表
     * @param pid 父级ID，NULL为ROOT级
     * @param source 类型
     * @param status 状态；NULL代表全部状态
     * @return 列表
     */
    public List<ICategoryTree> findTreeOfSource(Long pid, Integer source, Boolean status);

    /**
     * 根据类型取ROOT列表
     * @param source 类型
     * @param status 状态；NULL代表全部状态
     * @return 列表
     */
    public List<ICategory> findListOfSource(Integer source,Boolean status);

    /**
     * 根据父ID取列表
     * @param pid 父ID
     * @param source 类型，可选
     * @param status 状态；NULL代表全部状态
     * @return 列表
     */
    public List<ICategory> findListOfPid(Long pid,Integer source,Boolean status);
}
