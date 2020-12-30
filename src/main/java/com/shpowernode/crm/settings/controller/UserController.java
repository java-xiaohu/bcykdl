package com.shpowernode.crm.settings.controller;

import com.shpowernode.crm.base.constants.CrmConstants;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.base.util.UUIDUtil;
import com.shpowernode.crm.settings.bean.User;
import com.shpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/userlogin")
    public String login(User user, Model model, HttpServletRequest request){
        try {
            String remoteAddr = request.getRemoteAddr();
            user.setAllowips(remoteAddr);
            user=userService.login(user);
            request.getSession().setAttribute(CrmConstants.LOGIN_USER,user);
            return "/index";
        }catch (CrmException e){
            e.printStackTrace();
            model.addAttribute("loginAct",user.getLoginact());
            model.addAttribute("error",e.getMessage());
        }
        return "forward:/login.jsp";

    }

    @RequestMapping("/loginout")
    private String loginout(HttpServletRequest request){
        request.getSession().removeAttribute(CrmConstants.LOGIN_USER);
        return "redirect:/login.jsp";

    }
    @RequestMapping("/userQueryAll")
    @ResponseBody
    public List<User> queryAll(){
       return userService.selectAll();
    }

    //上传头像
    @RequestMapping("/userUpload")
    public String photoUpload(MultipartFile photo, HttpSession session,HttpServletRequest request){
        String contextPath = session.getServletContext().getRealPath("/upload");
        System.out.println("getRealPath="+contextPath);
        File file = new File(contextPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String originalFilename = photo.getOriginalFilename();
        originalFilename= UUIDUtil.getUUID()+originalFilename;
        try {
            photo.transferTo(new File(contextPath+File.separator+originalFilename));
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            System.out.println("request.getContextPath="+request.getContextPath());
            user.setPhoto(File.separator+"upload"+File.separator+originalFilename);
            userService.fileUpload(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
