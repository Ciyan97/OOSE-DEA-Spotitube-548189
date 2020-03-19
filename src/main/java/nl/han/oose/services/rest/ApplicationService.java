package nl.han.oose.services.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class ApplicationService extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
                LoginService.class,
                PlaylistService.class,
                TrackService.class,
                UtilService.class));
    }
}
