package com.danekja.edu.ormprobe.controllers;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.persistance.GroupMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

/**
 * Created by witz on 12.4.15.
 */
@Controller
@RequestMapping(value="/g")
public class GroupsController {

  private GroupMapper groupService;

  public void setGroupService(GroupMapper gs){
    this.groupService = gs;
  }

  public GroupsController(){
  }

  @RequestMapping(value="/a", method = RequestMethod.GET)
  public ModelAndView getAll() {
    List<Group> gs = groupService.getAll();
    for(Group g : gs) {
      System.out.println(g.getName());
    }
    return new ModelAndView("index");
  }

  @RequestMapping(value="/ibg")
  public ModelAndView getItemsBigGroups(@RequestParam("id") String id) {
    Set<BigGroup> gs = groupService.listItemsBigGroups(Long.parseLong(id));
    for(BigGroup bg : gs) {
      System.out.println(bg.getName());
    }
    return new ModelAndView("index");
  }

  @RequestMapping(value = "/loc")
  public ModelAndView listOwnershipCandidates(@RequestParam("id") long id) {
    Set<Group> gs = groupService.listOwnershipCandidates(id);
    for(Group bg : gs) {
      System.out.println(bg.getName());
    }
    return new ModelAndView("index");
  }
}
