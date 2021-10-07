package dea.eelkedejong.spotitube.eigenaar;

import dea.eelkedejong.spotitube.eigenaar.dao.EigenaarDao;
import dea.eelkedejong.spotitube.eigenaar.dao.EigenaarDaoImpl;
import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;
import dea.eelkedejong.spotitube.eigenaar.dto.LoginRequest;
import dea.eelkedejong.spotitube.eigenaar.exceptions.LoginFailedException;
import dea.eelkedejong.spotitube.eigenaar.exceptions.NoValidTokenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestEigenaarService {
    private EigenaarService sut;
    private EigenaarDao mockedEigenaarDao;

    @BeforeEach
    public void setup() {
        sut = new EigenaarService();
        mockedEigenaarDao = Mockito.mock(EigenaarDaoImpl.class);

        sut.setEigenaarDao(mockedEigenaarDao);
    }

    @Test
    public void testLogin() {
        var itemToReturn = new Eigenaar(1, "Eelke", "cc03e747a6afbbcbf8be7668acfebee5", "1234");
        LoginRequest loginRequest = new LoginRequest("Eelke", "test123");

        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("Eelke")).thenReturn(itemToReturn);

        sut.login(loginRequest);

        Mockito.verify(mockedEigenaarDao).getEigenaarByUsernameOrToken("Eelke");
    }

    @Test
    public void testBadLogin() {
        var itemToReturn = new Eigenaar(1, "Eelke", "test123", "1234");
        LoginRequest loginRequest = new LoginRequest("Eelke", "test123");
        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("test")).thenReturn(itemToReturn);

        Assertions.assertThrows(LoginFailedException.class, () -> sut.login(loginRequest));
    }

    @Test
    public void testToken() {
        var itemToReturn = new Eigenaar(1, "Eelke", "test123", "1234");
        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("Eelke")).thenReturn(itemToReturn);

        Assertions.assertThrows(NoValidTokenException.class, () -> sut.checkToken("123"));
    }
}
