package com.danekja.edu.ormprobe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by witz on 12.4.15.
 */
@Controller
@RequestMapping(value="/")
public class MainController {

  public MainController(){
  }

  @RequestMapping(value="index", method = RequestMethod.GET)
  public ModelAndView getGroups(){
    System.out.println("main");
    return new ModelAndView("index");
  }
}
