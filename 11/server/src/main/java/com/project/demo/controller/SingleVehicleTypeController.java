package com.project.demo.controller;

import com.project.demo.entity.SingleVehicleType;
import com.project.demo.service.SingleVehicleTypeService;
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
 * 单车类型：(SingleVehicleType)表控制层
 *
 */
@RestController
@RequestMapping("/single_vehicle_type")
public class SingleVehicleTypeController extends BaseController<SingleVehicleType, SingleVehicleTypeService> {

    /**
     * 单车类型对象
     */
    @Autowired
    public SingleVehicleTypeController(SingleVehicleTypeService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
