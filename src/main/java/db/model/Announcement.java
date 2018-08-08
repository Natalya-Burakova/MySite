package db.model;

public class Announcement {

    private Integer id;
    private String name;
    private String textAnnounce;
    private String imageURL;
    private User owner;

    public static class BuilderForAnnouncement {
        private Integer id;
        private String name;
        private String textAnnounce;
        private String imageURL;
        private User owner;

        public BuilderForAnnouncement(String name, String textAnnounce, String imageURL) {
            this.name = name;
            this.textAnnounce = textAnnounce;
            this.imageURL = imageURL;
        }

        public BuilderForAnnouncement setId(Integer id) {
            this.id  = id;
            return this;
        }

        public BuilderForAnnouncement setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Announcement build() {
            return new Announcement(this);
        }
    }

    private Announcement(BuilderForAnnouncement builder) {
        id = builder.id;
        name = builder.name;
        owner = builder.owner;
        imageURL = builder.imageURL;
        textAnnounce = builder.textAnnounce;
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

    public void setOwner(User owner) { this.owner = owner; }

}
