package com.ruoyaya.xpress.modules.home.service;

import cn.hutool.json.JSONUtil;
import com.ruoyaya.xpress.modules.home.dao.TestRepository;
import com.ruoyaya.xpress.modules.home.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    TestRepository testRepository;


    public String testDB() {
        List<TestEntity> list = testRepository.findAll();
        return JSONUtil.toJsonPrettyStr(list);
    }
}
