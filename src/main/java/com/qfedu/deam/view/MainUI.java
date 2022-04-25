package com.qfedu.deam.view;

import com.qfedu.deam.entity.TelephoneItem;
import com.qfedu.deam.server.TelephoneService;
import com.qfedu.deam.server.impl.TelephoneServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * @ClassName MainUI
 * @Description TODO
 * @Author ELeven
 * @Date 2022/4/22 14:44
 * @Version 1.0
 **/
public class MainUI {
    private static final TelephoneService telephoneService = new TelephoneServiceImpl();
    public static void main(String[] args) {
        if(login()) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                help();
                switch (sc.next()) {
                    case "0":
                        return;
                    case "1":
                        addItem();
                        break;
                    case "2":
                        deleteItem();
                        break;
                    case "3":
                        updateItem();
                        break;
                    case "4":
                        selectAllItems();
                        break;
                    case "5":
                        findByName();
                        break;
                    default:
                        System.out.println("没有该功能");
                }
            }
        }
    }

    /**
     * 登录模块,用户输入密码 正确则进入通讯录管理系统,失败三次则退出
     *
     * @return 是否登陆成功true or false
     */
    public static boolean login() {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        while (true) {
            System.out.print("请输入用户名:");
            String username = sc.next();
            System.out.print("请输入密码:");
            String password = sc.next();
            //判断输入的密码是否正确
            boolean result = telephoneService.logIn(username,password);
            if(result) {
                System.out.println("登陆成功");
                return true;
            } else {
                counter++;
                if(counter==3){
                    System.out.println("失败次数过多,程序退出");
                    return false;
                }
                System.out.println("用户名密码错误,请重新输入.");
            }
        }
    }
    /**
     * 用以输出帮助菜单,包括操作提示等
     */
    public static void help() {
        System.out.println("---------------------------通讯录管理系统-----------------------------");
        System.out.println("1.添加\t2.删除\t3.修改\t4.查询所有\t5.根据姓名查询\t0.退出");
        System.out.println("---------------------------通讯录管理系统-----------------------------");
        System.out.print("请选择业务:");
    }

    /**
     * 功能1:
     * 新增联系人(通讯录中的单条记录)记录方法
     */
    public static void addItem() {
        System.out.println("---------添加通讯录---------");
        System.out.print("姓名:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        //判断改 姓名 name在 通讯录 book.item[] 中是否已经存在
        TelephoneItem item = telephoneService.findByName(name);
        if ( item!=null ) {
            System.out.println("用户已经存在");

        } else {
            item = getNewItem(name);
            System.out.println(item);
            //向book.items中添加item 成功添加返回true 失败则返回false
            boolean addResult = telephoneService.addItem(item);
            if (addResult) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        }
    }
    /**
     * 功能2:
     * 通过联系人姓名删除联系人记录
     */
    public static void deleteItem() {
        System.out.println("--------删除通讯录--------");
        System.out.print("请输入要删除的姓名:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        TelephoneItem item  = telephoneService.findByName(name);
        if (item != null) {
            System.out.println(item);
            while (true) {
                System.out.print("是否删除联系人信息:   y-确定  n-取消:");
                String choose = sc.next();
                if ("y".equalsIgnoreCase(choose)) {
                    boolean deleteResult = telephoneService.deleteByName(name);
                    if(deleteResult) {
                        System.out.println();
                    }
                    break;
                } else if ("n".equalsIgnoreCase(choose)) {
                    System.out.println("取消删除");
                    break;
                } else {
                    System.out.println("选择错误,请重新输入");
                }
            }
        } else {
            System.out.println("查无此人");
        }
    }
    /**
     * 功能3:
     * 通过联系人姓名修改联系人信息
     * 1.判断要求改的联系人是否存在 不存在则退出
     * 2.修改后得了联系人不能与其他联系人姓名重复(可以与修改前的本联系人同名)
     */
    public static void updateItem() {
        System.out.println("--------修改通讯录--------");
        System.out.print("请输入要修改的姓名:");
        Scanner sc = new Scanner(System.in);
        String oldName = sc.next();
        //判断该名字的联系人是否存在
        TelephoneItem item = telephoneService.findByName(oldName);


        if (item != null) {
            System.out.println("请重新输入信息");
            System.out.print("姓名:");
            String newName = sc.next();
            TelephoneItem newItem = telephoneService.findByName(newName);
            if (newItem != null && !newName.equals(oldName)) {
                System.out.println("要修改的姓名与通讯录中其他项姓名重复,不能修改");
            } else {
                newItem = getNewItem(newName);
                newItem.setCid(item.getCid());
                telephoneService.updateByName(newItem);
                System.out.println("修改成功");
            }
        } else {
            System.out.println("查无此人");
        }

    }
    /**
     * 功能4:
     * 查询通讯录中所有联系人信息,并在控制台打印
     */
    public static void selectAllItems(){
        List<TelephoneItem> list = telephoneService.selectAll();
        list.forEach(System.out::println);
    }

    /**
     * 功能5:
     * 从通讯录中查询指定的联系人数据 ;
     */
    public static void findByName() {
        System.out.println("---------根据姓名查找---------");
        System.out.print("姓名:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();

        TelephoneItem item = telephoneService.findByName(name);
        if (item == null) {
            System.out.println("查无此人");

        } else {
            System.out.println(item);
        }
    }
    /**
     * 新建一个联系人TelephoneItem对象,并由用户进行赋值,可以用功能1 和 功能3
     *
     * @param name 经过过滤后可以使用的唯一键值name作为item.name
     * @return 新建的联系人TelephoneItem对象
     */
    public static TelephoneItem getNewItem(String name) {
        TelephoneItem item = new TelephoneItem(name);
        Scanner sc = new Scanner(System.in);
        System.out.print("性别:");
        item.setCgender(sc.next());
        System.out.print("年龄:");
        item.setCage(sc.nextInt());
        System.out.print("电话:");
        item.setCtel(sc.next());
        while(item.getCtel().length() != 11){
            System.out.print("号码输入错误:请正确数据11位手机号码:");
            item.setCtel(sc.next());
        }
        System.out.print("QQ:");
        item.setCqq(sc.next());
        System.out.print("地址:");
        item.setAddress(sc.next());
        return item;
    }
}
