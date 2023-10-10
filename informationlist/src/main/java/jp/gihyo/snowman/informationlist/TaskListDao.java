package jp.gihyo.snowman.informationlist;

import ch.qos.logback.core.model.Model;
import jp.gihyo.snowman.informationlist.HomeController.TaskItem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TaskListDao {
    private final JdbcTemplate  jdbcTemplate;

    @Autowired
    TaskListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public void add(TaskItem taskItem){
        SqlParameterSource parm = new BeanPropertySqlParameterSource(taskItem);
        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("tasklist");
        insert.execute(parm);
    }


    public List<TaskItem> findAll(){
        String query = "SELECT * FROM tasklist LEFT JOIN member ON tasklist.member_id = member.id  LEFT JOIN content ON tasklist.content_id = content.id  ORDER BY deadline ASC";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<TaskItem> taskItems = result.stream()
                .map((Map<String ,Object> row) -> new TaskItem(
                        row.get("id").toString(),
                        row.get("information").toString(),
                        row.get("task").toString(),
                        row.get("type").toString(),
                        row.get("name").toString(),
                        row.get("deadline").toString()))
                .toList();

        return taskItems;
    }
    public List<TaskItem> findMember(String id){
        String query = "SELECT * FROM  tasklist LEFT JOIN member ON tasklist.member_id = member.id LEFT JOIN content ON tasklist.content_id = content.id WHERE tasklist.member_id IN ('0','" + id  +"') ORDER BY deadline ASC";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<TaskItem> taskItems = result.stream()
                .map((Map<String ,Object> row) -> new TaskItem(
                        row.get("id").toString(),
                        row.get("information").toString(),
                        row.get("task").toString(),
                        row.get("type").toString(),
                        row.get("name").toString(),
                        row.get("deadline").toString()))
                .toList();

        return taskItems;
    }


    public int delete(String id){
        int number = jdbcTemplate.update("DELETE FROM tasklist WHERE id =?" ,id);
        return number;
    }

}