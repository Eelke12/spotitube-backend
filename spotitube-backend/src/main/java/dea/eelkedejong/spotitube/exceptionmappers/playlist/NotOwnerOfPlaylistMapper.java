package dea.eelkedejong.spotitube.exceptionmappers.playlist;

import dea.eelkedejong.spotitube.playlist.exceptions.NotOwnerOfPlaylistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotOwnerOfPlaylistMapper implements ExceptionMapper<NotOwnerOfPlaylistException> {
    @Override
    public Response toResponse(NotOwnerOfPlaylistException e) {
        return Response
                .status(400)
                .entity(e.getMessage())
                .build();
    }
}
