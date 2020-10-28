package com.uniba.corpusmanager.repository;

import com.uniba.corpusmanager.domain.Corpus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Corpus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CorpusRepository extends JpaRepository<Corpus, Long> {

    Page<Corpus> findAllByIsPublicIsTrueOrCreatedByEquals(Pageable pageable, String createdBy);

    @Query(value = "SELECT DISTINCT corpus FROM Corpus corpus WHERE corpus.name LIKE CONCAT(:name,'%') " +
        "AND (corpus.isPublic = true OR corpus.createdBy = :createdBy)",
        countQuery = "SELECT count(corpus.id) FROM Corpus corpus WHERE corpus.name LIKE CONCAT(:name,'%') " +
            "AND (corpus.isPublic = true OR corpus.createdBy = :createdBy)")
    Page<Corpus> search(Pageable pageable, String name, String createdBy);
}
