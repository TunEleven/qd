package com.qfedu.deam.server;

import com.qfedu.deam.entity.TelephoneItem;

import java.util.List;

public interface TelephoneService {
    /**
     * 登录
     */
    public boolean logIn(String username,String password);
    /**
     * 功能1 新增联系人(通讯录中的单条记录)记录方法
     */
    boolean addItem(TelephoneItem item);
    /**
     * 功能2:
     * 通过联系人姓名删除联系人记录
     */
    boolean deleteByName(String name);
    /**
     * 功能3:
     * 通过联系人姓名修改联系人信息
     * 1.判断要求改的联系人是否存在 不存在则退出
     * 2.修改后得了联系人不能与其他联系人姓名重复(可以与修改前的本联系人同名)
     */
    boolean updateByName(TelephoneItem item);
    /**
     * 功能4:
     * 查询通讯录中所有联系人信息,并在控制台打印
     */
    List<TelephoneItem> selectAll();
    /**
     * 功能5:
     * 从通讯录中查询指定的联系人数据 ;
     */
    TelephoneItem findByName(String name);
}
