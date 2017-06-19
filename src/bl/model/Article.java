package bl.model;


public class Article {

    private int id;
    private String code;
    private int alinea;
    private String content;

    public Article() {
    }

    public Article(String code, int alinea, String content) {
        this.code = code;
        this.alinea = alinea;
        this.content = content;
    }

    public Article(String code, int alinea) {
        this.code = code;
        this.alinea = alinea;
    }

    public Article(String code) {
        this.code = code;
    }


    public Article(int id, String code, int alinea, String content) {
        this.id = id;
        this.code = code;
        this.alinea = alinea;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAlinea() {
        return alinea;
    }

    public void setAlinea(int alinea) {
        this.alinea = alinea;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
