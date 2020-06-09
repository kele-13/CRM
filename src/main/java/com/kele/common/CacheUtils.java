package com.kele.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kele.dao.CrmDictMapper;
import com.kele.pojo.CrmDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 12402
 */
@Component
public class CacheUtils {

    @Autowired
    CrmDictMapper dictMapper;

    private LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.DAYS)
            .maximumSize(100)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    System.out.println("访问数据库了。。。。。。。。。。。。");
                    // 查询数据库，把数据放入缓存
                    if (key.equals("dictData")) {

                        List<CrmDict> dicts = dictMapper.selectByExample(null);

                        // 将数据转换成以ID为key，name为值
                        HashMap<String, String> map = new HashMap<String, String>();
                        for (CrmDict dict : dicts) {
                            map.put(dict.getDictId(), dict.getDictItemName());
                        }
                        return map;
                    }
                    return null;
                }
            });


    public String getDictNameById(String id) {

        try {
            HashMap<String, String> map = (HashMap<String, String>) cache.get("dictData");
            return map.get(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加自定义缓存
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    // 清楚某个缓存
    public void invalidate(String key) {
        cache.invalidate(key);
    }

    // 获取某个缓存数据
    public Object get(String key) {
        try {
            cache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
