package com.atsoy.data.repositories;

import com.atsoy.data.entities.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SongsRepository {

	private JdbcTemplate template;

	private static final String QUESTION =
            "SELECT * FROM song ORDER BY RAND() LIMIT 4;";

	@Autowired
	public SongsRepository(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

    public List<Song> findFourSongs(){
        return this.template.query(QUESTION, new Object[] {},new SongsRowMapper());
    }

    public static class SongsRowMapper implements RowMapper<Song> {

        public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
            Song song = new Song();
            song.setSource(rs.getString("source"));
            song.setId(rs.getLong("id"));
            song.setName(rs.getString("name"));
            song.setArtist(rs.getString("artist"));
            return song;
        }
    }

}
