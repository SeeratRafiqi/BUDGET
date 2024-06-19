package com.csc3402.lab.group.service;

import com.csc3402.lab.group.model.Group;
import com.csc3402.lab.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImp implements GroupService{

    private final GroupRepository groupRepository;

    public GroupServiceImp(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Override
    public List<Group> listAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group addNewGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Optional<Group> findGroupbyId(Long budgetId) {
        return groupRepository.findById(budgetId);
    }


    @Override
    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }
}
