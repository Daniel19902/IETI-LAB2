package edu.escuelaing.lab2.service;

import edu.escuelaing.lab2.data.User;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserServiceHashMap {
/*
    private HashMap<String, User> userHashMap = new HashMap<>();

    public HashMap<String, User> getUserHashMap() {
        return userHashMap;
    }

    public void setUserHashMap(HashMap<String, User> userHashMap) {
        this.userHashMap = userHashMap;
    }

    @Override
    public User create(User user) {

        userHashMap.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(String id) {
        return userHashMap.getOrDefault(id, null);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userHashMap.values());
    }

    @Override
    public void deleteById(String id) {
        userHashMap.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        userHashMap.remove(userId);
        userHashMap.put(userId, user);
        return user;
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryTest) {
        return null;
    }

    @Override
    public List<User> findUsersCreateAfter(Data startDate) {
        return null;
    }




*/
}