package dea.eelkedejong.spotitube.playlist;

import dea.eelkedejong.spotitube.eigenaar.EigenaarService;
import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;
import dea.eelkedejong.spotitube.playlist.dto.Playlist;
import dea.eelkedejong.spotitube.playlist.dao.dto.PlaylistEntity;
import dea.eelkedejong.spotitube.playlist.dto.Playlists;
import dea.eelkedejong.spotitube.playlist.dao.dto.mapper.PlaylistEntityMapper;
import dea.eelkedejong.spotitube.playlist.exceptions.NoPlaylistWithThatIdException;
import dea.eelkedejong.spotitube.playlist.dao.PlaylistDao;
import dea.eelkedejong.spotitube.playlist.exceptions.NotOwnerOfPlaylistException;
import dea.eelkedejong.spotitube.track.TrackService;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService {
    private PlaylistDao playlistDao;
    private EigenaarService eigenaarService;
    private TrackService trackService;
    private PlaylistEntityMapper playlistEntityMapper;

    public Playlists getPlaylists(String token) {
        Eigenaar eigenaar = eigenaarService.checkToken(token);
        List<PlaylistEntity> playlistEntities = playlistDao.getPlaylists();

        int length = playlistEntities.stream().mapToInt(playlistEntity ->
                playlistDao.getDurationOfPlaylist(playlistEntity.getId())).sum();

        return new Playlists(
                playlistEntityMapper.mapToPlaylist(playlistEntities, eigenaar),
                length
        );
    }

    public Playlists deletePlaylist(int id, String token) {
        checkTokenAndOwner(id, token);

        trackService.deleteTracksFromPlaylist(id);
        playlistDao.deletePlaylistById(id);
        return getPlaylists(token);
    }

    public Playlists addPlaylist(Playlist playlist, String token) {
        Eigenaar eigenaar = eigenaarService.checkToken(token);

        PlaylistEntity playlistEntity = new PlaylistEntity(
                playlist.getId(),
                playlist.getNaam(),
                eigenaar.getId()
        );

        playlistDao.addPlaylist(playlistEntity);
        return getPlaylists(token);
    }

    public Playlists editPlaylist(int id, Playlist playlist, String token) {
        checkTokenAndOwner(id, token);

        playlistDao.editPlaylist(id, playlist.getNaam());

        return getPlaylists(token);
    }

    private void checkTokenAndOwner(int id, String token) {
        Eigenaar eigenaar = eigenaarService.checkToken(token);
        PlaylistEntity playlistEntity = playlistDao.getPlaylistById(id);

        if (playlistEntity == null) {
            throw new NoPlaylistWithThatIdException("there is no playlist with that id");
        }

        if (eigenaar.getId() != playlistEntity.getEigenaarId()) {
            throw new NotOwnerOfPlaylistException("your not the owner of the playlist");
        }
    }

    @Inject
    public void setPlaylistDao(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    @Inject
    public void setEigenaarService(EigenaarService eigenaarService) {
        this.eigenaarService = eigenaarService;
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @Inject
    public void setPlaylistEntityMapper(PlaylistEntityMapper playlistEntityMapper) {
        this.playlistEntityMapper = playlistEntityMapper;
    }
}
