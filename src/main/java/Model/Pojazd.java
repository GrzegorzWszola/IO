package Model;

public class Pojazd {
    private String id;
    private String status;

    public Pojazd(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatusPojazdu() {
        return status;
    }
}