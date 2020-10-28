package com.uniba.corpusmanager.repository;

import com.uniba.corpusmanager.domain.ActionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ActionLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {

    Page<ActionLog> findAllByCreatedByOrderByCreatedDateDesc(Pageable pageable, String createdBy);
}
