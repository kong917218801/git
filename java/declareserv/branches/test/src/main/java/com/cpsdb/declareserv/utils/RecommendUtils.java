package com.cpsdb.declareserv.utils;

import com.cpsdb.declareapi.utils.UserUtils;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RecommendUtils {
    /**
     * 推荐关系图
     */
    public static Map<UserUtils.UserType, Set<UserUtils.UserType>> RECOMMEND_RELATIONSHIP_GRAPH;

    static {
        RECOMMEND_RELATIONSHIP_GRAPH = new HashMap<>();
        // 省级可以推荐省级、服务处、申报官
        RECOMMEND_RELATIONSHIP_GRAPH.put(UserUtils.UserType.service,
                Sets.newHashSet(UserUtils.UserType.service, UserUtils.UserType.organiz, UserUtils.UserType.declarer));

        // 服务处可以推荐服务处、申报官
        RECOMMEND_RELATIONSHIP_GRAPH.put(UserUtils.UserType.organiz,
                Sets.newHashSet(UserUtils.UserType.organiz, UserUtils.UserType.declarer));

        // 申报官只能推荐申报官
        RECOMMEND_RELATIONSHIP_GRAPH.put(UserUtils.UserType.declarer,
                Sets.newHashSet(UserUtils.UserType.declarer));
    }

    public enum TargetState {
        pending,
        passed,
        unpass;
    }
}