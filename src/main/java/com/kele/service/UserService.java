package com.kele.service;

import com.kele.pojo.CrmUser;

/**
 * @author 12402
 */
public interface UserService {

    public CrmUser userLogin(String usercode,String password) throws Exception;

    CrmUser userRegist(String usercode, String password) throws Exception;
}
