package com.project.demo.controller;

import com.project.demo.entity.OwnerUser;
import com.project.demo.service.OwnerUserService;
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
 * 车主用户：(OwnerUser)表控制层
 *
 */
@RestController
@RequestMapping("/owner_user")
public class OwnerUserController extends BaseController<OwnerUser, OwnerUserService> {

    /**
     * 车主用户对象
     */
    @Autowired
    public OwnerUserController(OwnerUserService service) {
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
