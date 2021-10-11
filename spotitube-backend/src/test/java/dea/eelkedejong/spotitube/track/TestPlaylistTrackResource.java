package dea.eelkedejong.spotitube.track;

import dea.eelkedejong.spotitube.track.dto.Track;
import dea.eelkedejong.spotitube.track.dto.Tracks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestPlaylistTrackResource {

    private PlaylistTrackResource sut;
    private TrackService mockedTrackService;

    @BeforeEach
    public void setup() {
        sut = new PlaylistTrackResource();
        mockedTrackService = Mockito.mock(TrackService.class);

        sut.setTrackService(mockedTrackService);
    }

    @Test
    public void testGetTracksForPlaylist() {
        Tracks tracksToReturn = new Tracks(new ArrayList<>());
        Mockito.when(mockedTrackService.getTracksForPlaylist(1, "1234")).thenReturn(tracksToReturn);

        var testValue = sut.getTracksOfPlaylist(1, "1234");

        Assertions.assertEquals(testValue.getEntity(), tracksToReturn);
    }

    @Test
    public void testDeleteTrackFromPlaylist() {
        sut.deleteTrackOfPlaylist(1, 1, "1234");

        Mockito.verify(mockedTrackService).deleteTrack(1, 1, "1234");
    }

    @Test
    public void testAddTrackToPlaylist() {
        Track track = new Track();

        sut.addTrackToPlaylist(1, "1234", track);

        Mockito.verify(mockedTrackService).addTrack(1, "1234", track);
    }
}
