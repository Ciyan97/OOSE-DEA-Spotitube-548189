package nl.han.oose.services.rest;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.persistence.DAO;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.tracks.Song;
import nl.han.oose.domain.tracks.Track;
import nl.han.oose.domain.tracks.Video;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Path("utilities")
public class UtilService {
    private DAO<User> userDAO;
    private DAO<Playlist> playlistDAO;
    private DAO<Track> trackDAO;

    public UtilService() {}

    @Inject
    public UtilService(DAO<User> userDAO, DAO<Playlist> playlistDAO, DAO<Track> trackDAO) {
        this.userDAO = userDAO;
        this.playlistDAO = playlistDAO;
        this.trackDAO = trackDAO;
    }

    @Path("database-script")
    @GET
    public Response insertDatabaseTestData() throws DatabaseException {
        insertIntoDatabase();
        return Response.ok().build();
    }

    /*
    It's just an insert script.
    */
    private void insertIntoDatabase() throws DatabaseException {
        User user = new User("user", "password");

        Playlist playlist = new Playlist();

        Track firstSong = new Song("Despacito", "Jason Derulo", 300,
                "https://www.youtube.com/watch?v=kJQP7kiw5Fk", false, playlist,
                "Best Meme Songs", 0);
        Track secondSong = new Song("Thriller", "Michael Jackson", 275,
                "https://www.youtube.com/watch?v=sOnqjkJTMaA", false, playlist,
                "Best Meme Songs", 0);
        Track thirdSong = new Song("billiejean", "Michael Jackson", 275,
                "https://www.youtube.com/watch?v=sOnqjkJTMaA", false, playlist,
                "test", 0);

        Track firstVideo = new Video("How to be a Ninja", "Ryan Higa", 500,
                "https://www.youtube.com/watch?v=JdLCEwEFCMU", false, playlist,
                "26-07-2007", "Learn how to be a ninja");
        Track secondVideo = new Video("10 TIPS", "Default youtuber", 666,
                "https://www.youtube.com", true, playlist,
                "26-07-2019", "Elke standaard klikbeet video.");
        Track thirdVideo = new Video("1100 TIPS", "Default youtuber", 666,
                "https://www.youtube.com", true, playlist,
                "26-07-2019", "Elke standaard klikbeet video.");

        Set<Track> tracks = new HashSet<Track>();
        tracks.add(firstSong);
        tracks.add(secondSong);
        tracks.add(firstVideo);
        tracks.add(secondVideo);
        tracks.add(thirdSong);

        playlist.setName("Mijn Coolste Playlist");
        playlist.setOwner(user);
        playlist.setTracks(tracks);

        userDAO.save(user);
        playlistDAO.save(playlist);
    }
}

