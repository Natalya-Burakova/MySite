package db.model.dao;

import db.model.User;

import java.util.List;

public interface UsersDao extends CrudDao<User> {
    List<User> findAllByLogin(String login);
}
