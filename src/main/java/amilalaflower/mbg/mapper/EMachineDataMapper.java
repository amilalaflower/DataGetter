package amilalaflower.mbg.mapper;

import amilalaflower.mbg.entity.EMachineDataExample;
import amilalaflower.mbg.entity.EMachineDataKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EMachineDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int countByExample(EMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int deleteByExample(EMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int deleteByPrimaryKey(EMachineDataKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int insert(EMachineDataKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int insertSelective(EMachineDataKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    List<EMachineDataKey> selectByExample(EMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int updateByExampleSelective(@Param("record") EMachineDataKey record, @Param("example") EMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table E_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:42:21 JST 2016
     */
    int updateByExample(@Param("record") EMachineDataKey record, @Param("example") EMachineDataExample example);
}