package com.green.nowon.repository;

import com.green.nowon.domain.entity.ElectronicDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ElectronicDocumentEntityRepository extends JpaRepository<ElectronicDocumentEntity,Long> {
    List<ElectronicDocumentEntity> findAllByEmployee_no(long mno);

	List<ElectronicDocumentEntity> findByDocTitleContaining(String keyword);

	List<ElectronicDocumentEntity> findByLetterContaining(String keyword);
}
