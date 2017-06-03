package org.aziz.studentapp.controller;

import org.aziz.studentapp.model.Course;
import org.aziz.studentapp.service.CourseRepository;
import org.aziz.studentapp.service.FeatureService;
import org.aziz.studentapp.service.FeatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    private FeatureService featureService = new FeatureServiceImpl();

    @RequestMapping(value = "/")
    public String loadIndex(Model model) {
        String message;
        if (featureService.getNODE_ID().equals("")) {
            model.addAttribute("pod", "No group found for this pod, default mode enabled");
        } else {
            model.addAttribute("pod", featureService.getNODE_ID());
        }
        model.addAttribute("featureService", featureService);
        if (featureService.isActive(featureService.getNODE_ID() + ".db-persistence")) {
            model.addAttribute("courses", courseDAO.findAll().iterator());
        } else {
            message = "No database connected";
            model.addAttribute("message", message);
        }

        return "index";
    }

    @RequestMapping(value = "/addNewCourse", method = RequestMethod.POST)
    public String addNewCourse(@RequestParam("input_title") String input_title, @RequestParam String input_location, @RequestParam String input_desc, @RequestParam String input_category) {
        Course course = new Course(input_title, input_category, input_location, input_desc);
        courseDAO.save(course);
        return "redirect:/";
    }

    @Autowired
    private CourseRepository courseDAO;


}
