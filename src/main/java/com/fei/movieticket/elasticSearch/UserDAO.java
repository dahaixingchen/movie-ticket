package com.fei.movieticket.elasticSearch;

import com.fei.movieticket.elasticSearch.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Create by wangxb
 * 2019-09-08 18:53
 */
public interface UserDAO extends CrudRepository<UserEntity, String> {

}