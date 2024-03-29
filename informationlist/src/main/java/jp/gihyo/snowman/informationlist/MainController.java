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


    @GetMapping("/main/add")//個々のための情報、タイトル、内容、メンバー、日付
    String addItem(@RequestParam("task")String task,
                   @RequestParam("content_id") String content_id,
                   @RequestParam("member_id") String member_id,
                   @RequestParam("deadline") String deadline){
        String id = UUID.randomUUID().toString().substring(0,8);
        HomeController.TaskItem item = new HomeController.TaskItem(id,task,content_id,member_id,deadline);
        dao.add(item);

        return "redirect:/main";
    }
    @GetMapping("/main/{member_id}")
    //個々のメンバー情報を出す場所
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
        if (member_id.equals("5")){
            name="shoppi.jpg";
        }
        if (member_id.equals("6")){
            name="ko-ji.jpg";
        }
        if (member_id.equals("7")){
            name="abetyan.jpg";
        }
        if (member_id.equals("8")){
            name="sakkun.jpg";
        }
        if (member_id.equals("9")){
            name="meme.jpg";
        }
        model.addAttribute("name",name);


//        List<HomeController.TaskItem> member = dao.findAll();
//        model.addAttribute("member",member);

        return "main";
    }
}

