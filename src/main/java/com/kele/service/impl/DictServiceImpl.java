package com.kele.service.impl;

import com.kele.dao.CrmDictMapper;
import com.kele.pojo.CrmDict;
import com.kele.pojo.CrmDictExample;
import com.kele.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 12402
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    CrmDictMapper dictMapper;
    public List<CrmDict> getDictByTypeCode(String code) {

        CrmDictExample dictExample = new CrmDictExample();
        CrmDictExample.Criteria criteria = dictExample.createCriteria();
        criteria.andDictTypeCodeEqualTo(code);
        return dictMapper.selectByExample(dictExample);
    }
}
