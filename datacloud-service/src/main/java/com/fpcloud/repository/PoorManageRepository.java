package com.fpcloud.repository;

import com.fpcloud.entity.PoorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PoorManageRepository extends JpaRepository<PoorInfo, Long> {
    Page<PoorInfo> findAllByTenantIdAndHContainsAndIContains(String tenantId,String h, String i, Pageable pageable);
    Page<PoorInfo> findAllByTenantIdAndHContains(String tenantId,String h, Pageable pageable);
    Page<PoorInfo> findAllByTenantIdAndIContains(String tenantId,String i, Pageable pageable);
    Page<PoorInfo> findAllByTenantId(String tenantId, Pageable pageable);

    List<PoorInfo> findPoorInfoByIdAndTenantId(Long id,String tenantId);

    @Transactional
    @Modifying
    @Query(value = "insert into poor_info(tenant_id,file_id,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,ab) " +
            "values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24,?25,?26,?27,?28,?29)",nativeQuery = true)
    int insertPoorInfo(String tenatId,String fileId,String b,String c,String d,String e,String f,String g,String h,String i,String j,
                       String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,
                       String w,String x,String y,String z,String aa,String ab);
    @Query(value ="select id,ii,t from poor_info where tenant_id = ?1 limit ?2 , ?3",nativeQuery=true)
    List<PoorInfo> findcardNoAndpoorReson(String tenantId, int start,int size);

    int deletePoorInfoByFileId(String fileId);

    List<PoorInfo> findPoorInfoByTenantIdAndIi(String tenantId,String ii);

    Long countPoorInfoByTenantId(String tenantId);
}
