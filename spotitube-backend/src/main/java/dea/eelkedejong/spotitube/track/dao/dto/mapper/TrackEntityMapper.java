package dea.eelkedejong.spotitube.track.dao.dto.mapper;

import dea.eelkedejong.spotitube.track.dao.dto.TrackEntity;
import dea.eelkedejong.spotitube.track.dto.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackEntityMapper {

    public List<Track> mapToTrack(List<TrackEntity> trackEntities) {
        List<Track> tracks = new ArrayList<>();

        trackEntities.stream().forEach(trackEntity -> {
            boolean offlineAvailable = false;

            if (trackEntity.getOfflineAvailable() == 1) {
                offlineAvailable = true;
            }

            tracks.add(new Track(
                    trackEntity.getId(),
                    trackEntity.getTitle(),
                    trackEntity.getPerformer(),
                    trackEntity.getDuration(),
                    trackEntity.getAlbum(),
                    trackEntity.getPlaycount(),
                    trackEntity.getPublicationDate().toString(),
                    trackEntity.getDescription(),
                    offlineAvailable
            ));
        });

        return tracks;
    }
}
