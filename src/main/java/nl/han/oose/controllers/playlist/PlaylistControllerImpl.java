package nl.han.oose.controllers.playlist;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.requests.PlaylistRequest;
import nl.han.oose.domain.responses.PlaylistResponse;
import nl.han.oose.domain.tracks.Track;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlaylistControllerImpl implements PlaylistController {
    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    @Inject
    public PlaylistControllerImpl(PlaylistDAO playlistDAO, TrackDAO trackDAO) {
        this.playlistDAO = playlistDAO;
        this.trackDAO = trackDAO;
    }

    @Override
    public PlaylistResponse getAllPlaylists(User user)
            throws DatabaseException, EntityNotFoundException {
        return generatePlaylistResponse(user.getId());
    }

    @Override
    public PlaylistResponse deletePlaylist(int id, User user)
            throws DatabaseException, EntityNotFoundException {
        deleteTracksInPlaylist(id);
        playlistDAO.delete(id);
        return generatePlaylistResponse(user.getId());
    }

    @Override
    public PlaylistResponse addPlaylist(PlaylistRequest requestBody, User user)
            throws DatabaseException, EntityNotFoundException {
        Playlist playlist = new Playlist(requestBody.getName(), user, requestBody.getTracks());
        playlistDAO.save(playlist);
        return generatePlaylistResponse(user.getId());
    }

    @Override
    public PlaylistResponse updatePlaylist(Playlist requestBody, User user, int id)
            throws DatabaseException, EntityNotFoundException {
        playlistDAO.updateByName(requestBody);
        return generatePlaylistResponse(user.getId());
    }

    private PlaylistResponse generatePlaylistResponse(int id) throws DatabaseException, EntityNotFoundException {
        List<Playlist> playlists = playlistDAO.getAll(id);
        List<Track> tracks = getTracksInPlaylists(playlists);
        return new PlaylistResponse(playlists, calculateLength(tracks));
    }

    private List<Track> getTracksInPlaylists(List<Playlist> playlists)
            throws DatabaseException, EntityNotFoundException {
        List<Track> tracks = new ArrayList<Track>();
        for (Playlist playlist: playlists) {
            Set<Track> tracksInPlaylist = trackDAO.getAllInPlaylist(playlist.getId());
            playlist.setTracks(tracksInPlaylist);
            tracks.addAll(tracksInPlaylist);
        }
        return tracks;
    }

    private void deleteTracksInPlaylist(int id) throws DatabaseException, EntityNotFoundException {
        Set<Track> tracksToDelete = trackDAO.getAllInPlaylist(id);
        for (Track track: tracksToDelete) {
            trackDAO.delete(track.getId());
        }
    }

    private int calculateLength(List<Track> tracks){
        int length = 0;
        for (Track track: tracks) {
            length += track.getDuration();
        }
        return length;
    }
}
