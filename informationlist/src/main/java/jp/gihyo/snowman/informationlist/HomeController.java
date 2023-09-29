package jp.gihyo.snowman.informationlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    private final TaskListDao dao;

    @Autowired
    HomeController(TaskListDao dao){
        this.dao = dao;
    }

//    @RequestMapping(value ="/hello")
//    @ResponseBody
//    String hello(){
//        return """
//                <html>
//                    <head><title>Hello</head></title>
//                    <body>
//                    <h1>Hello</h1>
//                    It works!<br>
//                    現在時刻は%sです。
//                    </body>
//                   </html>
//                """.formatted(LocalDateTime.now());
//    }

    record TaskItem(String id, String information,String task, String content,String deadline){}
    private List<TaskItem> taskItems = new ArrayList<>();

    @RequestMapping(value = "/hello")
    String hello(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }
    @GetMapping("/list")
    String listItems(Model model){
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("taskList",taskItems);
        return "home";
    }

    @GetMapping("/add")
    String addItem(@RequestParam("information")String information,
                   @RequestParam("task")String task,
                   @RequestParam("content") String content,
                   @RequestParam("deadline") String deadline){
        String id = UUID.randomUUID().toString().substring(0,8);
        TaskItem item = new TaskItem(id,information,task,content,deadline);
        dao.add(item);

        return "redirect:/list";
    }
    @GetMapping("/delete")
    String deleteItem(@RequestParam("id")String id){
        dao.delete(id);
        return "redirect:/list";
    }


    @GetMapping("/home")
    String home(Model model) {
        return "home0";
    }
}