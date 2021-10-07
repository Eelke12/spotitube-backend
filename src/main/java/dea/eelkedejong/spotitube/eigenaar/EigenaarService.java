package dea.eelkedejong.spotitube.eigenaar;

import dea.eelkedejong.spotitube.eigenaar.dao.EigenaarDao;
import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;
import dea.eelkedejong.spotitube.eigenaar.dto.LoginRequest;
import dea.eelkedejong.spotitube.eigenaar.dto.LoginResponse;
import dea.eelkedejong.spotitube.eigenaar.exceptions.LoginFailedException;
import dea.eelkedejong.spotitube.eigenaar.exceptions.NoValidTokenException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.UUID;

public class EigenaarService {

    private EigenaarDao eigenaarDao;

    public LoginResponse login(LoginRequest loginRequest) {
        loginRequest.setWachtwoord(DigestUtils.md5Hex(loginRequest.getWachtwoord()));
        String token = UUID.randomUUID().toString();

        Eigenaar eigenaar = eigenaarDao.getEigenaarByUsernameOrToken(loginRequest.getGebruikersnaam());

        if (eigenaar == null || !eigenaar.getWachtwoord().equals(loginRequest.getWachtwoord())) {
            throw new LoginFailedException("login details incorrect");
        }

        eigenaarDao.updateToken(token, eigenaar.getId());
        return new LoginResponse(eigenaar.getGebruikersnaam(), token);
    }

    public Eigenaar checkToken(String token) {
        Eigenaar eigenaar = eigenaarDao.getEigenaarByUsernameOrToken(token);

        if (eigenaar == null) {
            throw new NoValidTokenException("no valid token");
        }

        return eigenaar;
    }

    @Inject
    public void setEigenaarDao(EigenaarDao eigenaarDao) {
        this.eigenaarDao = eigenaarDao;
    }
}
