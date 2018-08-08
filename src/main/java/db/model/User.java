package db.model;

import java.util.List;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String login;
    private String password;
    private List<Announcement> announcementList;

    public static class BuilderForUser {
        private Integer id;
        private String name;
        private String email;
        private String login;
        private String password;
        private List<Announcement> announcementList;

        public BuilderForUser(String name, String email, String login, String password) {
            this.name = name;
            this.email = email;
            this.login = login;
            this.password = password;
        }

        public BuilderForUser setId(Integer id) {
            this.id  = id;
            return this;
        }

        public BuilderForUser setAnnouncementList(List<Announcement> announcementList) {
            this.announcementList  = announcementList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(BuilderForUser builder) {
        id = builder.id;
        name = builder.name;
        login = builder.login;
        password = builder.password;
        email = builder.email;
        announcementList = builder.announcementList;
    }

    public Integer getId(){ return id; }

    public String getName(){ return name; }

    public String getEmail(){ return email; }

    public String getLogin(){ return login; }

    public String getPassword(){ return password; }

    public List<Announcement> getAnnouncementList() { return announcementList; }

    public void setId(Integer id){ this.id = id; }

    public void setName(String name){ this.name = name; }

    public void setEmail(String email){ this.email = email; }

    public void setLogin(String login){ this.login = login; }

    public void setPassword(String password){ this.password = password; }

    public void setAnnouncementList(List<Announcement> announcementList) { this.announcementList = announcementList; }

}
