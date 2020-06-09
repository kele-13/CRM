package com.kele.service;

import com.github.pagehelper.PageInfo;
import com.kele.pojo.CrmCustomer;
import com.kele.pojo.SearchInfo;

/**
 * @author 12402
 */
public interface CustomerService {


    public PageInfo<CrmCustomer> getCustomerList(SearchInfo searchInfo);

    int updateCustomer(CrmCustomer crmCustomer);

    CrmCustomer getCustomerById(String id);

    int deleteCustomer(Integer id);
}
