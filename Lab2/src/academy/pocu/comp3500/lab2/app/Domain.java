package academy.pocu.comp3500.lab2.app;

public class Domain {
    private int id;
    private String url;

    public Domain() {}

    // Constructor
    public Domain(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return Integer.valueOf(id);
    }

    @Override
    public String toString() {
        return "Domain{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
