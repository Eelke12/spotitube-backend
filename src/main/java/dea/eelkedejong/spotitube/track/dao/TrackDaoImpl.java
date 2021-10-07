package dea.eelkedejong.spotitube.track.dao;

import dea.eelkedejong.spotitube.dbconnection.PostgresConnection;
import dea.eelkedejong.spotitube.track.dao.dto.TrackEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDaoImpl implements TrackDao {

    private Connection conn;

    public TrackDaoImpl() {
        conn = PostgresConnection.getConnection();
    }

    @Override
    public List<TrackEntity> getTracksThatAreNotInPlaylist(int id) {
        String sql = "SELECT * FROM track WHERE id NOT IN " +
                    "(SELECT trackid FROM playlisttrack WHERE playlistid = ?)";

        return getTracksFromQuery(sql, id);
    }

    @Override
    public List<TrackEntity> getTracksForPlaylist(int id) {
        String sql = "SELECT * FROM track WHERE id IN " +
                    "(SELECT trackid FROM playlisttrack WHERE playlistid = ?)";

        return getTracksFromQuery(sql, id);
    }

    @Override
    public void addTrackToPlaylist(int trackId, int playlistId) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlisttrack(playlistid, trackid) VALUES (?, ?)");
            ps.setInt(1, playlistId);
            ps.setInt(2, trackId);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrack(int trackId, int playlistId) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM playlisttrack WHERE playlistid = ? AND trackid = ?");

            ps.setInt(1, playlistId);
            ps.setInt(2, trackId);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOfflineAvailable(int trackId, boolean isOfflineAvailable) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE track SET offlineavailable = ?::bit WHERE id = ?");

            int offlineAvailable = isOfflineAvailable ? 1 : 0;

            ps.setInt(1, offlineAvailable);
            ps.setInt(2, trackId);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTracksFromPlaylist(int playlistId) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM playlisttrack WHERE playlistid = ?");

            ps.setInt(1, playlistId);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<TrackEntity> getTracksFromQuery(String sql, int id) {
        List<TrackEntity> tracks = new ArrayList<>();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tracks.add(new TrackEntity(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("performer"),
                        rs.getInt("duration"),
                        rs.getString("album"),
                        rs.getInt("playcount"),
                        rs.getDate("publicationdate"),
                        rs.getString("description"),
                        rs.getInt("offlineAvailable")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }
}
