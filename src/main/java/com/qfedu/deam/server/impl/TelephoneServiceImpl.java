package com.qfedu.deam.server.impl;

import com.qfedu.deam.dao.TelephoneDao;
import com.qfedu.deam.dao.impl.TelephoneDaoImpl;
import com.qfedu.deam.entity.TelephoneItem;
import com.qfedu.deam.server.TelephoneService;

import java.util.List;

/**
 * @author Eleven
 * @date 2022/3/27
 * @Function 通讯录系统工具类
 **/
public class TelephoneServiceImpl implements TelephoneService {
    static TelephoneDao telephoneDao = new TelephoneDaoImpl();

    /**
     * 用户名 密码验证
     * @param username 用户名
     * @param password 密码
     * @return true or false
     */
    public boolean logIn(String username,String password){
        return telephoneDao.logIn(username,password);
    }
    /**
     * 功能1:
     * 新增联系人(通讯录中的单条记录)记录方法
     */
    @Override
    public  boolean addItem(TelephoneItem item) {
        return telephoneDao.addItemToMySql(item) == 1;
    }
    /**
     * 功能2:
     * 通过联系人姓名删除联系人记录
     */
    @Override
    public  boolean deleteByName(String name) {
        return telephoneDao.deleteFromMysql(name) == 1;
    }

    /**
     * 功能3:
     * 通过联系人姓名修改联系人信息
     * 1.判断要求改的联系人是否存在 不存在则退出
     * 2.修改后得了联系人不能与其他联系人姓名重复(可以与修改前的本联系人同名)
     */
    @Override
    public  boolean updateByName(TelephoneItem item) {
        return telephoneDao.updateToMysql(item) == 1;
    }
    /**
     * 功能4:
     * 查询通讯录中所有联系人信息,并在控制台打印
     */
    @Override
    public List<TelephoneItem> selectAll() {
        List<TelephoneItem> list = telephoneDao.selectAllFromMySql();
        if(list.size() == 0) {
            System.out.println("暂无联系人");
        }
        return list;
    }
    /**
     * 功能5:
     * 从通讯录中查询指定的联系人数据 ;
     */
    @Override
    public TelephoneItem findByName(String name) {
        return telephoneDao.selectAllFromMySql(name);
    }
}
