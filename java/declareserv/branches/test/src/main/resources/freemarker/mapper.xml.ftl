<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${entity.daoPackageName}.${entity.className}Mapper">
    <resultMap type="${entity.entityPackageName}.${entity.className}" id="${entity.classInstanceName}Map">
        <id column="${entity.idColumn}" property="${entity.idName}"/>
        <#list entity.propList as prop>
        <result column="${prop.column}" property="${prop.propName}"/>
        </#list>
        <#if entity.hasHibernateVersion>
        <result column="version" property="version"/>
        </#if>
    </resultMap>

    <insert id="insert" parameterType="${entity.entityPackageName}.${entity.className}" useGeneratedKeys="true"
            keyProperty="${entity.idColumn}">
        INSERT INTO
    ${entity.tableName}
        (<#list entity.propList as prop>${prop.column}<#if prop_has_next>,</#if></#list>)
        VALUES
        (<#list entity.propList as prop>${entity.poundSign}{${prop.propName}}<#if prop_has_next>,</#if></#list>)
    </insert>

    <update id="update" parameterType="${entity.entityPackageName}.${entity.className}">
        UPDATE
    ${entity.tableName}
        SET 
        <#list entity.propList as prop>
            ${prop.column}=${entity.poundSign}{${prop.propName}}<#if prop_has_next>,</#if>
        </#list>
        <#if entity.hasHibernateVersion>
        	,version = version + 1
        </#if>
        WHERE
    ${entity.idColumn}=${entity.poundSign}{${entity.idName}}
        	<#if entity.hasHibernateVersion>
        	And version = ${entity.versionLock}
            </#if>
    </update>

    <select id="getById" parameterType="${entity.idSimpleType}" resultMap="${entity.classInstanceName}Map">
        SELECT
        *
        FROM
    ${entity.tableName}
        WHERE
    ${entity.idColumn}=${entity.poundSign}{${entity.idName}}
    </select>



    <select id="query" resultMap="${entity.classInstanceName}Map">
        SELECT
        *
        <include refid="querySql"/>
        order by ${pager.orderColumn} ${pager.orderType}
        LIMIT #{pager.startNum},#{pager.pageCount}
    </select>

    <select id="count" parameterType="java.util.Map" resultType="java.lang.Long">
        SELECT COUNT(id)
        <include refid="querySql"/>
    </select>

    <sql id="querySql">
        FROM
        ${entity.tableName}
        <where>
             <#list entity.propList as prop>
                 <if test="params.id != null">
                     and a.id = #{params.Id}
                 </if>
                 <result column="${prop.column}" property="${prop.propName}"/>
             </#list>
        </where>
    </sql>


</mapper>