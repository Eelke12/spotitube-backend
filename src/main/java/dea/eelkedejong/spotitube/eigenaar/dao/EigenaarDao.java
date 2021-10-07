package dea.eelkedejong.spotitube.eigenaar.dao;

import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;

public interface EigenaarDao {
    void updateToken(String token, int id);
    Eigenaar getEigenaarByUsernameOrToken(String gebruikersnaam);
}
