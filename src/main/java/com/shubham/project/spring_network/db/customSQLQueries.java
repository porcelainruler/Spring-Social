package com.shubham.project.spring_network.db;

import com.shubham.project.spring_network.persistence.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface customSQLQueries {

    /*
     * * Dummy example for custom query {Type -> JPQL}
     */
    @Query("SELECT * FROM User WHERE enabled = 1")
    Collection<User> getAllActiveUsersJPQL ();

    /*
     * * Dummy example for custom query {Type -> Native}
     */
    @Query(value = "SELECT * FROM User WHERE enabled = 1", nativeQuery = true)
    Collection<User> getAllActiveUsersNative ();

}
