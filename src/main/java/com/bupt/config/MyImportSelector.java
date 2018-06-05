package com.bupt.config;


import com.bupt.bean.Yellow;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {


        return new String[]{"com.bupt.bean.Yellow"};
    }
}
