package com.csc3402.lab.group.controller;

import com.csc3402.lab.group.model.Group;
import com.csc3402.lab.group.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("list")
    public String showAllGroups(Model model) {
        model.addAttribute("groups", groupService.listAllGroups());
        return "index";
    }

    @GetMapping("signup")
    public String showAddNewGroupForm(Group group, Model model) {
        return "add-group";
    }

    @PostMapping("add")
    public String addGroup(@Valid Group group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-group";
        }

        // Extract the month from the startDate or endDate
        String month = group.getStartDate().split("-")[1];

        // Get the list of groups for the same month
        List<Group> allGroups = groupService.listAllGroups();
        List<Group> sameMonthGroups = allGroups.stream()
                .filter(g -> {
                    String groupMonth = g.getStartDate().split("-")[1];
                    return groupMonth.equals(month);
                })
                .collect(Collectors.toList());

        // Check if a group with the same name exists for the specified month
        boolean nameExists = sameMonthGroups.stream()
                .anyMatch(g -> g.getCategory().equalsIgnoreCase(group.getCategory()));
        if (nameExists) {
            return "redirect:/group/signup?error2";
        }

        // Calculate the total budget for that month
        double totalBudget = sameMonthGroups.stream()
                .mapToDouble(Group::getBamount)
                .sum();

        // Check if adding the new group's budget exceeds the limit
        if (totalBudget + group.getBamount() <= 1000) {
            groupService.addNewGroup(group);
            return "redirect:/group/list";
        } else {
            // Redirect to the form with an error message
            return "redirect:/group/signup?error1";
        }
    }

    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        // Get the list of groups for June (month = 6)
        List<Group> allGroups = groupService.listAllGroups();

        // Filter groups for the month of June (month = 6)
        List<Group> juneGroups = allGroups.stream()
                .filter(g -> {
                    // Extract month from startDate
                    String groupMonth = g.getStartDate().split("-")[1];
                    return groupMonth.equals("06"); // Filter for June (month = 6)
                })
                .collect(Collectors.toList());

        model.addAttribute("groups", juneGroups);
        return "choose-category-to-update";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Group group = groupService.findGroupbyId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + id));
        model.addAttribute("group", group);
        return "update-category";
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") long id, @Valid Group group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            group.setBudgetId(id);
            return "update-category";
        }

        // Extract the month from the startDate or endDate
        String month = group.getStartDate().split("-")[1]; // Assuming your date format allows this

        // Get the list of groups for the same month (June)
        List<Group> allGroups = groupService.listAllGroups();
        List<Group> juneGroups = allGroups.stream()
                .filter(g -> isMonthInRange(g, "6")) // Assuming "6" is June
                .collect(Collectors.toList());

        // Remove the current group being updated from the list
        juneGroups = juneGroups.stream()
                .filter(g -> g.getBudgetId() != id)
                .collect(Collectors.toList());

        // Check if a group with the same name exists for the specified month
        boolean nameExists = juneGroups.stream()
                .anyMatch(g -> g.getCategory().equalsIgnoreCase(group.getCategory()));
        if (nameExists) {
            return "redirect:/group/edit/" + id + "?error2";
        }

        // Calculate the total budget for June
        double totalBudget = juneGroups.stream()
                .mapToDouble(Group::getBamount)
                .sum();

        // Check if adding the new group's budget exceeds the limit
        if (totalBudget + group.getBamount() <= 1000) {
            groupService.updateGroup(group);
            return "redirect:/group/list";
        } else {
            // Redirect to the form with an error message
            return "redirect:/group/edit/" + id + "?error1";
        }
    }


    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        // Get the list of groups for June (month = 6)
        List<Group> allGroups = groupService.listAllGroups();

        // Filter groups for the month of June (month = 6)
        List<Group> juneGroups = allGroups.stream()
                .filter(g -> {
                    // Extract month from startDate
                    String groupMonth = g.getStartDate().split("-")[1];
                    return groupMonth.equals("06"); // Filter for June (month = 6)
                })
                .collect(Collectors.toList());

        model.addAttribute("groups", juneGroups);
        return "choose-category-to-delete";


    }

    @GetMapping("delete/{id}")
    public String deleteGroup(@PathVariable("id") long id, Model model) {
        Group group = groupService.findGroupbyId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + id));

        groupService.deleteGroup(group);
        return "redirect:/group/list";
    }

    @GetMapping("/category")
    @ResponseBody
    public List<Group> getCategoryData(@RequestParam("month") String month) {
        List<Group> groups = groupService.listAllGroups();
        List<Group> filteredGroups = groups.stream()
                .filter(group -> isMonthInRange(group, month))
                .collect(Collectors.toList());
        return filteredGroups;
    }

    private boolean isMonthInRange(Group group, String month) {
        try {
            String startDate = group.getStartDate();
            String endDate = group.getEndDate();
            if (startDate == null || endDate == null) {
                return false;
            }
            int startMonth = Integer.parseInt(startDate.split("-")[1]);
            int endMonth = Integer.parseInt(endDate.split("-")[1]);
            int filterMonth = Integer.parseInt(month);
            return startMonth == filterMonth || endMonth == filterMonth;
        } catch (Exception e) {
            // Log the exception and the problematic group details for debugging
            System.err.println("Error processing group: " + group);
            e.printStackTrace();
            return false;
        }
    }

}
