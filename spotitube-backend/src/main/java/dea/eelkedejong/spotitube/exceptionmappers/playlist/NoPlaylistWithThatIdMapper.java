package dea.eelkedejong.spotitube.exceptionmappers.playlist;

import dea.eelkedejong.spotitube.playlist.exceptions.NoPlaylistWithThatIdException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoPlaylistWithThatIdMapper implements ExceptionMapper<NoPlaylistWithThatIdException> {
    @Override
    public Response toResponse(NoPlaylistWithThatIdException e) {
        return Response
                .status(404)
                .entity(e.getMessage())
                .build();
    }
}
