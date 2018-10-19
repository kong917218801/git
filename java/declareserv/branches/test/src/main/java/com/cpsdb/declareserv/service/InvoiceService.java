package com.cpsdb.declareserv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpsdb.declareserv.dao.InvoiceMapper;
import com.cpsdb.declareserv.entity.Invoice;

/**
 * 
 * @ClassName InvoiceService
 * @Description
 * @author 李银
 * @date 2018-09-20 10:55:08
 */
@Service
public class InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;
    
    public void insert(Invoice invoice) {
invoiceMapper.insert(invoice);
    }
    
    public void update(Invoice invoice) {
invoiceMapper.update(invoice);
    }

    public Invoice getById(Long id) {
        return invoiceMapper.getById(id);
    }

    public Invoice getByOrderId(Long orderId) {
        return invoiceMapper.getByOrderId(orderId);
    }
}