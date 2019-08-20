package com.fpcloud.repository;

import com.fpcloud.entity.AccessInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessInfoRepository extends JpaRepository<AccessInfo,Long> {
    AccessInfo findAccessInfoByTelnantId(String telnantId);
}
