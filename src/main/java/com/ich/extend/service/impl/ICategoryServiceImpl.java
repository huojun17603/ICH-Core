package com.ich.extend.service.impl;

import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.extend.dao.ICategoryMapper;
import com.ich.extend.dto.ICategoryTree;
import com.ich.extend.pojo.ICategory;
import com.ich.extend.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ICategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryMapper categoryMapper;


    @Override
    public HttpResponse addOrEditCategory(ICategory category) {
        if(ObjectHelper.isEmpty(category.getSource())){
            return new HttpResponse(HttpResponse.HTTP_ERROR,"请选择类目的类型");
        }
        if(ObjectHelper.isEmpty(category.getName())){
            return new HttpResponse(HttpResponse.HTTP_ERROR,"请输入类目名称");
        }
        int result = 0;
        if(ObjectHelper.isEmpty(category.getId())){
            if(ObjectHelper.isEmpty(category.getOnum())) category.setOnum(0);//默认排序
            category.setStatus(false);//初始化为无效
            result = categoryMapper.insert(category);
        }else{
            result = categoryMapper.updateByPrimaryKeySelective(category);
        }
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }

    @Override
    public HttpResponse editCategoryStatus(Long id, Boolean status) {
        int result = categoryMapper.updateStatus(id,status);
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }

    @Override
    public HttpResponse deleteCategory(Long id) {
        ICategory category = categoryMapper.selectByPrimaryKey(id);
        if(ObjectHelper.isEmpty(category)) return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
        List<ICategory> list = findListOfPid(category.getId(),category.getSource(),null);
        if(ObjectHelper.isNotEmpty(list)) return new HttpResponse(HttpResponse.HTTP_ERROR,"请先删除其子级类目");
        int result = categoryMapper.deleteByPrimaryKey(id);
        return result==1?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }

    @Override
    public List<ICategoryTree> findTreeOfSource(Long pid, Integer source, Boolean status) {
        if(ObjectHelper.isEmpty(source)) return null;//必须传入source
        List<ICategory> list = findListOfPid(pid,source,status);
        List<ICategoryTree> trees = new ArrayList<>();
        for(ICategory category : list){
            ICategoryTree tree = new ICategoryTree(category);
            tree.setId(String.valueOf(category.getId()));
            tree.setText(category.getName());
            if(ObjectHelper.isNotEmpty(category.getPid()))
                tree.setParent_id(String.valueOf(category.getPid()));
            tree.setChildren(findTreeOfSource(category.getId(),source,status));
            if(ObjectHelper.isEmpty(tree.getChildren())) tree.setState("closed");
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<ICategory> findListOfSource(Integer source, Boolean status) {
        List<ICategory> list = categoryMapper.selectListOfSource(source,status);
        return list;
    }

    @Override
    public List<ICategory> findListOfPid(Long pid,Integer source, Boolean status) {
        List<ICategory> list = categoryMapper.selectListOfPid(pid,source,status);
        return list;
    }
}
