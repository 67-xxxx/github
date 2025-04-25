package com.project.demo.controller;

import com.project.demo.entity.ReturnInformation;
import com.project.demo.service.ReturnInformationService;
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
 * 归还信息：(ReturnInformation)表控制层
 *
 */
@RestController
@RequestMapping("/return_information")
public class ReturnInformationController extends BaseController<ReturnInformation, ReturnInformationService> {

    /**
     * 归还信息对象
     */
    @Autowired
    public ReturnInformationController(ReturnInformationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> maplease_number = new HashMap<>();
        maplease_number.put("lease_number",String.valueOf(paramMap.get("lease_number")));
        List listlease_number = service.select(maplease_number, new HashMap<>()).getResultList();
        if (listlease_number.size()>0){
            return error(30000, "字段租用号内容不能重复");
        }
        this.addMap(paramMap);
        String sql = "SELECT MAX(return_information_id) AS max FROM "+"`return_information`";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = "UPDATE `single_car_information` INNER JOIN `return_information` ON single_car_information.management_serial_number=return_information.management_serial_number SET single_car_information.number_of_single_vehicles= single_car_information.number_of_single_vehicles + return_information.number_of_leases WHERE return_information.return_information_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
