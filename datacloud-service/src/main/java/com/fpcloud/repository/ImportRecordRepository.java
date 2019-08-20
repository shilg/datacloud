package com.fpcloud.repository;

import com.fpcloud.entity.ImportRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRecordRepository extends JpaRepository<ImportRecord, Long> {
    Page<ImportRecord> findAllByTenantId(String tenantId, Pageable pageable);
    int  deleteImportRecordByFileId(String fileId);
}
