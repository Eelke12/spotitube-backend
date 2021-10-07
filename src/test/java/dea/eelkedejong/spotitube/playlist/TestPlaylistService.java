package dea.eelkedejong.spotitube.playlist;

import dea.eelkedejong.spotitube.eigenaar.EigenaarService;
import dea.eelkedejong.spotitube.playlist.dao.PlaylistDao;
import dea.eelkedejong.spotitube.playlist.dao.PlaylistDaoImpl;
import dea.eelkedejong.spotitube.playlist.dao.dto.PlaylistEntity;
import dea.eelkedejong.spotitube.playlist.dao.dto.mapper.PlaylistEntityMapper;
import dea.eelkedejong.spotitube.playlist.dto.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestPlaylistService {

    private PlaylistService sut;
    private PlaylistDao mockedPlaylistDao;
    private EigenaarService mockedEigenaarService;
    private PlaylistEntityMapper mockedPlaylistEntityMapper;

    @BeforeEach
    public void setup() {
        sut = new PlaylistService();

        mockedPlaylistDao = Mockito.mock(PlaylistDaoImpl.class);
        mockedEigenaarService = Mockito.mock(EigenaarService.class);
        mockedPlaylistEntityMapper = Mockito.mock(PlaylistEntityMapper.class);

        sut.setPlaylistDao(mockedPlaylistDao);
        sut.setEigenaarService(mockedEigenaarService);
        sut.setPlaylistEntityMapper(mockedPlaylistEntityMapper);
    }

    @Test
    public void testGetPlaylists() {
        var itemsToReturn = new ArrayList<Playlist>();

        var testValue = sut.getPlaylists("1234");

        Mockito.verify(mockedEigenaarService).checkToken("1234");
        Assertions.assertEquals(testValue.getPlaylists(), itemsToReturn);
    }

    @Test
    public void testAddPlaylist() {
        var itemToAdd = new Playlist(
                1,
                "test",
                true,
                new ArrayList<>()
        );

        var itemToAddEntity = new PlaylistEntity(
                1,
                "test",
                1
        );
    }
//
//    @Test
//    public void testEditPlaylist() {
//        var itemToEdit = new Playlist(1, "test", false, new ArrayList<>());
//
//        Eigenaar eigenaar = new Eigenaar(1, "test", "test123", "1234");
//        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("1234")).thenReturn(eigenaar);
//
//        Mockito.when(mockedPlaylistDao.getPlaylistById(1)).thenReturn(new PlaylistEntity(1, "test", 2));
//        Assertions.assertThrows(NotOwnerOfPlaylistException.class, () -> sut.editPlaylist(1, itemToEdit, "1234"));
//    }
//
//    @Test
//    public void testEditPlaylistNotOwner() {
//        var itemToEdit = new Playlist(1, "test", false, new ArrayList<>());
//
//        Eigenaar eigenaar = new Eigenaar(1, "test", "test123", "1234");
//        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("1234")).thenReturn(eigenaar);
//
//        Mockito.when(mockedPlaylistDao.getPlaylistById(1)).thenReturn(new PlaylistEntity(1, "test", 2));
//        Assertions.assertThrows(NotOwnerOfPlaylistException.class, () -> sut.editPlaylist(1, itemToEdit, "1234"));
//    }
//
//    @Test
//    public void testEditPlaylistWithNoId() {
//        var itemToEdit = new Playlist(1, "test", true, new ArrayList<>());
//
//        Eigenaar eigenaar = new Eigenaar(1, "test", "test123", "1234");
//        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("1234")).thenReturn(eigenaar);
//
//        Mockito.when(mockedPlaylistDao.getPlaylistById(2)).thenReturn(new PlaylistEntity(1, "test", 1));
//
//        Assertions.assertThrows(NoPlaylistWithThatIdException.class, () -> sut.editPlaylist(1, itemToEdit, "1234"));
//    }
//
//    @Test
//    public void testDeletePlaylist() {
//        Eigenaar eigenaar = new Eigenaar(1, "test", "test123", "1234");
//        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("1234")).thenReturn(eigenaar);
//
//        Mockito.when(mockedPlaylistDao.getPlaylistById(1)).thenReturn(new PlaylistEntity(1, "test", 1));
//
//        sut.deletePlaylist(1, "1234");
//    }
//
//    @Test
//    public void testDeletePlaylistNotOwner() {
//        Eigenaar eigenaar = new Eigenaar(1, "test", "test123", "1234");
//        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("1234")).thenReturn(eigenaar);
//
//        Mockito.when(mockedPlaylistDao.getPlaylistById(1)).thenReturn(new PlaylistEntity(1, "test", 2));
//        Assertions.assertThrows(NotOwnerOfPlaylistException.class, () -> sut.deletePlaylist(1, "1234"));
//    }
//
//    @Test
//    public void testDeletePlaylistNoId() {
//        Eigenaar eigenaar = new Eigenaar(1, "test", "test123", "1234");
//        Mockito.when(mockedEigenaarDao.getEigenaarByUsernameOrToken("1234")).thenReturn(eigenaar);
//
//        Assertions.assertThrows(NoPlaylistWithThatIdException.class, () -> sut.deletePlaylist(1, "1234"));
//    }
}
