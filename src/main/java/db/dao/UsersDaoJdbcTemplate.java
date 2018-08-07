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

    //language=SQL
    private final String SQL_SELECT_ALL_ANNOUNCEMENT = "SELECT new_user.*,new_announce.id as announce_id, new_announce.name_announce, new_announce.text, new_announce.image FROM new_user LEFT JOIN new_announce ON new_user.id = new_announce.owner_id";


    private Map<String, User> usersMap = new HashMap<>();


    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        User user =  new User(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("mail"),
                resultSet.getString("login"),
                resultSet.getString("password"), new ArrayList<Announcement>());
        usersMap.put(resultSet.getString("login"), user);
        return user;
    };


    private RowMapper<User> userWithAnnouceRowMapper = (resultSet, i) -> {
        String login = resultSet.getString("login");
        if (!usersMap.containsKey(login)) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            User user = new User(id, name, mail, login, password, new ArrayList<Announcement>());
            usersMap.put(login, user);
        }
        Announcement announcement =
                new Announcement(resultSet.getInt("announce_id"), resultSet.getString("name_announce"), resultSet.getString("text"), resultSet.getString("image"), usersMap.get(login));
        usersMap.get(login).getAnnouncementList().add(announcement);
        return  usersMap.get(login);
    };

    public UsersDaoJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        List<User> listAllUsers = findAll();
        for (User user : listAllUsers){
            if (user.getLogin().equals(login))
                return Optional.of(new User(user.getId(), user.getName(), user.getEmail(), login, user.getPassword()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAllAnnounce(String login) {
        jdbcTemplate.query(SQL_SELECT_USER_WITH_ANNOUNCEMENT, userWithAnnouceRowMapper, login);
        if (usersMap.containsKey(login))
            return Optional.of(usersMap.get(login));
        return Optional.empty();
    }

    @Override
    public List<User> findAllAnnouncement() {
        List<User> list = jdbcTemplate.query(SQL_SELECT_ALL_ANNOUNCEMENT, userWithAnnouceRowMapper);
        return list;
    }

    @Override
    public void saveAnnouncement(Announcement model) {
        jdbcTemplate.update(SQL_CREATE_NEW_ANNOUNCEMENT, model.getName(), model.getTextAnnounce(), model.getImageURL(), model.getOwner().getId());
    }

    @Override
    public Optional<User> find(Integer id) {
        List<User> listAllUsers = findAll();
        for (User user : listAllUsers){
            if (user.getId().equals(id))
                return Optional.of(new User(id, user.getName(), user.getEmail(), user.getLogin(), user.getPassword()));
        }
        return Optional.empty();
    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(SQL_CREATE_NEW_USER, model.getName(), model.getEmail(), model.getLogin(), model.getPassword());
    }

    @Override
    public void update(User model) { }

    @Override
    public void delete(Integer id) { }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public Map<String, User> getUsersMap(){ return usersMap; }

}
