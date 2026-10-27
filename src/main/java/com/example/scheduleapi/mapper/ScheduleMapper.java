package com.example.scheduleapi.mapper;

import com.example.scheduleapi.dto.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Select("SELECT id,title,content,schedule,created_at FROM schedule ORDER BY created_at DESC")
    List<Schedule> findAll();

    @Insert("insert into schedule (title,content,schedule) values (#{title},#{content},#{schedule})")
    @Options(useGeneratedKeys = true,keyProperty = "id,createdAt",keyColumn = "id,created_at")
    int insert(Schedule schedule);

    @Select("SELECT id,title,content,schedule,created_at FROM schedule WHERE id = #{id}")
    Schedule findById(long id);

    @Delete("delete from schedule where id = #{id}")
    int delete(long id);

    @Update("update schedule set title = #{title},content = #{content},schedule = #{schedule} where id = #{id}")
    int update(Schedule schedule);
}
