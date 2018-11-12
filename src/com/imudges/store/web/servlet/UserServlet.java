package com.imudges.store.web.servlet;

import com.imudges.store.domain.User;
import com.imudges.store.service.UserService;
import com.imudges.store.service.serviceImp.UserServiceImp;
import com.imudges.store.utils.MailUtils;
import com.imudges.store.utils.MyBeanUtils;
import com.imudges.store.utils.UUIDUtils;
import com.imudges.store.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @Date: 2018/11/11 17:48
 * @Description:
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/login.jsp";
    }

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收表单参数 反射 -》 user
        User user = new User();
        MyBeanUtils.populate(user, request.getParameterMap());
        // 为用户其他属性赋值
        user.setUid(UUIDUtils.getId());
        user.setCode(UUIDUtils.getCode());
        user.setState(0);

        try {
            UserService userService = new UserServiceImp() {
            };
            userService.userRegist(user);
            // 注册成功向用户邮箱发送验证信息
            // 发送邮件
            MailUtils.sendMail(user.getEmail(), user.getCode());
            // 添加成功信息
            request.setAttribute("msg", "用户注册成功，请激活");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户注册失败，请重新注册");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 遍历map
//        Set<String> keySet = map.keySet();
//        Iterator<String> iterator = keySet.iterator();
//        while (iterator.hasNext()){
//            String key = iterator.next();
//            String[] values = map.get(key);
//            for(String k: values){
//                System.out.println("key:  " + key + "  values: " + k);
//            }
//        }
        return "/jsp/info.jsp";
    }

    /**
     * 用户激活
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String userActive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取激活码
        String code = request.getParameter("code");
        // 调用业务层激活功能
        UserService userService = new UserServiceImp();
        boolean flag = false;
        try {
            flag = userService.userActive(code);
            if (flag) {
                // 激活成功  向request放入提示信息，转发到登录界面
                request.setAttribute("msg", "用户激活成功，请登录");
                return "/jsp/login.jsp";
            } else {
                // 激活失败  向request放入提示信息，转发到登录界面
                request.setAttribute("msg", "用户激活失败，请重新激活");
                return "/jsp/info.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户数据
        User user = new User();
        MyBeanUtils.populate(user, request.getParameterMap());
        // 调用业务层功能
        UserService userService = new UserServiceImp();
        try {
            user = userService.userLodin(user);
            // 用户登录成功将信息放入sessionzhong
            request.getSession().setAttribute("loginUser", user);
            response.sendRedirect("/store/index.jsp");
            return null;
        }catch (Exception e){
            // 用户登录失败
            String msg = e.getMessage();
            request.setAttribute("msg", msg);
            return "/jsp/login.jsp";
        }
    }
}