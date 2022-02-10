package edu.escuelaing.lab2.service;

import edu.escuelaing.lab2.data.User;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface UserService {

    User create( User user);
    User findById( String id);
    List<User> getAll();
    void deleteById( String id);
    User update (User user, String userId);

    List<User> findUsersWithNameOrLastNameLike(String queryTest);

    List<User> findUsersCreateAfter(Date startDate);



}
