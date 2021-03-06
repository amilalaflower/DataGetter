package amilalaflower.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import amilalaflower.mbg.entity.WMachineData;
import amilalaflower.mbg.entity.WMachineDataExample;
import amilalaflower.mbg.entity.WMachineDataKey;

public interface WMachineDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int countByExample(WMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int deleteByExample(WMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int deleteByPrimaryKey(WMachineDataKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int insert(WMachineData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int insertSelective(WMachineData record);

    int insertWorkData(WMachineData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    List<WMachineData> selectByExample(WMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    WMachineData selectByPrimaryKey(WMachineDataKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int updateByExampleSelective(@Param("record") WMachineData record, @Param("example") WMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int updateByExample(@Param("record") WMachineData record, @Param("example") WMachineDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int updateByPrimaryKeySelective(WMachineData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.W_MACHINE_DATA
     *
     * @mbggenerated Sun Nov 13 19:35:58 JST 2016
     */
    int updateByPrimaryKey(WMachineData record);
}