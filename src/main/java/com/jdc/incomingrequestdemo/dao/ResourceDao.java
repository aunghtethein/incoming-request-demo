package com.jdc.incomingrequestdemo.dao;

import com.jdc.incomingrequestdemo.ds.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends CrudRepository<Resource,Integer> {
}
