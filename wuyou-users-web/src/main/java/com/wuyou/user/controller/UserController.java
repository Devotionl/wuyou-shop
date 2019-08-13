package com.wuyou.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wuyou.pojo.TbUser;
import com.wuyou.service.UserService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.PhoneFormatCheckUtils;

import java.util.List;


/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(timeout = 5000)
    private UserService userService;

//	@Autowired
//	private SellerService sellerService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbUser> findAll() {
        return userService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {

        return userService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param
     * @return
     */

    @RequestMapping("/add")
    public Result add(String username, String password, String phone, String smsCode) {
        System.out.println(username + password + password + smsCode);
        TbUser tbUser = new TbUser();
        tbUser.setPhone(phone);
        tbUser.setPassword(password);
        tbUser.setUsername(username);
        //校验验证码是否正确
        boolean checkSmscode = userService.checkSmsCode(tbUser.getPhone(), smsCode);
        if (!checkSmscode) {
            return new Result(false, "验证码不正确");
        }
        try {
            userService.add(tbUser);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbUser user) {
        try {
            userService.update(user);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbUser findOne(Long id) {
        return userService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            userService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbUser user, int page, int rows) {
        return userService.findPage(user, page, rows);
    }

    /**
     * 用户登录验证
     */

    @RequestMapping("/login")
    public Result login(String username, String password) {
        System.out.println(username + password);
        if (username.equals("admin") && password.equals("123")) {
            System.out.println("admin登录成功");
            return new Result(true, "登录成功admin");
        }
//		if(sellerService.SellerVerification(username,password)){
//			System.out.println("登录成功seller");
//			return  new Result(true,"登录成功seller");
//		}
        if (userService.selectbyusername(username, password)) {
            System.out.println("登录成功user");
            return new Result(true, "登录成功user");
        } else {
            System.out.println("登录失败");
            return new Result(false, "登录失败");
        }

    }

    @RequestMapping("/sendCode")
    public Result sendCode(String phone) {
        if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
            return new Result(false, "手机格式不正确");
        }
        try {
            userService.createSmsCode(phone);
            return new Result(true, "验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "验证码发送失败");
        }


    }

}
