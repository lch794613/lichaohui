package lch.dy.controller.mapper;

import lch.dy.controller.Entity.commit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface commitMapper {

    @Insert("insert into commit value(null,#{ip},#{commit},now())")
    public void insertCommit(@Param("ip")String ip,@Param("commit")String commit);

    @Select("select * from commit where id>#{start} and id<#{end}")
    public List<commit> queryById(@Param("start")String start,@Param("end") String end);

    @Select("select * from commit order by id desc limit #{index},9")
    public List<commit> queryByIndex(@Param("index")int index);
}
