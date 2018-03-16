package com.ich.file.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.file.pojo.ImageResource;

import java.util.List;

public interface ImageResourceService {
    /**
     * 添加图片
     * @param image 图片信息
     * @return 是否完成
     */
    public HttpResponse addImage(ImageResource image);
    /**
     * 修改图片
     * @param image 图片信息
     * @return 是否完成
     */
    public HttpResponse editImage(ImageResource image);
    /**
     * 调换图片顺序，即源和目标替换顺序，不影响其他
     * 要求：源和目标 属于同一来源
     * @param id 源ID
     * @param tagertid 目标ID
     * @return 是否完成
     */
    public HttpResponse editImageOnumOfReplace(String id,String tagertid);
    /**
     * 顺延图片顺序，源图片移至目标图片前，中间的图片顺序顺延
     * 注意：前移是移至目标之前，后移是移至目标之后
     * 要求：源和目标 属于同一来源
     * @param id 源ID
     * @param tagertid 目标ID
     * @return 是否完成
     */
    public HttpResponse editImageOnumOfExtended(String id,String tagertid);
    /**
     * 批量删除
     * @param ids ID集
     * @return 是否完成
     */
    public HttpResponse deleteImageByIds(String ids);

    /**
     * 按来源删除
     * @param source 来源
     * @param sourceid 来源ID
     * @return 是否完成
     */
    public HttpResponse deleteImageBySource(Integer source, String sourceid);

    /**
     * 按ID查询
     * @param id
     * @return 数据
     */
    public ImageResource findById(String id);

    /**
     * 按来源查询
     * @param source 来源
     * @param sourceid 来源ID
     * @return 列表
     */
    public List<ImageResource> findBySource(Integer source, String sourceid);
    /**
     * 垃圾清理，
     * 每天固定时间清理昨天的垃圾，
     * 建议每天凌晨4点清理，
     * 只会清理属于配置目录下的垃圾
     * 需要系统配置
     * IM_CLEAR_ROOT：绝对路径根目录，
     * IM_MIME_TYPE：检测的图片类型
     * 谨慎配置此选项不然会导致其他文件被删除
     * */
    public void clearGar();
}
