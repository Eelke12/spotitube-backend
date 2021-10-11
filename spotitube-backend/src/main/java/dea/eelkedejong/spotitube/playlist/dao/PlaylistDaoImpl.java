package dea.eelkedejong.spotitube.playlist.dao;

import dea.eelkedejong.spotitube.dbconnection.PostgresConnection;
import dea.eelkedejong.spotitube.playlist.dao.dto.PlaylistEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDaoImpl implements PlaylistDao {
    private final Connection conn;

    public PlaylistDaoImpl() {
        conn = PostgresConnection.getConnection();
    }

    @Override
    public List<PlaylistEntity> getPlaylists() {

        ArrayList<PlaylistEntity> playlists = new ArrayList<>();

        try {
            ResultSet rs = conn.prepareStatement("SELECT * FROM playlist").executeQuery();

            while (rs.next()) {
                playlists.add(new PlaylistEntity(
                        rs.getInt("id"),
                        rs.getString("naam"),
                        rs.getInt("eigenaarid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public PlaylistEntity getPlaylistById(int id) {

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new PlaylistEntity(
                        rs.getInt("id"),
                        rs.getString("naam"),
                        rs.getInt("eigenaarid")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editPlaylist(int id, String naam) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE playlist SET naam = ? WHERE id = ?");

            ps.setString(1, naam);
            ps.setInt(2, id);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPlaylist(PlaylistEntity playlist) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist (naam, eigenaarid) VALUES (?, ?)");

            ps.setString(1, playlist.getNaam());
            ps.setInt(2, playlist.getEigenaarId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylistById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getDurationOfPlaylist(int id) {
        int duration = 0;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT duration FROM track where id IN " +
                    "(SELECT trackid FROM playlisttrack WHERE playlistid = ?)");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                duration += rs.getInt("duration");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duration;
    }
}
