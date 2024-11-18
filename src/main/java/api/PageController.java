package api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    private final String TEMPLATE_FILE = "index";

    @GetMapping("/")
    public String homePage() {
        return TEMPLATE_FILE;
    }

    @GetMapping("/signin")
    public String signIn() {
        return TEMPLATE_FILE;
    }

    @GetMapping("/login")
    public String login() {
        return TEMPLATE_FILE;
    }

    @GetMapping("/createPost")
    public String createPost() {
        return TEMPLATE_FILE;
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") String postID) {
        return TEMPLATE_FILE;
    }

}
