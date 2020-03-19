package nl.han.oose.domain.requests;

import nl.han.oose.domain.Constants;
public class TrackRequest {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private transient String album;
    private transient int playcount;
    private transient String publicationDate;
    private transient String description;
    private boolean offlineAvailable;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPerformer() {
        return performer;
    }
    public void setPerformer(String performer) {
        this.performer = performer;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
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
    public String getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

    public String getType() {
        if (album != null) {
            return Constants.SONG;
        }
        return Constants.VIDEO;
    }
}