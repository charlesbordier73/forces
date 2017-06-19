package bl.model;


public class Unit {

    private int id;
    private String name;
    private String sigle;
    private String mail;

    public Unit() {
    }

    public Unit(String name, String sigle, String mail) {
        this.name = name;
        this.sigle = sigle;
        this.mail = mail;
    }

    public Unit(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public Unit(int id, String name, String sigle, String mail) {
        this.id = id;
        this.name = name;
        this.sigle = sigle;
        this.mail = mail;
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

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
