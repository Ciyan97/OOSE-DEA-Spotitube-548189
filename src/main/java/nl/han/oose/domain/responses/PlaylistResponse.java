package nl.han.oose.domain.responses;

import com.google.gson.annotations.Expose;
import nl.han.oose.domain.Playlist;

import java.util.List;

public class PlaylistResponse {
    @Expose
    private List<Playlist> playlists;

    @Expose
    private int length;

    public PlaylistResponse() {}

    public PlaylistResponse(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}