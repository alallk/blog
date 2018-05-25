package com.github.alallk.repository;

import com.github.alallk.domain.Body;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Body entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BodyRepository extends JpaRepository<Body, Long> {

}
