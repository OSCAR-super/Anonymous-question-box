package com.lyt.mapper;

import com.lyt.pojo.Lahei;
import com.lyt.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LaheiMapper {
    void lahei(@Param("LaheiP") int LaheiP, @Param("LaheiB") String LaheiB, @Param("LaheiN") String LaheiN);
    void qlahei(@Param("LaheiP") int LaheiP, @Param("LaheiN") String LaheiN);
    List<Lahei> findLahei(@Param("UserId") String UserId);
}
