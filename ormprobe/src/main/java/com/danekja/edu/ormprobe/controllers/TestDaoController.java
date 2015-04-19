package com.danekja.edu.ormprobe.controllers;

import com.danekja.edu.ormprobe.dao.TestDao;
import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * Created by witz on 19.4.15.
 */
@Controller
@RequestMapping(value = "/i")
public class TestDaoController {
  private TestDao testDaoService;

  public void setTestDaoService(TestDao daoService) {
    this.testDaoService = daoService;
  }

  @RequestMapping(value = "/loc")
  public ModelAndView listOwnershipCandidates(@RequestParam(value = "id") long id) {
    Set<Group> groups = testDaoService.listOwnershipCandidates(id);
    for (Group group : groups) {
      System.out.println(group.getName());
    }
    return new ModelAndView("index");
  }

  @RequestMapping(value = "/ictob")
  public ModelAndView isConnectedToBigGroup(@RequestParam(value = "gid") long gid,
                                            @RequestParam(value = "iid") long iid) {

    System.out.println(gid + " " + iid + ": " + testDaoService.isConnectedToBigGroup(gid, iid));
    return new ModelAndView("index");
  }

  @RequestMapping(value = "/lbgi")
  public ModelAndView listBigGroupItems(@RequestParam(value = "id") long id) {
    Set<Item> items = testDaoService.listBigGroupsItems(id);
    for (Item item : items) {
      System.out.println(item.getName());
    }
    return new ModelAndView("index");
  }

  @RequestMapping(value = "/libg")
  public ModelAndView listItemsBigGroups(@RequestParam(value = "id") long id) {
    Set<BigGroup> groups = testDaoService.listItemsBigGroups(id);
    for (BigGroup bigGroup : groups) {
      System.out.println(bigGroup.getName());
    }
    return new ModelAndView("index");
  }
}
