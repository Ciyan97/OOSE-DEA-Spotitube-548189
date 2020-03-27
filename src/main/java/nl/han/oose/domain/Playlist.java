package nl.han.oose.domain;

import com.google.gson.annotations.Expose;
import nl.han.oose.domain.tracks.Track;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Playlist {
    @Expose
    @Id
    @GeneratedValue
    private int id;

    @Expose
    @Column
    private String name;

    @Expose
    @ManyToOne
    private User owner;

    @JsonbTransient
    @Expose
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Track> tracks;

    public Playlist() {
    }

    public Playlist(String name, User owner, Set<Track> tracks) {
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Track> getTracks() {
        return tracks;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }
    public User getOwner() {
        return owner;
    }
}
