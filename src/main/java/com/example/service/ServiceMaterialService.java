package com.example.service;

import com.example.entity.ServiceMaterial;
import com.example.mapper.ServiceMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMaterialService {

    @Autowired
    private ServiceMaterialMapper serviceMaterialMapper;

    public List<ServiceMaterial> queryAllServiceMaterial() {
        List<ServiceMaterial> serviceMaterials = serviceMaterialMapper.queryAllMaterial();
        return serviceMaterials;
    }

    public void updateServiceMaterial(ServiceMaterial serviceMaterial) {
        serviceMaterialMapper.updateServiceMaterial(serviceMaterial);
    }
}
