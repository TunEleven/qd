package com.qfedu.deam.dao.impl;

import com.qfedu.deam.dao.TelephoneDao;
import com.qfedu.deam.entity.TelephoneItem;
import com.qfedu.deam.util.ElevenJdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TelephoneDao
 * @Description TODO 增删改查 联系人信息
 * @Author ELeven
 * @Date 2022/4/21 19:20
 * @Version 1.0
 **/
public class TelephoneDaoImpl implements TelephoneDao {

    /**
     * 用户名 密码验证
     * @param username 用户名
     * @param password 密码
     * @return true or false
     */
    @Override
    public boolean logIn(String username ,String password){
        Object[] params = {username,password};
        String sql = "SELECT * FROM telephonebook.user WHERE username = ? AND password = ?";

        QueryRunner qr = new QueryRunner(ElevenJdbcUtils.getDataSource());
        Map<String, Object> user= null;
        try {
            user = qr.query(sql, new MapHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user != null;
    }

    /**
     * 向数据库中写入联系人信息
     * @param item 要写入的信息
     * @return true or false
     */
    @Override
    public  int addItemToMySql(TelephoneItem item) {
        String sql = "INSERT INTO telephonebook.contact(cname, cgender, cage, ctel, cqq, address) VALUE (?,?,?,?,?,?)";
        Object[] params = {item.getCname(),item.getCgender(),item.getCage(),item.getCtel(),item.getCqq(),item.getAddress()};
        QueryRunner qr = new QueryRunner(ElevenJdbcUtils.getDataSource());
        int result = 0;
        try {
            result = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从数据库删除联系人
     * @param name 要删除的联系人
     * @return true or false
     */
    @Override
    public  int deleteFromMysql(String name){
        String sql = "DELETE FROM telephonebook.contact WHERE cname = ?";
        Object[] params = {name};
        QueryRunner qr = new QueryRunner(ElevenJdbcUtils.getDataSource());
        int result = 0;
        try {
            result = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改MySql中联系人信息
     * @param item 修改后的数据
     */
    @Override
    public  int updateToMysql(TelephoneItem item){
        String sql = "UPDATE telephonebook.contact SET cname = ? ,cage = ? , cgender = ? ,ctel = ?, cqq = ? ,address = ? WHERE cid = ?";
        Object[] params = {item.getCname(),item.getCage(),item.getCgender(),item.getCtel(),item.getCqq(),item.getAddress(),item.getCid() };
        QueryRunner qr = new QueryRunner(ElevenJdbcUtils.getDataSource());
        int result = 0;
        try {
            result = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据条件查询
     * @param name 查询的条件是通讯录资料中的人员姓名 -> cname
     * @return 查询到的数据
     */
    @Override
    public   TelephoneItem selectAllFromMySql(String name){
        TelephoneItem item = null;
        String sql = "SELECT * FROM telephonebook.contact WHERE cname = ?";
        Object[] params = {name};
        QueryRunner qr = new QueryRunner(ElevenJdbcUtils.getDataSource());
        try {
            item = qr.query(sql, new BeanHandler<>(TelephoneItem.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    /**
     * 查询返回所有联系人信息
     * @return 包括有所有联系人信息的集合
     */
    @Override
    public  List<TelephoneItem> selectAllFromMySql(){
        List<TelephoneItem> list = null;
        String sql = "SELECT * FROM telephonebook.contact";
        QueryRunner qr = new QueryRunner(ElevenJdbcUtils.getDataSource());
        int result = 0;
        try {
            list = qr.query(sql, new BeanListHandler<>(TelephoneItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
