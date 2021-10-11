package dea.eelkedejong.spotitube.eigenaar.dao;

import dea.eelkedejong.spotitube.dbconnection.PostgresConnection;
import dea.eelkedejong.spotitube.eigenaar.dto.Eigenaar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EigenaarDaoImpl implements EigenaarDao {
    private final Connection conn;

    public EigenaarDaoImpl() {
        conn = PostgresConnection.getConnection();
    }

    @Override
    public void updateToken(String token, int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE eigenaar SET token = ? WHERE id = ?");

            ps.setString(1, token);
            ps.setInt(2, id);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Eigenaar getEigenaarByUsernameOrToken(String gebruikersnaamToken) {

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM eigenaar WHERE gebruikersnaam = ? OR token = ?");

            ps.setString(1, gebruikersnaamToken);
            ps.setString(2, gebruikersnaamToken);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Eigenaar(rs.getInt("id"),
                        rs.getString("gebruikersnaam"),
                        rs.getString("wachtwoord"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
