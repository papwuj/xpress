package com.ruoyaya.xpress.modules.home.dao;

import com.ruoyaya.xpress.modules.home.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, String> {
}
