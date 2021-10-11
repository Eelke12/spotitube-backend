package dea.eelkedejong.spotitube.track;

import dea.eelkedejong.spotitube.eigenaar.EigenaarService;
import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;
import dea.eelkedejong.spotitube.track.dao.TrackDao;
import dea.eelkedejong.spotitube.track.dao.TrackDaoImpl;
import dea.eelkedejong.spotitube.track.dao.dto.TrackEntity;
import dea.eelkedejong.spotitube.track.dao.dto.mapper.TrackEntityMapper;
import dea.eelkedejong.spotitube.track.dto.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TestTrackService {

    private TrackService sut;
    private TrackDao mockedTrackDao;
    private EigenaarService mockedEigenaarService;
    private TrackEntityMapper mockedTrackEntityMapper;

    @BeforeEach
    public void setup() {
        sut = new TrackService();
        mockedTrackDao = Mockito.mock(TrackDaoImpl.class);
        mockedEigenaarService = Mockito.mock(EigenaarService.class);
        mockedTrackEntityMapper = Mockito.mock(TrackEntityMapper.class);

        sut.setTrackDao(mockedTrackDao);
        sut.setEigenaarService(mockedEigenaarService);
        sut.setTrackEntityMapper(mockedTrackEntityMapper);
    }

    @Test
    public void testGetTracksThatAreNotInPlaylist() {
        Eigenaar eigenaar = new Eigenaar(
                1,
                "test",
                "test123",
                "1234"
        );

        List<TrackEntity> trackEntities = new ArrayList<>();

        Mockito.when(mockedEigenaarService.checkToken("1234")).thenReturn(eigenaar);
        Mockito.when(mockedTrackDao.getTracksThatAreNotInPlaylist(1)).thenReturn(trackEntities);

        var testValue = sut.getTracksThatAreNotInPlaylist(1, "1234");
        Assertions.assertEquals(testValue.getTracks(), trackEntities);
    }

    @Test
    public void testGetTracksForPlaylist() {
        Eigenaar eigenaar = new Eigenaar(
                1,
                "test",
                "test123",
                "1234"
        );

        List<TrackEntity> trackEntities = new ArrayList<>();

        Mockito.when(mockedEigenaarService.checkToken("1234")).thenReturn(eigenaar);
        Mockito.when(mockedTrackDao.getTracksForPlaylist(1)).thenReturn(trackEntities);

        var testValue = sut.getTracksForPlaylist(1, "1234");
        Assertions.assertEquals(testValue.getTracks(), trackEntities);
    }

    @Test
    public void testAddTrack() {
        Eigenaar eigenaar = new Eigenaar(
                1,
                "test",
                "test123",
                "1234"
        );

        Track track = new Track(
                1,
                "title",
                "performer",
                10,
                "album",
                1,
                "2017-01-10",
                "description",
                true
        );

        Mockito.when(mockedEigenaarService.checkToken("1234")).thenReturn(eigenaar);
        Mockito.doNothing().when(mockedTrackDao).updateOfflineAvailable(1, true);
        Mockito.doNothing().when(mockedTrackDao).addTrackToPlaylist(1, 1);

        sut.addTrack(1, "1234", track);

        Mockito.verify(mockedTrackDao).updateOfflineAvailable(1, true);
        Mockito.verify(mockedTrackDao).addTrackToPlaylist(1, 1);
    }

    @Test
    public void testDeleteTrack() {
        Eigenaar eigenaar = new Eigenaar(
                1,
                "test",
                "test123",
                "1234"
        );

        Mockito.when(mockedEigenaarService.checkToken("1234")).thenReturn(eigenaar);
        Mockito.doNothing().when(mockedTrackDao).deleteTrack(1, 1);

        sut.deleteTrack(1, 1, "1234");

        Mockito.verify(mockedTrackDao).deleteTrack(1, 1);
    }

    @Test
    public void testDeleteTracksFromPlaylist() {
        Mockito.doNothing().when(mockedTrackDao).deleteTracksFromPlaylist(1);

        sut.deleteTracksFromPlaylist(1);

        Mockito.verify(mockedTrackDao).deleteTracksFromPlaylist(1);
    }
}
