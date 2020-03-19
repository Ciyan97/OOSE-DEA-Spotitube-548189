package nl.han.oose.domain.requests;

import nl.han.oose.domain.tracks.Track;
import java.util.List;

public class PlaylistRequest {
    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;

    public PlaylistRequest() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
