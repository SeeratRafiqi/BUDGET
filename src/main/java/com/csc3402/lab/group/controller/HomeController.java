package com.csc3402.lab.group.controller;

import com.csc3402.lab.group.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final GroupService groupService;

    public HomeController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("groups", groupService.listAllGroups());
        return "index";
    }

}

