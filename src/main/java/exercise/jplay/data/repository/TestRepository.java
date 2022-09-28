package exercise.jplay.data.repository;

import exercise.jplay.data.entity.AudioTrack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRepository {
    Connection conn;

    public void save(AudioTrack track) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO TRACK (title, author, album, duration_sec, RELEASE_DATE, COMMENT, FILE_PATH)" +
                                                                    " VALUES (?, ?, ?, ?, ?, ?, ?);");
        statement.setString(1, track.getTitle());
        statement.setString(2, track.getAuthor());
        statement.setString(3, track.getAlbum());
        statement.setInt(4, track.getDurationSeconds());
        statement.setDate(5, track.getReleaseDate() == null ? null : new java.sql.Date(track.getReleaseDate().getTime()));
        statement.setString(6, track.getComment());
        statement.setString(7, track.getFilePath());

        statement.executeUpdate();

    }

    public TestRepository() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/./demo", "sa", "");
    }

    public List<String> allSongs() throws SQLException {
        List<String> titles = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement("SELECT FILE_PATH FROM TRACK;");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            titles.add(rs.getString(1));
        }
        titles.forEach(System.out::println);
        return titles;
    }

}
