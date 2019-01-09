package com.bsoft.mysql.dao;


import com.bsoft.entity.MappingRelation;
import com.bsoft.entity.MappingRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MappingRelationMapper {
    int countByExample(MappingRelationExample example);

    int deleteByExample(MappingRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MappingRelation record);

    int insertSelective(MappingRelation record);

    List<MappingRelation> selectByExample(MappingRelationExample example);

    MappingRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MappingRelation record, @Param("example") MappingRelationExample example);

    int updateByExample(@Param("record") MappingRelation record, @Param("example") MappingRelationExample example);

    int updateByPrimaryKeySelective(MappingRelation record);

    int updateByPrimaryKey(MappingRelation record);
    MappingRelation selectByUrl(String url);
}