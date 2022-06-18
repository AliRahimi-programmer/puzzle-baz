package puzzlebaz.example.puzzlebaz.BaseClass;

public class StatusClass {
    int status, id;

    public StatusClass(int status, int id){
        this.status = status;
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }
}
