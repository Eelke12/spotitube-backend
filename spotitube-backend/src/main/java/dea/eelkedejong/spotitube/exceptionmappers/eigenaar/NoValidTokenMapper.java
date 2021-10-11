package dea.eelkedejong.spotitube.exceptionmappers.eigenaar;

import dea.eelkedejong.spotitube.eigenaar.exceptions.NoValidTokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoValidTokenMapper implements ExceptionMapper<NoValidTokenException> {
    @Override
    public Response toResponse(NoValidTokenException e) {
        return Response
                .status(400)
                .entity(e.getMessage())
                .build();
    }
}
