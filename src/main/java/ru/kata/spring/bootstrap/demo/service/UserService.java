package ru.kata.spring.bootstrap.demo.service;







import ru.kata.spring.bootstrap.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(int id);

    void addUser(User user);

    User updateUser(int id, User updatedUser);

    void deleteUser(int id);
}