package nl.han.oose.domain.tracks;

import com.google.gson.annotations.Expose;
import nl.han.oose.domain.Playlist;

import javax.persistence.*;

@Entity
public abstract class Track {
    @Expose
    @Id
    @GeneratedValue
    private int id;

    @Expose
    @Column
    private String title;

    @Expose
    @Column
    private String performer;

    @Expose
    @Column
    private int duration;

    @Expose
    @Column
    private String url;

    @Expose
    @Column
    private boolean offlineAvailable;

    @ManyToOne
    private Playlist playlist;

    public Track(String title, String performer, int duration, String url, boolean offlineAvailable,
                 Playlist playlist) {
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.url = url;
        this.offlineAvailable = offlineAvailable;
        this.playlist = playlist;
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
    public int getDuration() {
        return duration;
    }
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
