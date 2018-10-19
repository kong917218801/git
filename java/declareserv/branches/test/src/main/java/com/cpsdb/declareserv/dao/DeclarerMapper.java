package com.cpsdb.declareserv.dao;

import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.params.ApiQueryParams;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.params.declarer.JDeclarerPublicParams;
import com.cpsdb.declareserv.params.declarer.JDeclarerQueryParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李银
 * @ClassName DeclarerMapper
 * @Description
 * @date 2018-09-20 10:42:40
 */
public interface DeclarerMapper {

    Long insert(Declarer declarer);

    Integer update(Declarer declarer);

    Declarer getById(Long id);

    void updateState(@Param("id") Long id, @Param("stateTo") String stateTo);

    Declarer getByIdNumber(String idNumber);

    Declarer getByPhone(String cellphone);

    List<Declarer> getByOrganizId(@Param("params") JDeclarerQueryParams params, @Param("objectId") Long objectId, @Param("pager") DatagridPager pager);

    List<Declarer> getByServiceId(@Param("params") JDeclarerQueryParams params, @Param("objectId") Long objectId, @Param("pager") DatagridPager pager);

    List<Declarer> getPublicDeclarerList(@Param("params") JDeclarerPublicParams params, @Param("pager") DatagridPager pager);

    Long getPublicDeclarerCount(@Param("params") JDeclarerPublicParams params);

    Integer getDeclarer(@Param("params") ApiQueryParams params);

    List<Declarer> query(@Param("params") JDeclarerQueryParams params, @Param("pager") DatagridPager pager);

    Long count(@Param("params") JDeclarerQueryParams params);

    Declarer getByExaminee(@Param("name") String name,
                           @Param("idnumber") String idnumber,
                           @Param("cellphone") String cellphone);

    void validatyPhone(@Param("cellphone") String cellphone);

    void validatyIdNumber(@Param("idNumber") String idNumber);

    Long getByOrganizCount(@Param("params") JDeclarerQueryParams params, @Param("objectId") Long objectId);

    Long getByServiceCount(@Param("params") JDeclarerQueryParams params, @Param("objectId") Long objectId);

    void updateProvence(@Param("areaCode") String areaCode ,
                        @Param("provenceId") Long provenceId);

    void updateOrganiz(@Param("id") Long id,
                       @Param("organizId") Long organizId);

    Declarer getByRegist(@Param("organizId") Long organizId,@Param("serviceId") Long serviceId, @Param("registMethod") String registMethod);

}