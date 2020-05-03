package com.lyt.mapper;

import com.lyt.pojo.Haoyou;
import com.lyt.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HaoyouMapper {
    void add(@Param("LaheiP") int LaheiP, @Param("LaheiB") String LaheiB, @Param("LaheiN") int LaheiN);
    void dadd(@Param("LaheiP") int LaheiP, @Param("LaheiN") String LaheiN);
    List<Haoyou> findHaoyou(@Param("UserId") String UserId);
}
