package dea.eelkedejong.spotitube.playlist;

import dea.eelkedejong.spotitube.playlist.dto.Playlist;
import dea.eelkedejong.spotitube.playlist.dto.Playlists;
import dea.eelkedejong.spotitube.track.dto.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestPlaylistResource {
    private PlaylistResource sut;
    private PlaylistService mockedPlaylistService;

    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
        mockedPlaylistService = Mockito.mock(PlaylistService.class);

        sut.setPlaylistService(mockedPlaylistService);
    }

    @Test
    public void testGetPlaylists() {
        var itemsToReturn = new Playlists(new ArrayList<Playlist>(), 0);
        Mockito.when(mockedPlaylistService.getPlaylists("1234")).thenReturn(itemsToReturn);

        var testValue = sut.getPlaylists("1234");

        Assertions.assertEquals(testValue.getEntity(), itemsToReturn);
    }

    @Test
    public void testAddPlaylist() {
        var itemsToReturn = new Playlists(new ArrayList<>(), 0);
        var itemToAdd = new Playlist(1, "test", true, new ArrayList<Track>());

        Mockito.when(mockedPlaylistService.addPlaylist(itemToAdd, "1234")).thenReturn(itemsToReturn);

        var testValue = sut.addPlaylist(itemToAdd, "1234");
        Playlists playlists = (Playlists) testValue.getEntity();
        Assertions.assertEquals(playlists.getPlaylists().size(), 0);
    }

    @Test
    public void testDeletePlaylist() {
        sut.deletePlaylist(1, "1234");

        Mockito.verify(mockedPlaylistService).deletePlaylist(1, "1234");
    }

    @Test
    public void testEditPlaylist() {
        var itemsToReturn = new Playlists(new ArrayList<>(), 0);
        var itemToEdit = new Playlist(1, "test", true, new ArrayList<Track>());

        Mockito.when(mockedPlaylistService.editPlaylist(1, itemToEdit, "1234")).thenReturn(itemsToReturn);

        var testValue = sut.editPlaylist(1, "1234", itemToEdit);

        Assertions.assertEquals(testValue.getEntity(), itemsToReturn);
    }
}
