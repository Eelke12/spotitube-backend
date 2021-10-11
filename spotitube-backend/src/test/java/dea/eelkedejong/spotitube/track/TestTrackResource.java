package dea.eelkedejong.spotitube.track;

import dea.eelkedejong.spotitube.track.dto.Tracks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestTrackResource {

    private TrackResource sut;
    private TrackService mockedTrackService;

    @BeforeEach
    public void setup() {
        sut = new TrackResource();
        mockedTrackService = Mockito.mock(TrackService.class);

        sut.setTrackService(mockedTrackService);
    }

    @Test
    public void testGetPlaylists() {
        var tracksToReturn = new Tracks(new ArrayList<>());
        Mockito.when(mockedTrackService.getTracksThatAreNotInPlaylist(1, "1234")).thenReturn(tracksToReturn);

        var testValue = sut.getTracks(1, "1234");

        Assertions.assertEquals(testValue.getEntity(), tracksToReturn);
    }
}
