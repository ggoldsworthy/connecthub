package api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    // bundled JavaScript files
    private final String SIGN_UP_SCRIPT = ""; 
    private final String LOG_IN_SCRIPT = ""; 
    private final String POST_SCRIPT = ""; 
    private final String CREATE_POST_SCRIPT = "";  
    private final String HOME_PAGE_SCRIPT = "";  
    
    private final String TEMPLATE_FILE = "index";
    private final String PAGE_TITLE = "page_title";
    private final String SCRIPT = "script";


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(PAGE_TITLE, "Home");
        model.addAttribute(SCRIPT, HOME_PAGE_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute(PAGE_TITLE, "Home");
        model.addAttribute(SCRIPT, HOME_PAGE_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/signin")
    public String signIn(Model model) {
        model.addAttribute(PAGE_TITLE, "Sign Up");
        model.addAttribute(SCRIPT, SIGN_UP_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(PAGE_TITLE, "Log In");
        model.addAttribute(SCRIPT, LOG_IN_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/createPost")
    public String createPost(Model model) {
        model.addAttribute(PAGE_TITLE, "Create Post");
        model.addAttribute(SCRIPT, CREATE_POST_SCRIPT);
        return TEMPLATE_FILE;
    }

    @GetMapping("/post")
    public String post( Model model) {
        model.addAttribute(PAGE_TITLE, "Home");
        model.addAttribute(SCRIPT, POST_SCRIPT);
        return TEMPLATE_FILE;
    }

}
