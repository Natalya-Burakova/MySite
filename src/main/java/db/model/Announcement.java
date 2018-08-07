package db.model;

public class Announcement {

    private Integer id;
    private String name;
    private String textAnnounce;
    private String imageURL;
    private User owner;

    public Announcement() {}

    public Announcement(String name, String textAnnounce, String imageURL){
        this.name = name;
        this.textAnnounce = textAnnounce;
        this.imageURL = imageURL;
    }

    public Announcement(Integer id, String name, String textAnnounce, String imageURL){
        this.id = id;
        this.name = name;
        this.textAnnounce = textAnnounce;
        this.imageURL = imageURL;
    }

    public Announcement(String name, String textAnnounce, String imageURL, User owner){
        this.name = name;
        this.textAnnounce = textAnnounce;
        this.imageURL = imageURL;
        this.owner = owner;
    }

    public Announcement(Integer id, String name, String textAnnounce, String imageURL, User owner){
        this.id = id;
        this.name = name;
        this.textAnnounce = textAnnounce;
        this.imageURL = imageURL;
        this.owner = owner;
    }


    public Integer getId(){ return id; }

    public String getName(){ return name; }

    public String getTextAnnounce(){ return textAnnounce; }

    public String getImageURL(){ return imageURL; }

    public User getOwner() { return owner; }

    public void setId(Integer id){ this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setTextAnnounce(String textAnnounce){ this.textAnnounce = textAnnounce; }

    public void setImageURL(String imageURL){ this.imageURL = imageURL; }

    public void setOwner(User use) { this.owner = owner; }


}
