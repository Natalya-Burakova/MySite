package db.model;


public class User {
    private Integer id;
    private String name;
    private String email;
    private String login;
    private String password;


    public User() {}

    public User(Integer id, String name, String email, String login, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getName(){ return name; }

    public String getEmail(){ return email; }

    public String getLogin(){ return login; }

    public String getPassword(){ return password; }

    public void setName(String name){ this.name = name; }

    public void setEmail(String email){ this.email = email; }

    public void setLogin(String login){ this.login = login; }

    public void setPassword(String password){ this.password = password; }



}
