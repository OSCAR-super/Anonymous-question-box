package com.lyt.mapper;


import com.lyt.pojo.Hueifu;
import com.lyt.pojo.PinLuen;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface PinLuenMapper {
    List<PinLuen> findAllPinLuen();
    List<PinLuen>findPinLuenByUserId(@Param("UserId") int UserId);
    void addPinLuenByBAndP(@Param("UserB") int UserB, @Param("neiron") String neiron, @Param("UserIdP") int UserIdP);

    void addH(@Param("IdP") int idP, @Param("HN") String HN, @Param("UserId") String UserId);

    List<Hueifu> findH(@Param("IdP") int idP);

    PinLuen findPinLuenById(@Param("IdP") int idP);

    void dP(@Param("IdP") int idP);

    void set(@Param("IdP") int idP);

    List<Hueifu> findHR(@Param("IdP") int userId);

    List<PinLuen> findPR(@Param("IdP") int userId);
}
