package db.dao;

import db.model.Announcement;
import db.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.util.*;

public class UsersDaoJdbcTemplate implements UsersDao {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM new_user";

    //language=SQL
    private final String SQL_CREATE_NEW_USER = "INSERT INTO new_user(name, mail, login, password) VALUES (?,?,?,?)";

    //language=SQL
    private final String SQL_CREATE_NEW_ANNOUNCEMENT = "INSERT INTO new_announce(name_announce, text, image, owner_id) VALUES (?,?,?,?)";

    //language=SQL
    private final String SQL_SELECT_USER_WITH_ANNOUNCEMENT = "SELECT new_user.*,new_announce.id as announce_id, new_announce.name_announce, new_announce.text, new_announce.image FROM new_user LEFT JOIN new_announce ON new_user.id = new_announce.owner_id WHERE new_user.login = ? ";

    private Map<String, User> usersMap = new HashMap<>();


    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        User user =  new User
                .BuilderForUser(resultSet.getString("name"),
                                resultSet.getString("mail"),
                                resultSet.getString("login"),
                                resultSet.getString("password"))
                .setId(resultSet.getInt("id"))
                .setAnnouncementList(new ArrayList<Announcement>())
                .build();

        usersMap.put(resultSet.getString("login"), user);
        return user;
    };


    private RowMapper<User> userWithAnnounceRowMapper = (resultSet, i) -> {
        String login = resultSet.getString("login");
        if (!usersMap.containsKey(login)) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            User user = new User
                    .BuilderForUser(name, mail, login, password)
                    .setId(id)
                    .setAnnouncementList(new ArrayList<Announcement>())
                    .build();
            usersMap.put(login, user);
        }

        Announcement announcement =
                new Announcement
                        .BuilderForAnnouncement(resultSet.getString("name_announce"),
                                                resultSet.getString("text"),
                                                resultSet.getString("image"))
                        .setId(resultSet.getInt("announce_id"))
                        .setOwner(usersMap.get(login))
                        .build();

        usersMap.get(login).getAnnouncementList().add(announcement);

        return usersMap.get(login);
    };

    public UsersDaoJdbcTemplate(DataSource dataSource){ this.jdbcTemplate = new JdbcTemplate(dataSource); }

    @Override
    public Optional<User> findByLogin(String login) {
        List<User> listAllUsers = findAll();

        for (User user : listAllUsers){
            if (user.getLogin().equals(login))
                return Optional.of(new User
                        .BuilderForUser( user.getName(), user.getEmail(), login, user.getPassword())
                        .setId(user.getId()).build());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAllAnnounce(String login) {
        if (usersMap.containsKey(login))
            usersMap.get(login).setAnnouncementList(new ArrayList<>());

        jdbcTemplate.query(SQL_SELECT_USER_WITH_ANNOUNCEMENT, userWithAnnounceRowMapper, login);

        if (usersMap.containsKey(login))
            return Optional.of(usersMap.get(login));
        return Optional.empty();
    }

    @Override
    public List<User> findAllAnnouncement() {
        List<User> list = new ArrayList<User>();
        if (!usersMap.isEmpty()) {
            for (String login : usersMap.keySet()) {
                Optional<User> opt = findByLoginAllAnnounce(login);
                if(opt.isPresent())
                    list.add(opt.get());
            }
        }
        return list;
    }

    @Override
    public void saveAnnouncement(Announcement model) { jdbcTemplate.update(SQL_CREATE_NEW_ANNOUNCEMENT, model.getName(), model.getTextAnnounce(), model.getImageURL(), model.getOwner().getId()); }

    @Override
    public Optional<User> find(Integer id) {
        List<User> listAllUsers = findAll();
        for (User user : listAllUsers){
            if (user.getId().equals(id))
                return Optional.of(new User
                        .BuilderForUser(user.getName(), user.getEmail(), user.getLogin(), user.getPassword())
                        .setId(id)
                        .build());
        }
        return Optional.empty();
    }

    @Override
    public void save(User model) { jdbcTemplate.update(SQL_CREATE_NEW_USER, model.getName(), model.getEmail(), model.getLogin(), model.getPassword()); }

    @Override
    public void update(User model) { }

    @Override
    public void delete(Integer id) { }

    @Override
    public List<User> findAll() { return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper); }

    @Override
    public Map<String, User> getUsersMap(){ return usersMap; }

}
