package dea.eelkedejong.spotitube.track.dao;

import dea.eelkedejong.spotitube.track.dao.dto.TrackEntity;

import java.util.List;

public interface TrackDao {
    List<TrackEntity> getTracksThatAreNotInPlaylist(int id);
    List<TrackEntity> getTracksForPlaylist(int id);
    void addTrackToPlaylist(int trackId, int playlistId);
    void deleteTrack(int trackId, int playlistId);
    void updateOfflineAvailable(int trackId, boolean isOfflineAvailable);
    void deleteTracksFromPlaylist(int playlistId);
}
