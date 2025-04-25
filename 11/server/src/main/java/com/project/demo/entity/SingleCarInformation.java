package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 单车信息：(SingleCarInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SingleCarInformation")
public class SingleCarInformation implements Serializable {

    // SingleCarInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "single_car_information_id")
    private Integer single_car_information_id;

    // 管理序号
    @Basic
    private String management_serial_number;
    // 单车名称
    @Basic
    private String name_of_single_vehicle;
    // 单车类型
    @Basic
    private String single_vehicle_type;
    // 封面图
    @Basic
    private String cover_image;
    // 租用单价
    @Basic
    private Integer rental_unit_price;
    // 单车数量
    @Basic
    private Integer number_of_single_vehicles;
    // 租用标准
    @Basic
    private String rental_standards;
    // 车主用户
    @Basic
    private Integer owner_user;
    // 单车详情
    @Basic
    private String single_car_details;

    // 点击数
    @Basic
    private Integer hits;













    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
