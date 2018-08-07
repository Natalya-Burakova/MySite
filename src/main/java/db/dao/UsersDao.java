package db.dao;


import db.model.Announcement;
import db.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UsersDao extends CrudDao<User> {
    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAllAnnounce(String login);
    List<User> findAllAnnouncement();
    void saveAnnouncement(Announcement model);
    Map<String, User> getUsersMap();
}
