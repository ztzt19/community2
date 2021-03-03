package life.majiang.community.dto;

/**
 * @author zt
 * @create 2021-03-03 14:17
 */
public class GithubUser {
    private Long id;
    private String name;
    private String bio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
