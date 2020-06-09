package com.kele.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kele.common.CacheUtils;
import com.kele.dao.CrmCustomerMapper;
import com.kele.dao.CrmDictMapper;
import com.kele.pojo.CrmCustomer;
import com.kele.pojo.CrmCustomerExample;
import com.kele.pojo.CrmDict;
import com.kele.pojo.SearchInfo;
import com.kele.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 12402
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CrmCustomerMapper customerMapper;
    @Autowired
    CrmDictMapper dictMapper;
    @Autowired
    CacheUtils cacheUtils;

    public PageInfo<CrmCustomer> getCustomerList(SearchInfo searchInfo) {
        // 条件查询
        CrmCustomerExample customerExample = new CrmCustomerExample();
        CrmCustomerExample.Criteria criteria = customerExample.createCriteria();
        // 名字模糊查询
        if (StringUtils.isNotEmpty(searchInfo.getCustName())) {
            criteria.andCustNameLike("%" + searchInfo.getCustName() + "%");
        }
        //客户来源
        if (StringUtils.isNotEmpty(searchInfo.getCustSource())) {
            criteria.andCustSourceEqualTo( searchInfo.getCustSource() );
        }
        // 客户行业
        if (StringUtils.isNotEmpty(searchInfo.getCustIndustry())) {
            criteria.andCustIndustryEqualTo(  searchInfo.getCustIndustry() );
        }
        // 客户级别
        if (StringUtils.isNotEmpty(searchInfo.getCustLevel())) {
            criteria.andCustLevelEqualTo(searchInfo.getCustLevel());
        }

        // 执行分页，只对接下来执行的第一条语句生效
        PageHelper.startPage(searchInfo.getPage(),searchInfo.getSize());
        List<CrmCustomer> crmCustomers = customerMapper.selectByExample(customerExample);

        //在这里查询数据库,将id替换为名称
        convertIDToNameWithCache(crmCustomers);
        // 获取本次分页的信息
        PageInfo<CrmCustomer> pageInfo = new PageInfo<CrmCustomer>(crmCustomers);
        return pageInfo;
    }

    public int updateCustomer(CrmCustomer crmCustomer) {

        return customerMapper.updateByPrimaryKey(crmCustomer);
    }

    public CrmCustomer getCustomerById(String id) {
        return customerMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    public int deleteCustomer(Integer id) {
        return customerMapper.deleteByPrimaryKey(Long.valueOf(id));

    }


    private void convertIDToName(List<CrmCustomer> customers ){
        /*
        1.先查询出所有的dict数据
        2.在遍历所有customer  通过id到dict中查找数据
         */

        List<CrmDict> crmDicts = dictMapper.selectByExample(null);
        for (CrmCustomer crmCustomer : customers){
            for (CrmDict dict : crmDicts){
                //当客户的来源id == dict的id  那就将name赋值给客户的cust_source
                if (crmCustomer.getCustSource().equals(dict.getDictId())){
                    crmCustomer.setCustSource(dict.getDictId());
                }

                if (crmCustomer.getCustIndustry().equals(dict.getDictId())){
                    crmCustomer.setCustIndustry(dict.getDictItemName());
                }

                if (crmCustomer.getCustLevel().equals(dict.getDictId())){
                    crmCustomer.setCustLevel(dict.getDictItemName());
                }
            }
        }
    }

    private void convertIDToNameWithCache(List<CrmCustomer> customers){
        //遍历所有客户对象  拿着id去缓存中找
        for (CrmCustomer customer:customers){
            //客户来源
            customer.setCustSource(cacheUtils.getDictNameById(customer.getCustSource()));
            //行业
            customer.setCustIndustry(cacheUtils.getDictNameById(customer.getCustIndustry()));
            //级别
            customer.setCustLevel(cacheUtils.getDictNameById(customer.getCustLevel()));
        }
    }
}
