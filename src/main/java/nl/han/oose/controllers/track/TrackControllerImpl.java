package nl.han.oose.controllers.track;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.requests.TrackRequest;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.domain.tracks.Track;

import javax.inject.Inject;
import java.util.List;

public class TrackControllerImpl implements TrackController{
    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    @Inject
    public TrackControllerImpl(PlaylistDAO playlistDAO, TrackDAO trackDAO) {
        this.playlistDAO = playlistDAO;
        this.trackDAO = trackDAO;
    }

    @Override
    public TrackResponse getAllTrackByPlaylistId(int id) throws DatabaseException, EntityNotFoundException {
        List<Track> tracks = trackDAO.getAllInPlaylist(id);
        return new TrackResponse(tracks);
    }

    @Override
    public TrackResponse deleteTrackInPlaylist(int playlistId, int trackId)
            throws DatabaseException, EntityNotFoundException {
        Playlist playlist = removeTrackInPlaylist(playlistDAO.getPlaylist(playlistId), trackId);
        playlistDAO.update(playlist);
        return new TrackResponse(playlist.getTracks());
    }

    @Override
    public TrackResponse addTrackToPlaylist(TrackRequest requestBody, int id)
            throws DatabaseException, EntityNotFoundException {
        trackDAO.updateTrackIsOfflineAvailable(requestBody.getId(), requestBody.isOfflineAvailable());
        Track track = trackDAO.getTrack(requestBody.getId());
        Playlist playlist = playlistDAO.getPlaylist(id);
        List<Track> tracks = trackDAO.getAllInPlaylist(id);
        tracks.add(track);
        playlist.setTracks(tracks);
        playlistDAO.update(playlist);
        return new TrackResponse(trackDAO.getAllInPlaylist(id));
    }

    @Override
    public TrackResponse getAllTracks() throws DatabaseException, EntityNotFoundException {
        List<Track> tracks = trackDAO.getAll();
        return new TrackResponse(tracks);
    }

    @Override
    public TrackResponse getAllTracksNotInPlaylist(int id) throws DatabaseException, EntityNotFoundException {
        return new TrackResponse(getTracksNotInPlaylist(id));
    }

    private List<Track> getTracksNotInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException {
        List<Track> tracks = trackDAO.getAll();
        List<Track> tracksInPlaylist = trackDAO.getAllInPlaylist(playlistId);
        for (Track track: tracks) {
            if (tracksInPlaylist.contains(track)) {
                tracks.remove(track);
            }
        }
        return tracks;
    }

    private Playlist removeTrackInPlaylist(Playlist playlist, int id)
            throws DatabaseException, EntityNotFoundException {
        List<Track> tracksInPlaylist = trackDAO.getAllInPlaylist(playlist.getId());
        Track trackToRemove = null;
        for (Track track : tracksInPlaylist) {
            if (track.getId() == id) {
                trackToRemove = track;
            }
        }
        tracksInPlaylist.remove(trackToRemove);
        playlist.setTracks(tracksInPlaylist);
        return playlist;
    }
}
