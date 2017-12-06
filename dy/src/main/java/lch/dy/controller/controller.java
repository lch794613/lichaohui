package lch.dy.controller;

import lch.dy.controller.Entity.commit;
import lch.dy.controller.Entity.register;
import lch.dy.controller.Entity.user;
import lch.dy.controller.serivce.commitService;
import lch.dy.controller.serivce.registerService;
import lch.dy.util.IpUtil;
import lch.dy.util.JSM;
import lch.dy.util.create_code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class controller {

    @Autowired
    private registerService registerService;
    @Autowired
    private commitService commitService;
    @Autowired
    private JSM jsm;
    @Autowired
    private create_code create_code;


    @RequestMapping("/")
    public String index(register register){
        return "index";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(register register,HttpSession session) {
        String email=register.getEmail();
        System.out.println(email);
        String password=register.getLoginpassword();
        String code =register.getCode();
        user u=registerService.queryByName(email);
        String e_code=(String)session.getAttribute("code");
        System.out.println("注册code:"+e_code);
        if(u==null){
            if(code.equals(e_code)){
                registerService.registerUser(email,password);
                System.out.println("注册成功");
                return "index";
            }
        }
        if (u!=null){
            System.out.println("帐号已经被注册");
            return "index";
        }
        return null;
    }


    @RequestMapping("/email/ajax")
    @ResponseBody
    public String email(HttpServletRequest request, HttpSession session){
        String e_address=request.getParameter("email");
        System.out.println(e_address);
        String code=create_code.create();
        session.setAttribute("code",code);
        System.out.println("邮件发送的code:"+code);
        jsm.sendTxtMail(e_address,code);
        return "OK";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/project")
    public String project(){
        return "project";
    }
    @RequestMapping("/resume")
    public String resume(){
        return "resume";
    }

    @RequestMapping("/commit")
    public String commit(commit commit){
        return "commit";
    }
    @RequestMapping(value="/commit",method = RequestMethod.POST)
    public String PostCommit(commit commit,HttpServletRequest request){
        commitService.insertCommit(IpUtil.getIpAddr(request),commit.getCont());
        /*request.getRemoteAddr()*/
        return "redirect:/commit";
    }
    @RequestMapping("/commit/ajax")
    @ResponseBody
    public List<commit> ajaxCommit(HttpServletRequest request){
        int index=Integer.parseInt(request.getParameter("start"));
        List<commit> c=commitService.queryByIndex(index);
        return c;
    }
}
