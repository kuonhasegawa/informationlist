package jp.gihyo.snowman.informationlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final TaskListDao dao;

    @Autowired
    MainController(TaskListDao dao){
        this.dao = dao;
    }

    record TaskItem(String id, String information,String task, String content_id,String member_id,String deadline){}
    private List<HomeController.TaskItem> taskItems = new ArrayList<>();


    @GetMapping("/main/add")
    String addItem(@RequestParam("information")String information,
                   @RequestParam("task")String task,
                   @RequestParam("content_id") String content_id,
                   @RequestParam("member_id") String member_id,
                   @RequestParam("deadline") String deadline){
        String id = UUID.randomUUID().toString().substring(0,8);
        HomeController.TaskItem item = new HomeController.TaskItem(id,information,task,content_id,member_id,deadline);
        dao.add(item);

        return "redirect:/main";
    }
    @GetMapping("/main/{member_id}")
    String listItems(Model model, @PathVariable("member_id") String member_id){
        List<HomeController.TaskItem> taskItems = dao.findMember(member_id);
        model.addAttribute("taskList",taskItems);
        String name="";
        if (member_id.equals("1")){
            name="hi-kun.jpg";
        }
        if (member_id.equals("2")){
            name="fukka.jpg";
        }
        if (member_id.equals("3")){
            name="rau.jpg";
        }
        if (member_id.equals("4")){
            name="datesama.jpg";
        }
        model.addAttribute("name",name);


//        List<HomeController.TaskItem> member = dao.findAll();
//        model.addAttribute("member",member);

        return "main";
    }
}
