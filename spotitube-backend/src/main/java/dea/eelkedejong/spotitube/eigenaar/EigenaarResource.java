package dea.eelkedejong.spotitube.eigenaar;

import dea.eelkedejong.spotitube.eigenaar.dto.LoginRequest;
import dea.eelkedejong.spotitube.eigenaar.dto.LoginResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class EigenaarResource {

    @Inject
    private EigenaarService eigenaarService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {

        LoginResponse naamToken = eigenaarService.login(loginRequest);

        return Response
                .status(200)
                .entity(naamToken)
                .build();
    }

    public void setEigenaarService(EigenaarService eigenaarService) {
        this.eigenaarService = eigenaarService;
    }
}
