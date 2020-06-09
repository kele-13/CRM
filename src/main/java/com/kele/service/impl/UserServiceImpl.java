package com.kele.service.impl;

import com.kele.dao.CrmUserMapper;
import com.kele.pojo.CrmUser;
import com.kele.pojo.CrmUserExample;
import com.kele.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author 12402
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CrmUserMapper userMapper;


    public CrmUser userLogin(String usercode, String password) throws Exception {
        CrmUserExample userExample = new CrmUserExample();
        CrmUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserCodeEqualTo(usercode);
        List<CrmUser> crmUsers = userMapper.selectByExample(userExample);

        if (crmUsers.size() > 0){
            CrmUser crmUser = crmUsers.get(0);
            //的需要先将用户传递的明文进行加密 在比对加密后的结果
            if (crmUser.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                //用户状态是否可用  1表示正常  0表示被锁定
                if (crmUser.getUserState().equals("1")) {
                    return crmUser;
                }
                throw new  Exception("账户被锁定");
            }
            throw new  Exception("密码不正确");
        }
        throw new  Exception("用户不存在");

    }

    public CrmUser userRegist(String usercode, String password) throws Exception {

        if (StringUtils.isNotEmpty(usercode) && StringUtils.isNotEmpty(password)) {
            CrmUserExample example = new CrmUserExample();
            CrmUserExample.Criteria criteria = example.createCriteria();
            criteria.andUserCodeEqualTo(usercode);
            List<CrmUser> crmUsers = userMapper.selectByExample(example);

            if (crmUsers.size() == 0) {
                CrmUser newUser = new CrmUser();
                newUser.setUserCode(usercode);
                newUser.setUserPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

                newUser.setUserState("1");
                newUser.setUserName(usercode);

                 userMapper.insert(newUser);
                return newUser;
            }
            throw  new Exception("用户已存在");
        }
        throw new Exception("用户名或密码不能为空");
    }
}
