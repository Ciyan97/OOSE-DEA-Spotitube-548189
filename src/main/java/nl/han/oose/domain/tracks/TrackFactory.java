package nl.han.oose.domain.tracks;

import nl.han.oose.domain.Constants;
import nl.han.oose.domain.requests.TrackRequest;
public class TrackFactory {
    private static TrackFactory instance;

    private TrackFactory(){}

    public static TrackFactory getInstance() {
        if (instance == null)
            instance = new TrackFactory();
        return instance;
    }

    public Track create(String type, TrackRequest trackRequest) throws IllegalArgumentException {
        if (Constants.SONG.equals(type)) {
            return Song.createFromRequestBody(trackRequest);
        } else if (Constants.VIDEO.equals(type)) {
            return Video.createFromRequestBody(trackRequest);
        } else {
            throw new IllegalArgumentException("Type does not exist.");
        }
    }
}
