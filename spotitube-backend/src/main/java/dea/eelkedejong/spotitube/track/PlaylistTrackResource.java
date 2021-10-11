package dea.eelkedejong.spotitube.track;

import dea.eelkedejong.spotitube.track.dto.Track;
import dea.eelkedejong.spotitube.track.dto.Tracks;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class PlaylistTrackResource {

    private TrackService trackService;

    @GET
    @Path("playlists/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksOfPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {

        Tracks tracks = trackService.getTracksForPlaylist(id, token);

        return Response
                .status(200)
                .entity(tracks)
                .build();
    }

    @DELETE
    @Path("playlists/{id}/tracks/{trackid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackOfPlaylist(@PathParam("id") int playlistId,
                                          @PathParam("trackid") int trackId,
                                          @QueryParam("token") String token) {

        Tracks tracks = trackService.deleteTrack(playlistId, trackId, token);

        return Response
                .status(200)
                .entity(tracks)
                .build();
    }

    @POST
    @Path("playlists/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int id, @QueryParam("token") String token, Track track) {

        Tracks tracks = trackService.addTrack(id, token, track);

        return Response
                .status(200)
                .entity(tracks)
                .build();
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
