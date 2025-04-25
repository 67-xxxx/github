package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 停车区域：(ParkingAreaSharing)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ParkingAreaSharing")
public class ParkingAreaSharing implements Serializable {

    // ParkingAreaSharing编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_area_sharing_id")
    private Integer parking_area_sharing_id;

    // 区域名称
    @Basic
    private String area_name;
    // 封面
    @Basic
    private String cover;
    // 场地面积
    @Basic
    private String site_area;
    // 容纳车辆数
    @Basic
    private Integer number_of_vehicles_accommodated;
    // 区域地址
    @Basic
    private String regional_address;
    // 详情介绍
    @Basic
    private String details;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
