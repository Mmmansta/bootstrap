package ru.kata.spring.bootstrap.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.bootstrap.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u where u.name = '" + name + "'", User.class).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void deleteAll(){
        entityManager.createQuery("delete from User").executeUpdate();
    }

    @Override
    public User updateUser(int id, User updatedUser) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        return entityManager.merge(userToBeUpdated);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }
}