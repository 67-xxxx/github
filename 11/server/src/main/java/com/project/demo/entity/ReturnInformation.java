package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 归还信息：(ReturnInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReturnInformation")
public class ReturnInformation implements Serializable {

    // ReturnInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_information_id")
    private Integer return_information_id;

    // 租用号
    @Basic
    private String lease_number;
    // 管理序号
    @Basic
    private String management_serial_number;
    // 单车名称
    @Basic
    private String name_of_single_vehicle;
    // 单车类型
    @Basic
    private String single_vehicle_type;
    // 租用单价
    @Basic
    private Integer rental_unit_price;
    // 租用标准
    @Basic
    private String rental_standards;
    // 车主用户
    @Basic
    private Integer owner_user;
    // 租车用户
    @Basic
    private Integer car_rental_users;
    // 租用数量
    @Basic
    private Integer number_of_leases;
    // 租用日期
    @Basic
    private Timestamp lease_date;
    // 归还日期
    @Basic
    private Timestamp return_date;
    // 出借时间
    @Basic
    private Integer lending_time;
    // 支付金额
    @Basic
    private String payment_amount;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
