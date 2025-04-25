package com.project.demo.controller;

import com.project.demo.entity.LeaseInformation;
import com.project.demo.service.LeaseInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 租赁信息：(LeaseInformation)表控制层
 *
 */
@RestController
@RequestMapping("/lease_information")
public class LeaseInformationController extends BaseController<LeaseInformation, LeaseInformationService> {

    /**
     * 租赁信息对象
     */
    @Autowired
    public LeaseInformationController(LeaseInformationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(lease_information_id) AS max FROM "+"`lease_information`";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = ("SELECT count(*) count FROM `single_car_information` INNER JOIN `lease_information` ON single_car_information.management_serial_number=lease_information.management_serial_number WHERE single_car_information.number_of_single_vehicles < lease_information.number_of_leases AND lease_information.lease_information_id="+max).replaceAll("&#60;","<");
        select = service.runCountSql(sql);
        Integer count = Integer.valueOf(String.valueOf(select.getSingleResult()));
        if(count>0){
            sql = "delete from "+"lease_information"+" WHERE "+"lease_information_id"+" ="+max;
            select = service.runCountSql(sql);
            select.executeUpdate();
            return error(30000,"单车数量不足！");
        }
        sql = "UPDATE `single_car_information` INNER JOIN `lease_information` ON single_car_information.management_serial_number=lease_information.management_serial_number SET single_car_information.number_of_single_vehicles= single_car_information.number_of_single_vehicles - lease_information.number_of_leases WHERE lease_information.lease_information_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
