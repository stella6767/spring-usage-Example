package com.example.springapply.repository.board;

import com.example.springapply.domain.board.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateBoardRepositoryImpl implements BoardRepository {

    private final JdbcTemplate jdbcTemplate; //jdbcTemplate은 인젝션으로 받을 수 없다.

    // 생성자가 하나면 @Autowired 생략가능
    public JdbcTemplateBoardRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Board board) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("id");

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("title", board.getTitle());
        parameters.put("content", board.getContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return (int) key;
    }

    @Override
    public List<Board> findAll() {
        List<Board> results = jdbcTemplate.query("select * from board ", boardRowMapper());
        return results;
    }

    @Override
    public Optional<Board> findById(int id) {
        String sql = "select * from board where id = ?";
        List<Board> result = jdbcTemplate.query(sql, boardRowMapper(), id);

        return result.stream().findAny();
    }


    private RowMapper<Board> boardRowMapper(){
        return (rs, rowNum) -> {

            Board board = new Board();
            board.setId(rs.getInt("id"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));

            return board;
        };
    }

}
