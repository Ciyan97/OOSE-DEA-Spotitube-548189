package nl.han.oose.domain.tracks;

import nl.han.oose.domain.requests.TrackRequest;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Song extends Track {

    @Column
    private String album;

    @Column
    private int playcount;

    public Song(String title, String performer, int duration, String url, boolean offlineAvailable,
                String album, int playcount) {
        super(title, performer, duration, url, offlineAvailable);
        this.album = album;
        this.playcount = playcount;
    }

    public static Track createFromRequestBody(TrackRequest trackRequest){
        Song song = new Song();
        song.setPerformer(trackRequest.getPerformer());
        song.setTitle(trackRequest.getTitle());
        song.setDuration(trackRequest.getDuration());
        song.setOfflineAvailable(trackRequest.isOfflineAvailable());
        song.setAlbum(trackRequest.getAlbum());
        song.setPlaycount(trackRequest.getPlaycount());
        return song;
    }

    public Song() {
        super();
    }

    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public int getPlaycount() {
        return playcount;
    }
    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }
}
