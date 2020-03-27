package nl.han.oose.domain.tracks;

import com.google.gson.annotations.Expose;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.requests.TrackRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Song extends Track {
    @Expose
    @Column
    private String album;

    @Expose
    @Column
    private int playcount;

    public Song(String title, String performer, int duration, String url, boolean offlineAvailable,
                Playlist playlist, String album, int playcount) {
        super(title, performer, duration, url, offlineAvailable, playlist);
        this.album = album;
        this.playcount = playcount;
    }

    public Song() {
        super();
    }
}
