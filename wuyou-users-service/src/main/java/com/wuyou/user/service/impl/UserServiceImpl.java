package com.wuyou.user.service.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.dubbo.config.annotation.Service;
import com.wuyou.mapper.TbUserMapper;
import com.wuyou.pojo.TbUser;
import com.wuyou.pojo.TbUserExample;
import com.wuyou.service.UserService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.JmsTemplate;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbUser> findAll() {
        return userMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbUser> page = (Page<TbUser>) userMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * 增加
     */
    @Override
    public void add(TbUser user) {
        userMapper.insert(user);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbUser user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */

    public TbUser findOne(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            userMapper.deleteByPrimaryKey(id);
        }
    }


    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean selectbyusername(String username, String password) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<TbUser> users = userMapper.selectByExample(tbUserExample);
        if (users.size() == 0 || users == null) {
            return false;
        } else {
            return true;
        }

    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JmsTemplate jmsTemlate;
    @Autowired
    public Destination smsDestination;

    /**
     * 发送短信验证码
     *
     * @param phone
     */
    @Override
    public void createSmsCode(final String phone) {
        //1.生成一个6位的随机数
        final String smscode = (long) (Math.random() * 1000000) + "";
        System.out.println("验证码：" + smscode);
        //2.将随机数放入缓存redis
        System.out.println(phone + smscode);
        redisTemplate.boundHashOps("smscode").put(phone, smscode);
        //3.将短信内容发送到activeMQ
        jmsTemlate.send(smsDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("mobile", phone);//手机号
                message.setString("template_code", "SMS_165060589");//验证码
                message.setString("sign_name", "无忧商城");//签名
                Map map = new HashMap();
                map.put("code", smscode);
                message.setString("param", com.alibaba.fastjson.JSON.toJSONString(map));
                return message;
            }
        });
    }

    @Override
    public boolean checkSmsCode(String phone, String code) {
        String systemcode = (String) redisTemplate.boundHashOps("smscode").get(phone);
        if (systemcode == null) {
            return false;
        }
        if (!systemcode.equals(code)) {
            return false;
        }
        return true;
    }

    /**
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(TbUser user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        if (user != null) {
            if (user.getUsername() != null && user.getUsername().length() > 0) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (user.getPassword() != null && user.getPassword().length() > 0) {
                criteria.andPasswordLike("%" + user.getPassword() + "%");
            }
            if (user.getPhone() != null && user.getPhone().length() > 0) {
                criteria.andPhoneLike("%" + user.getPhone() + "%");
            }
            if (user.getEmail() != null && user.getEmail().length() > 0) {
                criteria.andEmailLike("%" + user.getEmail() + "%");
            }

            if (user.getSex() != null && user.getSex().length() > 0) {
                criteria.andSexLike("%" + user.getSex() + "%");
            }

        }

        Page<TbUser> page = (Page<TbUser>) userMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


}
