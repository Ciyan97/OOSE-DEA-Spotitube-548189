package nl.han.oose.persistence;

import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.tracks.Song;
import nl.han.oose.domain.tracks.Track;
import nl.han.oose.domain.tracks.Video;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.playlist.PlaylistDAOImpl;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.persistence.track.TrackDAOImpl;
import nl.han.oose.persistence.user.UserDAO;
import nl.han.oose.persistence.user.UserDAOImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public abstract class BasePersistenceIntegrationTest {
    protected SessionFactory newSessionFactory() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:mem:test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");

        return new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Playlist.class)
                .addAnnotatedClass(Track.class)
                .addAnnotatedClass(Song.class)
                .addAnnotatedClass(Video.class)
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(properties)
                                .build());
    }

    protected void fillDatabase() throws DatabaseException {
        UserDAO userDAO = new UserDAOImpl(newSessionFactory());
        TrackDAO trackDAO = new TrackDAOImpl(newSessionFactory());
        PlaylistDAO playlistDAO = new PlaylistDAOImpl(newSessionFactory());

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
