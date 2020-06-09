package com.kele.controller;

import com.github.pagehelper.PageInfo;
import com.kele.pojo.CrmCustomer;
import com.kele.pojo.CrmDict;
import com.kele.pojo.SearchInfo;
import com.kele.service.CustomerService;
import com.kele.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 12402
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    // 注入配置信息
    @Value("${sourceType}")
    private String sourceType;
    @Value("${industry}")
    private String industry;
    @Value("${level}")
    private String level;

    @Autowired
    CustomerService customerService;

    @Autowired
    DictService dictService;


    @RequestMapping("/list")
    public String getCustomerList(Model model, SearchInfo searchInfo) {

        System.out.println(searchInfo);
        PageInfo<CrmCustomer> info = customerService.getCustomerList(searchInfo);
        // 分页查询
        model.addAttribute("customerList", info.getList());
        model.addAttribute("totalPage", info.getPages());

        // 客户来源
        List<CrmDict> sourceDict = dictService.getDictByTypeCode(sourceType);
        model.addAttribute("fromType", sourceDict);

        //所属行业
        List<CrmDict> industryDict = dictService.getDictByTypeCode(industry);
        model.addAttribute("industryType", industryDict);

        //客户级别
        List<CrmDict> levelDict = dictService.getDictByTypeCode(level);
        model.addAttribute("levelType", levelDict);

        // 数据回显
//        model.addAttribute("custName",searchInfo.getCustName());
//        model.addAttribute("custSource",searchInfo.getCustSource());
//        model.addAttribute("custIndustry",searchInfo.getCustIndustry());
//        model.addAttribute("custLevel",searchInfo.getCustLevel());


        return "customer";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public CrmCustomer getCustomerById(String id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateCustomer(CrmCustomer crmCustomer) {
        int c = customerService.updateCustomer(crmCustomer);
        if (c > 0) {
            return "edit success";
        }
        return "edit error";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String deleteCustomerById(Integer id) {
        int count = customerService.deleteCustomer(id);
        if (count > 0) {
            return "delete success";
        }
        return "delete error";
    }
}
