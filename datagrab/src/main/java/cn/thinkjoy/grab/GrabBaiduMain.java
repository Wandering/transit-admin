package cn.thinkjoy.grab;

import cn.thinkjoy.grab.common.RequestUtils;
import cn.thinkjoy.grab.common.StringUtils;
import org.junit.Test;

/**
 * Created by admin on 2016/1/30.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:springme.xml")
public class GrabBaiduMain {

    String host = "http://map.baidu.com/";
//    String host = "http://180.97.33.128:80/";
    //获取当前坐标
    String currPosition = "?qt=cur&wd=%E8%A5%BF%E5%AE%89%E5%B8%82&t=1454653065513&dtype=1";
    //搜索当前输入框内容
    String serch = "?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=s&da_src=searchBox.button&wd=251路&c=233&src=0&wd2=&sug=0&l=17&b=(12120215,4031025;12122619,4031561)&from=webmap&biz_forward={%22scaler%22:1,%22styles%22:%22pl%22}&sug_forward=&tn=B_NORMAL_MAP&nn=0&ie=utf-8&t=1454655863047";
    //获取当前路线详细信息
    String route = "?qt=bsl&tps=&newmap=1&uid=c3f79156470b0e033210af5f&c=233";

    String viaSite = "?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=ext&uid=c3f79156470b0e033210af5f&l=18&c=233&tn=B_NORMAL_MAP&nn=0&ie=utf-8&b=(12086866.615,4026135.9850000003;12163794.615,4045591.9850000003)&t=1454656448160";
    String viaSite1 = "?qt=panobatch";

    @Test
    public void getBusName() {
        String url = host + viaSite1 + "";
        String param="udt=20160201" +
                "&qt=batchdata" +
                "&from=busline" +
                "&key=12130525.51,4042367.47|12130438.01,4040929.06|12129563.03,4040720.96|12129549.00,4040421.97|12128566.49,4040251.96|12127883.98,4040133.03|12127812.51,4039737.52|12127157.50,4039658.96|12126970.48,4039092.93|12126546.57,4039073.49|12125750.07,4039051.51|12125218.52,4039038.50|12124898.47,4039033.01|12123671.49,4038883.93|12123493.49,4037869.55|12123371.04,4037624.52|12122922.53,4037000.02|12122397.54,4037051.49|12122225.44,4036490.02|12121933.44,4035665.50|12121622.97,4035662.95|12121346.45,4035670.46|12120789.51,4035684.53|12120559.97,4035060.41|12120596.03,4034455.09|12120691.44,4033323.06|12120127.49,4033011.13|12120120.03,4032578.62|12120212.53,4032025.02";
        String result = RequestUtils.requestPostData(url,param);
        System.out.println(StringUtils.UnicodetoChinese(result));
    }

}
