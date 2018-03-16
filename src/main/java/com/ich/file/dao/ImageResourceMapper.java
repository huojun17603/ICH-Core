package com.ich.file.dao;

import com.ich.file.pojo.ImageResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageResourceMapper {

    void insert(ImageResource image);

    void update(ImageResource image);

    void updateOnum(ImageResource image);

    void deleteBySource(@Param("source") Integer source, @Param("sourceid")String sourceid);

    void deleteById(String id);

    ImageResource selectById(String id);

    Integer selectMaxNumOfSource(@Param("source") Integer source, @Param("sourceid")String sourceid);

    List<ImageResource> selectByName(String randomname);

    List<ImageResource> selectBySource(@Param("source") Integer source, @Param("sourceid")String sourceid);

}
