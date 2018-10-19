package com.cpsdb.declareserv.dao;


import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.entity.Recommend;
import com.cpsdb.declareserv.params.JRecommendQueryParams;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;


/**
 * @author 李银
 * @ClassName RecommendMapper
 * @Description
 * @date 2018-09-20 10:27:53
 */
public interface RecommendMapper {

    Long insert(Recommend recommend);

    Integer update(Recommend recommend);

    Recommend getById(Long id);

    Recommend getByTargetId(@Param("targetUserId") Long targetUserId);

    List<Recommend> query(@Param("params") JRecommendQueryParams params,
                          @Param("pager") Pageable pager);

    Integer count(@Param("params") JRecommendQueryParams params);

    void updateTargetState(@Param("targetUserId") Long targetUserId,
                            @Param("state") String state);

    Boolean branchParent(@Param(value = "state") String state,
                         @Param(value = "userId") Long userId,
                         @Param(value = "declarerIds") List<Long> declarerIds);

    String getUserName(@Param("targetId")Long targetId);
}