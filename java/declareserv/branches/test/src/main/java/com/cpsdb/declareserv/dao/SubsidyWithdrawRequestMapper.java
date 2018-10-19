package com.cpsdb.declareserv.dao;

import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.dto.JPlatformWithdrewRcordAmounts;
import com.cpsdb.declareserv.entity.SubsidyWithdrawRequest;
import com.cpsdb.declareserv.params.subsidy.JSubsidyWithdrawQueryParams;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;
import java.util.Map;

/**
 * @author 李银
 * @ClassName SubsidyWithdrawRequestMapper
 * @Description
 * @date 2018-09-20 15:44:04
 */
public interface SubsidyWithdrawRequestMapper {

    Long insert(SubsidyWithdrawRequest subsidyWithdrawRequest);

    Integer update(SubsidyWithdrawRequest subsidyWithdrawRequest);

    SubsidyWithdrawRequest getById(Long id);

    void updateState(@Param(value = "id") Long id,
                     @Param(value = "state") String state);

    List<SubsidyWithdrawRequest> query(@Param(value = "params") JSubsidyWithdrawQueryParams params,
                                       @Param(value = "pager") DatagridPager pager);

    Integer count(@Param(value = "params") JSubsidyWithdrawQueryParams params);

    List<JPlatformWithdrewRcordAmounts> amount();
}