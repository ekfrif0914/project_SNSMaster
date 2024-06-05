package com.codemaster.project_snsmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroupController {


    @RequestMapping(value = "/")//첫화면
    public String home() {

        return null;
    }

}
