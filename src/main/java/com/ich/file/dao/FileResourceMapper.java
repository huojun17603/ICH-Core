package com.ich.file.dao;

import com.ich.file.pojo.FileResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileResourceMapper {

    void insert(FileResource file);

    void update(FileResource file);

    void deleteById(String id);

    void deleteBySource(@Param("source") Integer source, @Param("sourceid")String sourceid);

    FileResource selectById(String id);

    List<FileResource> selectBySource(@Param("source") Integer source, @Param("sourceid")String sourceid);

}
