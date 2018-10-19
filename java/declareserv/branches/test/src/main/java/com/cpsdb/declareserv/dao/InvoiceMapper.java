package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.Invoice;
import java.lang.Long;

/**
 *
 * @ClassName InvoiceMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:55:08
 */
public interface InvoiceMapper {

    Long insert(Invoice invoice);

    Integer update(Invoice invoice);

    Invoice getById(Long id);

    Invoice getByOrderId(Long orderId);
}