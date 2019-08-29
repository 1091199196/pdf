package com.example.mapper;

import com.example.entity.ServiceMaterial;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceMaterialMapper {
    /**
     * 获取docPath不是空的数据
     * @return
     */
    List<ServiceMaterial> queryAllMaterial();

    /**
     * 保存材料
     * @param serviceMaterial
     */
    void  updateServiceMaterial(ServiceMaterial serviceMaterial);
}
