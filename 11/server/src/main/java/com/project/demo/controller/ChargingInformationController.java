package com.project.demo.controller;

import com.project.demo.entity.ChargingInformation;
import com.project.demo.service.ChargingInformationService;
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
 * 充电信息：(ChargingInformation)表控制层
 *
 */
@RestController
@RequestMapping("/charging_information")
public class ChargingInformationController extends BaseController<ChargingInformation, ChargingInformationService> {

    /**
     * 充电信息对象
     */
    @Autowired
    public ChargingInformationController(ChargingInformationService service) {
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
