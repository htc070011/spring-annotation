package com.bupt.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //MetadataReader 描述当前扫描类信息
        //MdtadataReaderFacotry 获取其他任何类信息


        //获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        //当前类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        //获取当前类资源信息（路径）
        Resource resource = metadataReader.getResource();

        String name = classMetadata.getClassName();

        System.out.println("----->" + name);

        if(name.contains("er")) {
            return true;
        }

        return false;
    }
}
