package dea.eelkedejong.spotitube.playlist.dao;

import dea.eelkedejong.spotitube.playlist.dao.dto.PlaylistEntity;

import java.util.List;

public interface PlaylistDao {
    List<PlaylistEntity> getPlaylists();
    PlaylistEntity getPlaylistById(int id);
    void editPlaylist(int id, String naam);
    void addPlaylist(PlaylistEntity playlist);
    void deletePlaylistById(int id);
    int getDurationOfPlaylist(int id);
}
