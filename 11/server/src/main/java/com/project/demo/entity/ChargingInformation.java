package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 充电信息：(ChargingInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ChargingInformation")
public class ChargingInformation implements Serializable {

    // ChargingInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charging_information_id")
    private Integer charging_information_id;

    // 电桩名称
    @Basic
    private String name_of_electric_pile;
    // 电桩封面
    @Basic
    private String electric_pile_cover;
    // 收入电压
    @Basic
    private String revenue_voltage;
    // 收入电流
    @Basic
    private String revenue_current;
    // 电桩数量
    @Basic
    private Integer number_of_electric_piles;
    // 充电单价
    @Basic
    private Integer charging_unit_price;
    // 收费标准
    @Basic
    private String charging_standard;
    // 充电地址
    @Basic
    private String charging_address;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
