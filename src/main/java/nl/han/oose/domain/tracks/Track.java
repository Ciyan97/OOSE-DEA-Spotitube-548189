package nl.han.oose.domain.tracks;

import javax.persistence.*;

@Entity
public abstract class Track {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @Column
    private String performer;

    @Column
    private int duration;

    @Column
    private String url;

    @Column
    private boolean offlineAvailable;

    public Track(String title, String performer, int duration, String url, boolean offlineAvailable) {
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.url = url;
        this.offlineAvailable = offlineAvailable;
    }

    public Track() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPerformer() {
        return performer;
    }
    public void setPerformer(String performer) {
        this.performer = performer;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
