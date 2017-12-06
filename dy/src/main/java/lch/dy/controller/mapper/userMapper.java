package lch.dy.controller.mapper;

import lch.dy.controller.Entity.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {
    @Select("select * from user where name=#{name}")
    user getUserByName(@Param("name")String name);

    @Insert("insert into user values (null,#{name},#{password},'ROLE_USER',null)")
    void register(@Param("name")String name,@Param("password")String password);


}
