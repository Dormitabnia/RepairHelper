package com.ss.rh.dao;

import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.AuthenticationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthenticationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int countByExample(AuthenticationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int deleteByExample(AuthenticationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int insert(Authentication record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int insertSelective(Authentication record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    List<Authentication> selectByExample(AuthenticationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    Authentication selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int updateByExampleSelective(@Param("record") Authentication record, @Param("example") AuthenticationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int updateByExample(@Param("record") Authentication record, @Param("example") AuthenticationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int updateByPrimaryKeySelective(Authentication record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    int updateByPrimaryKey(Authentication record);
}