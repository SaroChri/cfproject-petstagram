package gr.aueb.cf.finalprojpets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class MainPageController {

        @GetMapping("/main")
        public String mainPage(Model model) {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);

            model.addAttribute("dateTxt", formattedDate);

            return "main";
        }
}
