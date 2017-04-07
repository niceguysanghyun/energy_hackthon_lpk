package com.lpk.energy.controller;

import com.lpk.energy.ClassDo;
import com.lpk.energy.TimeTableLoad;
import com.lpk.energy.TimeTableMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

/**
 * Created by HeeJoongKim on 2017-04-07.
 */
@Controller
public class WebController
{

    @Autowired
    TimeTableMongoRepository timeTableMongoRepository;

    @RequestMapping(value="/")
    public String main(){
        return "main";
    }
    @RequestMapping(value="/main")
    public String mainframe(){
        return "main";
    }

    @RequestMapping(value="/flot")
    public String flot(){
        return "flot";
    }


    @RequestMapping(value="/test1234")
    public String test(){
        TimeTableLoad timeTableLoad = new TimeTableLoad();
        timeTableMongoRepository.save(timeTableLoad.send());
        return "main";
    }

    @RequestMapping(value="/morris")
    public String morris(){
        return "morris";
    }
    @RequestMapping(value="/tables", method = RequestMethod.GET)
    public String tables(Model model, @RequestParam(required = false) String room){
        List<ClassDo> boards = timeTableMongoRepository.findAll();
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK)-1;
        String today = week[num];
        System.out.println("test"+boards.get(0).getName());
        System.out.println(today);
        for (int i=0;i<boards.size();i++) {
            if (!boards.get(i).getRoom().contains(today)) {
                boards.remove(i);
            }
        }
        model.addAttribute("boards",boards);
        return "tables";
    }

    @RequestMapping(value="/calendar")
    public String calendar(){
        return "calendar";
    }
}
