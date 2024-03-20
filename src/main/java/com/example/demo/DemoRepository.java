package com.example.demo;

import com.example.demo.models.DemoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends MongoRepository<DemoEntity, String> {

}
