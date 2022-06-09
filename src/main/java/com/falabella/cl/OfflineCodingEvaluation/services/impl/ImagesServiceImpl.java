package com.falabella.cl.OfflineCodingEvaluation.services.impl;

import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import com.falabella.cl.OfflineCodingEvaluation.repositories.IImagesRepository;
import com.falabella.cl.OfflineCodingEvaluation.services.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ImagesServiceImpl implements IImagesService {

    @Autowired
    private IImagesRepository imagesRepository;

    @Override
    public Set<Images> findImagesByProductId(long id) {
        return imagesRepository.findImagesByProductId(id);
    }
}
