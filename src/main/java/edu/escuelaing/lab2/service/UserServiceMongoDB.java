package edu.escuelaing.lab2.service;


import edu.escuelaing.lab2.data.User;
import edu.escuelaing.lab2.repository.UserRepository;
import edu.escuelaing.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        userRepository.deleteById(userId);
        return userRepository.insert(user);
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryTest) {
        return userRepository.findUsersWithNameOrLastNameLike(queryTest);
    }

    @Override
    public List<User> findUsersCreateAfter(Date startDate) {
        return userRepository.findUsersByCreateAtAfter(startDate);
    }



}
