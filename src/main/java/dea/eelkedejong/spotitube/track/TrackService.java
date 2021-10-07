package dea.eelkedejong.spotitube.track;

import dea.eelkedejong.spotitube.eigenaar.EigenaarService;
import dea.eelkedejong.spotitube.track.dao.TrackDao;
import dea.eelkedejong.spotitube.track.dto.Track;
import dea.eelkedejong.spotitube.track.dao.dto.TrackEntity;
import dea.eelkedejong.spotitube.track.dto.Tracks;
import dea.eelkedejong.spotitube.track.dao.dto.mapper.TrackEntityMapper;

import javax.inject.Inject;
import java.util.List;

public class TrackService {
    private TrackDao trackDao;
    private EigenaarService eigenaarService;
    private TrackEntityMapper trackEntityMapper;

    public Tracks getTracksThatAreNotInPlaylist(int forPlaylist, String token) {
        eigenaarService.checkToken(token);

        List<TrackEntity> trackEntities = trackDao.getTracksThatAreNotInPlaylist(forPlaylist);

        return new Tracks(
                trackEntityMapper.mapToTrack(trackEntities)
        );
    }

    public Tracks getTracksForPlaylist(int id, String token) {
        eigenaarService.checkToken(token);

        List<TrackEntity> trackEntities = trackDao.getTracksForPlaylist(id);

        return new Tracks(
                trackEntityMapper.mapToTrack(trackEntities)
        );
    }

    public Tracks addTrack(int id, String token, Track track) {
        eigenaarService.checkToken(token);

        trackDao.updateOfflineAvailable(id, track.isOfflineAvailable());
        trackDao.addTrackToPlaylist(track.getId(), id);

        return getTracksForPlaylist(id, token);
    }

    public Tracks deleteTrack(int playlistId, int trackId, String token) {
        eigenaarService.checkToken(token);

        trackDao.deleteTrack(trackId, playlistId);

        return getTracksForPlaylist(playlistId, token);
    }

    public void deleteTracksFromPlaylist(int playlistId) {
        trackDao.deleteTracksFromPlaylist(playlistId);
    }

    @Inject
    public void setTrackDao(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    @Inject
    public void setEigenaarService(EigenaarService eigenaarService) {
        this.eigenaarService = eigenaarService;
    }

    @Inject
    public void setTrackEntityMapper(TrackEntityMapper trackEntityMapper) {
        this.trackEntityMapper = trackEntityMapper;
    }
}
