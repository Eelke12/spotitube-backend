package dea.eelkedejong.spotitube.track;

import dea.eelkedejong.spotitube.track.dto.Tracks;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("forPlaylist") int id,
                              @QueryParam("token") String token) {

        Tracks tracks = trackService.getTracksThatAreNotInPlaylist(id, token);

        return Response
                .status(200)
                .entity(tracks)
                .build();
    }
}
