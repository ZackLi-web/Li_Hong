package vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VotePageController {

    // 1. 使用者投票首頁 (導向 http://localhost:8082/vote/)
    @GetMapping("/index")
    public String indexPage() {
        // 會自動尋找 resources/templates/index.html
        return "index";
    }

    // 2. 後台管理頁面 (導向 http://localhost:8082/vote/admin)
    @GetMapping("/admin")
    public String adminPage() {
        // 會自動尋找 resources/templates/admin.html
        return "admin";
    }
}
