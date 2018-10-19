package com.cpsdb.declareserv.dao;

import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.entity.SubsidyDetail;
import com.cpsdb.declareserv.params.subsidy.JSubsidyDetailQueryParams;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author 李银
 * @ClassName SubsidyDetailMapper
 * @Description
 * @date 2018-09-20 17:26:02
 */
public interface SubsidyDetailMapper {

    Long insert(SubsidyDetail subsidyDetail);

    Integer update(SubsidyDetail subsidyDetail);

    SubsidyDetail getById(Long id);

    BigDecimal getUnwithdrawedAmount(Long userId);

    List<SubsidyDetail> query(@Param("params") JSubsidyDetailQueryParams params,
                              @Param("pager") Pageable pager);

    Integer count(JSubsidyDetailQueryParams params);

    void updateState(@Param("detailIdSet") Set<Long> detailIdSet, @Param("state") String state);
}