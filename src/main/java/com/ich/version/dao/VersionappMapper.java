package com.ich.version.dao;

import com.ich.version.pojo.Versionapp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VersionappMapper {

    public void insert(Versionapp versionapp);

    public void update(Versionapp versionapp);

    public void updateAllToHistory(@Param("equipment") String equipment, @Param("appname") String appname);

    public void updateToNewest(Long id);

    public void delete(Long id);

    public Versionapp selectById(Long id);

    public Versionapp selectByVersion(@Param("equipment") String equipment, @Param("appname") String appname, @Param("version") String version);

    public Versionapp selectByNewest(@Param("equipment") String equipment, @Param("appname") String appname);

    public List<Versionapp> selectByHistory(@Param("equipment") String equipment, @Param("appname") String appname);

    List<Versionapp> selectByExample(@Param("equipment") String equipment, @Param("appname") String appname);
}
