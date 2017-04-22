package com.eliteams.quick4j.test;

/**
 * @作者 刘宝军
 * Created by Edion on 2017/1/13.
 */

import com.eliteams.quick4j.web.model.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * redis spring 简单例子
 * @author hk
 *
 * 2012-12-22 上午10:40:15
 */

public class TestRedis {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //这里已经配置好,属于一个redis的服务接口
        RedisService redisService = (RedisService) app.getBean("redisService");



        String ping = redisService.ping();//测试是否连接成功,连接成功输出PONG
        System.out.println(ping);

        //首先,我们看下redis服务里是否有数据
        long dbSizeStart = redisService.dbSize();
        System.out.println(dbSizeStart);

        redisService.set("username", "oyhk");//设值(查看了源代码,默认存活时间30分钟)
        String username = redisService.get("username");//取值
        System.out.println(username);
        redisService.set("username1", "oyhk1", 1);//设值,并且设置数据的存活时间(这里以秒为单位)
        String username1 = redisService.get("username1");
        System.out.println(username1);
        Thread.sleep(2000);//我睡眠一会,再去取,这个时间超过了,他的存活时间
        String liveUsername1 = redisService.get("username1");
        System.out.println(liveUsername1);//输出null

        //是否存在
        boolean exist = redisService.exists("username");
        System.out.println(exist);

        //查看keys
        Set<String> keys = redisService.keys("*");//这里查看所有的keys
        System.out.println(keys);//只有username username1(已经清空了)

        //删除
        redisService.set("username2", "oyhk2");
        String username2 = redisService.get("username2");
        System.out.println(username2);
        redisService.del("username2");
        String username2_2 = redisService.get("username2");
        System.out.println(username2_2);//如果为null,那么就是删除数据了

        //dbsize
        long dbSizeEnd = redisService.dbSize();
        System.out.println(dbSizeEnd);

        //清空reids所有数据
        //redisService.flushDB();


//        List<String> a=new ArrayList<String>();
//        a.add("1");
//        a.add("2");
////        for (String temp : a){
////            if("2".equals(temp)){
////                a.remove(temp);
////                System.out.println("删除："+temp);
////            }
////        }
//
//
//        Iterator<String> it=a.iterator();
//        while (it.hasNext()){
//            String temp=it.next();
//            if("2".equals(temp)){
//                it.remove();
//            }
//        }

    }
}
