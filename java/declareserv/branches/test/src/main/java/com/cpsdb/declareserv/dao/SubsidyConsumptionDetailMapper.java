package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.SubsidyConsumptionDetail;
import java.lang.Long;

/**
 *
 * @ClassName SubsidyConsumptionDetailMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 15:43:51
 */
public interface SubsidyConsumptionDetailMapper {

Long insert(SubsidyConsumptionDetail subsidyConsumptionDetail);

    Integer update(SubsidyConsumptionDetail subsidyConsumptionDetail);

SubsidyConsumptionDetail getById(Long id);
}