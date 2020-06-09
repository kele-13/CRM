package com.kele.service;

import com.kele.pojo.CrmDict;

import java.util.List;

/**
 * @author 12402
 */
public interface DictService {

    public List<CrmDict> getDictByTypeCode(String code);
}
