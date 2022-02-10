package edu.escuelaing.lab2.repository;

import edu.escuelaing.lab2.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface UserRepository  extends MongoRepository<User, String> {

    @Query("{'$or':[{'name': ?0}, {'lastName':?0}]}")
    List<User> findUsersWithNameOrLastNameLike(String queryTest);

    List<User> findUsersByCreateAtAfter(Date createAt);

}
