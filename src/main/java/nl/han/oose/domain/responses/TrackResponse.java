package nl.han.oose.domain.responses;

import com.google.gson.annotations.Expose;
import nl.han.oose.domain.tracks.Track;

import java.util.List;
import java.util.Set;

public class TrackResponse {
    @Expose
    private Set<Track> tracks;

    public TrackResponse() {
    }

    public TrackResponse(Set<Track> tracks) {
        this.tracks = tracks;
    }
}
