package dea.eelkedejong.spotitube.eigenaar;

import dea.eelkedejong.spotitube.eigenaar.dto.LoginRequest;
import dea.eelkedejong.spotitube.eigenaar.dto.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestEigenaarResource {
    private EigenaarResource sut;
    private EigenaarService mockedEigenaarService;

    @BeforeEach
    public void setup() {
        sut = new EigenaarResource();
        mockedEigenaarService = Mockito.mock(EigenaarService.class);

        sut.setEigenaarService(mockedEigenaarService);
    }

    @Test
    public void testLogin() {
        var itemToReturn = new LoginResponse("testgebruiker", "1234");
        LoginRequest loginRequest = new LoginRequest("test", "test123");
        Mockito.when(mockedEigenaarService.login(loginRequest)).thenReturn(itemToReturn);

        var testValue = sut.login(loginRequest);

        Assertions.assertEquals(testValue.getEntity(), itemToReturn);
    }
}
