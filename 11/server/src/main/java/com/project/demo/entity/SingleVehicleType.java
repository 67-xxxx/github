package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 单车类型：(SingleVehicleType)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SingleVehicleType")
public class SingleVehicleType implements Serializable {

    // SingleVehicleType编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "single_vehicle_type_id")
    private Integer single_vehicle_type_id;

    // 单车类型
    @Basic
    private String single_vehicle_type;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
