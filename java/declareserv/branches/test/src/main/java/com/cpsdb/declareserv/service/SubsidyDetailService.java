package com.cpsdb.declareserv.service;

import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.dao.SubsidyDetailMapper;
import com.cpsdb.declareserv.entity.SubsidyDetail;
import com.cpsdb.declareserv.entity.SubsidyWithdrawRequestItem;
import com.cpsdb.declareserv.params.subsidy.JSubsidyDetailQueryParams;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 李银
 * @ClassName SubsidyDetailService
 * @Description
 * @date 2018-09-20 17:26:02
 */
@Service
public class SubsidyDetailService {

    @Autowired
    private SubsidyDetailMapper subsidyDetailMapper;

    @Autowired
    private SubsidyWithdrawRequestItemService subsidyWithdrawRequestItemService;

    public void insert(SubsidyDetail subsidyDetail) {
        subsidyDetailMapper.insert(subsidyDetail);
    }

    public void update(SubsidyDetail subsidyDetail) {
        subsidyDetailMapper.update(subsidyDetail);
    }

    public SubsidyDetail getById(Long id) {
        return subsidyDetailMapper.getById(id);
    }

    /**
     * @param userId
     * @param type
     * @param name
     * @param targetId
     * @param quantity
     * @param amount
     * @param percent
     */
    public void insert(SubsidyWithdrawUtils.DetailType type, Long userId, Long targetId, String name,
                       Integer quantity, BigDecimal amount, BigDecimal percent) {
        this.insert(new SubsidyDetail()
                .setUserId(userId)
                .setAmount(amount)
                .setName(name)
                .setPercent(percent)
                .setQuantity(quantity)
                .setType(type.name())
                .setTargetId(targetId)
                .setState(SubsidyWithdrawUtils.DetailState.unwithdrawed.name())
        );
    }

    public BigDecimal getUnwithdrawedAmount(Long userId) {
        return ObjectUtils.defaultIfNull(this.subsidyDetailMapper.getUnwithdrawedAmount(userId), BigDecimal.ZERO)
                .setScale(3, RoundingMode.CEILING);
    }

    public List<SubsidyDetail> query(JSubsidyDetailQueryParams params, Pageable pager) {
        return this.subsidyDetailMapper.query(params, pager);
    }

    public void updateState(Long requestId, SubsidyWithdrawUtils.DetailState withdrawed) {
        Set<Long> detailIdSet = this.subsidyWithdrawRequestItemService.getByRequestId(requestId)
                .stream()
                .map(SubsidyWithdrawRequestItem::getFkSubsidyDetailId)
                .collect(Collectors.toSet());

        this.subsidyDetailMapper.updateState(detailIdSet, withdrawed.name());
    }
}