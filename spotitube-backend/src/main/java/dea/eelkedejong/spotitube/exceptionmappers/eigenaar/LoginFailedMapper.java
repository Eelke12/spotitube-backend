package dea.eelkedejong.spotitube.exceptionmappers.eigenaar;

import dea.eelkedejong.spotitube.eigenaar.exceptions.LoginFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginFailedMapper implements ExceptionMapper<LoginFailedException> {
    @Override
    public Response toResponse(LoginFailedException e) {
        return Response
                .status(401)
                .entity(e.getMessage())
                .build();
    }
}
