package com.falabella.cl.OfflineCodingEvaluation.repositories;

import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface IImagesRepository extends JpaRepository<Images, Long> {

    Set<Images> findImagesByProductId(long product_id);
}
