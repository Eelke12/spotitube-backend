package dea.eelkedejong.spotitube.playlist.dao.dto.mapper;

import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;
import dea.eelkedejong.spotitube.playlist.dto.Playlist;
import dea.eelkedejong.spotitube.playlist.dao.dto.PlaylistEntity;
import dea.eelkedejong.spotitube.track.dto.Track;

import java.util.ArrayList;
import java.util.List;

public class PlaylistEntityMapper {

    public List<Playlist> mapToPlaylist(List<PlaylistEntity> playlistEntity, Eigenaar eigenaar) {
        List<Playlist> playlists = new ArrayList<>();

        playlistEntity.stream().forEach(playlist -> {
            boolean owner;

            if (eigenaar.getId() == playlist.getEigenaarId()) {
                owner = true;
            } else {
                owner = false;
            }

            playlists.add(new Playlist(
                    playlist.getId(),
                    playlist.getNaam(),
                    owner,
                    new ArrayList<Track>()
            ));
        });

        return playlists;
    }
}
