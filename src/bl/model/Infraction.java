package bl.model;


public class Infraction {
    private int id;
    private String label;

    public Infraction() {
    }

    public Infraction(String label) {
        this.label = label;
    }

    public Infraction(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
