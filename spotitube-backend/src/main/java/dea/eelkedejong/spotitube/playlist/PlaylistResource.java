package dea.eelkedejong.spotitube.playlist;

import dea.eelkedejong.spotitube.playlist.dto.Playlist;
import dea.eelkedejong.spotitube.playlist.dto.Playlists;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class PlaylistResource {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {

        Playlists playlists = playlistService.getPlaylists(token);

        return Response
                .status(200)
                .entity(playlists)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("playlists")
    public Response addPlaylist(Playlist playlist, @QueryParam("token") String token) {

        Playlists playlists = playlistService.addPlaylist(playlist, token);

        return Response
                .status(201)
                .entity(playlists)
                .build();
    }

    @DELETE
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id,
                                   @QueryParam("token") String token) {

        Playlists playlists = playlistService.deletePlaylist(id, token);

        return Response
                .status(200)
                .entity(playlists)
                .build();
    }

    @PUT
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@PathParam("id") int id,
                                 @QueryParam("token") String token,
                                 Playlist playlist) {

        Playlists playlists = playlistService.editPlaylist(id, playlist, token);

        return Response
                .status(200)
                .entity(playlists)
                .build();
    }

    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }
}
