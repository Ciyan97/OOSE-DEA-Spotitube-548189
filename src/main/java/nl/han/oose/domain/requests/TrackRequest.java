package nl.han.oose.domain.requests;

public class TrackRequest {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private boolean offlineAvailable;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPerformer(String performer) {
        this.performer = performer;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}