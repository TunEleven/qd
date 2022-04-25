package com.qfedu.deam.dao;

import com.qfedu.deam.entity.TelephoneItem;

import java.util.List;
/**
 * @author Eleven
 * @date 2022/3/27
 * @Function 代表单条通讯录中的数据, 以name作为唯一键
 **/
public interface TelephoneDao {
    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return true or false
     */
    public boolean logIn(String username ,String password);
    /**
     * 增加联系人方法
     * @param item 要新增的联系人信息
     * @return 返回添加的数据条数
     */
    int addItemToMySql(TelephoneItem item);

    /**
     * 删除联系人方法
     * @param name 要删除的联系人姓名
     * @return 返回删除的数据条数
     */
    int deleteFromMysql(String name);

    /**
     * 查询所有联系人
     * @return 查询到的联系人数据集合
     */
    List<TelephoneItem> selectAllFromMySql();

    /**
     * 修改联系人
     * @param item 修改后的联系人数据
     * @return 修改的数据条数
     */
    int updateToMysql(TelephoneItem item);

    /**
     * 按照条件查询
     * @param name 查询条件 - 联系人姓名
     * @return 查询到的联系人信息 未知联系人 返回null
     */
    TelephoneItem selectAllFromMySql(String name);

}
