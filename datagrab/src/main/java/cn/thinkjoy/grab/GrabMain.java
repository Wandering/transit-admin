package cn.thinkjoy.grab;

import cn.thinkjoy.grab.dao.ICheciDAO;
import cn.thinkjoy.grab.dao.ILuxianDAO;
import cn.thinkjoy.grab.domain.Checi;
import cn.thinkjoy.grab.domain.Luxian;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by admin on 2016/1/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springme.xml")
public class GrabMain {

    @Autowired
    ICheciDAO checiDAO;
    @Autowired
    ILuxianDAO luxianDAO;
    @Test
    public void testMainStart() {
//        this.springStart();
        Map<String,String> map=this.getTransitName();
        Iterator<String> keyIterator=map.keySet().iterator();
        while (keyIterator.hasNext()){
            String key=keyIterator.next();
            this.getTransitLine(key,map.get(key));
        }
    }

    public Map<String,String> getTransitName(){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.wngj.cn/index.php?m=content&c=index&a=lists&catid=24").get();
        } catch (IOException e) {
            System.out.println("读取网页失败");
        }
        Elements root = doc.getElementsByClass("xianlsu");
        Elements lisroot = root.select("a");
        Map<String,String> map = new HashMap<>();
        for(Element e:lisroot){
            map.put(e.text(),e.attr("href"));
            this.insertCheci(e.text());
        }
        return map;
    }


    public Map<String,String> getTransitLine(String name,String url){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.wngj.cn"+url).get();
        } catch (IOException e) {
            System.out.println("读取网页失败");
        }
        Elements root = doc.getElementsByClass("xianlssu");
        Elements lisroot = root.select("li");
        Map<String,String> map = new HashMap<>();
        for(Element e:lisroot){
            Elements elements=e.children().select("div");
            map.put(elements.get(0).text().trim(),elements.get(1).text().trim());
            Map<String,Object> map1=new HashMap<>();
            map1.put("name",name);
            this.insertXianlu(elements.get(0).text().trim(), elements.get(1).text().trim(),checiDAO.queryOne(map1,"id","asc",null).getId());
        }
        return map;
    }

//    public void springStart(){
//        // TODO Auto-generated method stub
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springme.xml");
//    }

    public void insertCheci(String name){
        Checi checi=new Checi();
        checi.setName(name);
        checiDAO.insert(checi);
    }
    public void insertXianlu(String paixu,String zhanming,Long pid){
        Luxian luxian = new Luxian();
        luxian.setPaixu(paixu);
        luxian.setZhanming(zhanming);
        luxian.setPid(Integer.valueOf(pid.toString()));
        luxianDAO.insert(luxian);
    }

}
