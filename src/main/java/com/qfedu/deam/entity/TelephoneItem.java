package com.qfedu.deam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eleven
 * @date 2022/3/27
 * @Function 代表单条通讯录中的数据, 以name作为唯一键
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneItem {
    private int cid;
    private String cname;
    private String cgender;
    private int cage;
    private String ctel;
    private String cqq;
    private String address;

    public TelephoneItem(String name) {
        this.cname = name;
    }

}
