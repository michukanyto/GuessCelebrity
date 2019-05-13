package Model;

import java.util.ArrayList;

public class Celebrity {
    protected String name;
    protected String photo;
    protected ArrayList<Celebrity> celebrities = new ArrayList<>();

    public Celebrity(String name, String photo) {
        this.name = name;
        this.photo = photo;
        celebrities.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArrayList<Celebrity> getCelebrities() {
        return celebrities;
    }
}
