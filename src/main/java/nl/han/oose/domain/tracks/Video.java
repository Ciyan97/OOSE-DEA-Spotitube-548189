package nl.han.oose.domain.tracks;

import nl.han.oose.domain.requests.TrackRequest;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Video extends Track {

    @Column
    private String publicationData;

    @Column
    private String description;

    public Video(String title, String performer, int duration, String url, boolean offlineAvailable,
                 String publicationData, String description) {
        super(title, performer, duration, url, offlineAvailable);
        this.publicationData = publicationData;
        this.description = description;
    }

    public Video() {
        super();
    }

    public static Track createFromRequestBody(TrackRequest trackRequest){
        Video video = new Video();
        video.setPerformer(trackRequest.getPerformer());
        video.setTitle(trackRequest.getTitle());
        video.setDuration(trackRequest.getDuration());
        video.setOfflineAvailable(trackRequest.isOfflineAvailable());
        video.setPublicationData(trackRequest.getPublicationDate());
        video.setDescription(trackRequest.getDescription());
        return video;
    }

    public String getPublicationData() {
        return publicationData;
    }
    public void setPublicationData(String publicationData) {
        this.publicationData = publicationData;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
