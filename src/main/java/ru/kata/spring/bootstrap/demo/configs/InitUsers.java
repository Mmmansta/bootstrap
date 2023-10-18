package ru.kata.spring.bootstrap.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.bootstrap.demo.dao.UserDao;
import ru.kata.spring.bootstrap.demo.model.Role;
import ru.kata.spring.bootstrap.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class InitUsers {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    UserDao userDao;


    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void addUsers(){

        userDao.deleteAll();

        userDao.addUser(new User(
                List.of(new Role("ROLE_ADMIN")),
                "admin",
                encoder.encode("admin"),
                22,
                "admin@mail.ru"
        ));

        userDao.addUser(new User(
                List.of(new Role("ROLE_USER")),
                "user",
                encoder.encode("user"),
                22,
                "user@mail.ru"
        ));
    }
}
