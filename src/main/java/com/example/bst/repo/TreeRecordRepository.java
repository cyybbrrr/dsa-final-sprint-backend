package com.example.bst.repo;

import com.example.bst.model.TreeRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreeRecordRepository extends MongoRepository<TreeRecord, String> { }
