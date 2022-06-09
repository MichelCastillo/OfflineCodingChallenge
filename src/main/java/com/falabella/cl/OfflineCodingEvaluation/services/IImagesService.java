package com.falabella.cl.OfflineCodingEvaluation.services;

import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface IImagesService {


    Set<Images> findImagesByProductId(long id);

}
