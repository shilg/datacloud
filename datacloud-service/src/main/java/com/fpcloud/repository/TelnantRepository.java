package com.fpcloud.repository;

import com.fpcloud.entity.TelnantInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TelnantRepository extends JpaRepository<TelnantInfo,Long> {
    TelnantInfo findTelnantInfoByUserNameAndPassword(String userName,String password);
    TelnantInfo findTelnantInfoByUserName(String userName);
    TelnantInfo findTelnantInfoByUserNameAndTelnantId(String userName,String tenantId);
    Page<TelnantInfo> findTelnantInfoByTelnantIdAndType(String tenantId, String type ,Pageable pageable);
    Long countTelnantInfoByTelnantIdAndType(String tenantId, String type );
    @Transactional
    @Modifying
    @Query(value = "update telnant_info u set u.password = ?1 where u.id = ?2", nativeQuery = true)
    int updateTelnantInfo(String password , long id);
}
