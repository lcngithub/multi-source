
package com.bsoft.mysql.service;

import com.bsoft.entity.MappingRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsoft.mysql.dao.MappingRelationMapper;

@Service
public class MappingRelationService {
    @Autowired
    private MappingRelationMapper mappingRelationMapper;

    public MappingRelation selectByUrl(String url){
        return mappingRelationMapper.selectByUrl(url);
    }

}
