package bl.model;


public class District {

    private int id;
    private String name;
    private String map;

    public District() {
    }

    public District(String name, String map) {
        this.name = name;
        this.map = map;
    }

    public District(int id, String name, String map) {
        this.id = id;
        this.name = name;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
