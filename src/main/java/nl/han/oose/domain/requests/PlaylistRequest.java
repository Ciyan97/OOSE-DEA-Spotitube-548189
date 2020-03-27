package nl.han.oose.domain.requests;

import nl.han.oose.domain.tracks.Track;

import java.util.Set;

public class PlaylistRequest {
    private int id;
    private String name;
    private boolean owner;
    private Set<Track> tracks;

    public PlaylistRequest() { }

    public String getName() {
        return name;
    }
    public Set<Track> getTracks() {
        return tracks;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOwner(boolean owner) {
        this.owner = owner;
    }
    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }
}
