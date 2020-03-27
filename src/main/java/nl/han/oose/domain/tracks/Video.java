package nl.han.oose.domain.tracks;

import com.google.gson.annotations.Expose;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.requests.TrackRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Video extends Track {
    @Expose
    @Column
    private String publicationData;

    @Expose
    @Column
    private String description;

    public Video(String title, String performer, int duration, String url, boolean offlineAvailable,
                 Playlist playlist, String publicationData, String description) {
        super(title, performer, duration, url, offlineAvailable, playlist);
        this.publicationData = publicationData;
        this.description = description;
    }

    public Video() {
        super();
    }
}
