package com.example.ujwalfruit;


import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {


    @Autowired
    DayRepo dayRepo;
    @Autowired
    FruitRepo fruitRepo;

    @Autowired
    CloudinaryConfig cloud;



    //Adding fruits and returning to the homepage
    @RequestMapping("/")

   public String HomePage(Model model) {

        model.addAttribute("days", dayRepo.findAll());
        return "home";
    }
    @GetMapping("/adminpage")
    public  String admin(Model model){
        return "adminpage";
    }

    @GetMapping("/addnewfruit")
    public  String addFruit(Model model){

        model.addAttribute("fru", new Fruit());
        return "addnewfruit";
    }


    @GetMapping("/changefruit")
    public  String adminChangeFruit(Model model){
        model.addAttribute("day", dayRepo.findAll());
        return "changefruits";
    }


    @GetMapping("/changehours")
    public  String adminChangeHours(Model model){
        model.addAttribute("day", dayRepo.findAll());
        return "changehours";
    }

    @GetMapping("/fruitform")
    public  String addCar(Model model){
        model.addAttribute("fr", fruitRepo.findAll());
        model.addAttribute("da", new Weekday());
        return "updateeachfruit";
    }


    @RequestMapping("/updatefruit/{id}")
    public String updatefruit(@PathVariable("id") long id, Model model) {
        model.addAttribute("fr", fruitRepo.findAll());
        model.addAttribute("da", dayRepo.findById(id).get());
        return "updateeachfruit";

    }

    @GetMapping("/hoursform")
    public  String hoursform(Model model){
        model.addAttribute("fr", fruitRepo.findAll());
        model.addAttribute("da", new Weekday());
        return "hourform";


    }


    @RequestMapping("/updatehours/{id}")
    public String updatehours(@PathVariable("id") long id, Model model) {
        model.addAttribute("da", dayRepo.findById(id).get());
        return "hourform";



    }
    @RequestMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("daily", dayRepo.findById(id).get());
        return "dailydetail";
    }


    @PostMapping("/processfruits")
    public String processFruits(@Valid @ModelAttribute("weekday") Weekday weekday,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "/changefruit";
        }
        dayRepo.save(weekday);
        return "redirect:/";

    }



    @PostMapping("/processhours")
    public String processhours(@Valid @ModelAttribute("weekday") Weekday weekday,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "/changehours";
        }
        dayRepo.save(weekday);
        return "test";

    }




    @PostMapping("/processfruit")
    public String processCarForm(@Valid @ModelAttribute("car") Fruit fruit,
                                 BindingResult result, @RequestParam("file")MultipartFile file) {
        if (result.hasErrors()) {
            return "addnewfruit";
        }
        else if (file.isEmpty()) {
            return "redirect:/addnewfruit";
        }
        try {
            Map uploadResult = cloud.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            fruit.setImageurl(uploadResult.get("url").toString());
            fruitRepo.save(fruit);
        }
        catch (IOException e) {
            e.printStackTrace();
            return "redirect:/addnewfruit";

        }
        return "test";

    }
}
