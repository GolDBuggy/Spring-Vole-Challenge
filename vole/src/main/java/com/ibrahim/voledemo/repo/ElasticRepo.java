package com.ibrahim.voledemo.repo;

import com.ibrahim.voledemo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticRepo extends ElasticsearchRepository<User,String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"x-username\": \"?0\"}}]}}")
    List<User> findByQuery(String s, Pageable pageable);
}
