package com.project.demo.controller;

import com.project.demo.entity.ParkingAreaSharing;
import com.project.demo.service.ParkingAreaSharingService;
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
 * 停车区域：(ParkingAreaSharing)表控制层
 *
 */
@RestController
@RequestMapping("/parking_area_sharing")
public class ParkingAreaSharingController extends BaseController<ParkingAreaSharing, ParkingAreaSharingService> {

    /**
     * 停车区域对象
     */
    @Autowired
    public ParkingAreaSharingController(ParkingAreaSharingService service) {
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
