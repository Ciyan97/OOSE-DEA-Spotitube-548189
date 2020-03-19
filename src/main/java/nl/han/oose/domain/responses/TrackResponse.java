package nl.han.oose.domain.responses;

import nl.han.oose.domain.tracks.Track;

import java.util.List;

public class TrackResponse {
    private List<Track> tracks;

    public TrackResponse() {
    }

    public TrackResponse(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
