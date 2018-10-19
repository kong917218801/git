package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.DeclarerCodeDetail;
import java.lang.Long;
import java.util.List;

/**
 *
 * @ClassName DeclarerCodeDetailMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
public interface DeclarerCodeDetailMapper {

    Long insert(DeclarerCodeDetail declarerCodeDetail);

    Integer update(DeclarerCodeDetail declarerCodeDetail);

    DeclarerCodeDetail getById(Long id);

    List<DeclarerCodeDetail> getDetailsByDeclarerId(Long organizId);
}