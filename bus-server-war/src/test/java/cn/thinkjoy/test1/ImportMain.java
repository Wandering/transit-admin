package cn.thinkjoy.test1;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.domain.Profession;
import cn.thinkjoy.gaokao360.domain.ProfessionDetail;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.service.common.IProfessionDetailService;
import cn.thinkjoy.gaokao360.service.common.IProfessionService;
import cn.thinkjoy.gaokao360.service.common.IProfessionTypeService;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by admin on 2016/1/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springtest.xml")
public class ImportMain {

    @Autowired
    private IProfessionTypeService professionTypeService;
    @Autowired
    private IProfessionService professionService;
    @Autowired
    private IProfessionDetailService professionDetailService;

    @Test
    public void test1(){
        this.doImport(new File("C:\\Users\\admin\\Documents\\Tencent Files\\963984443\\FileRecv\\职业ok .xls"),0);
        this.doImport(new File("C:\\Users\\admin\\Documents\\Tencent Files\\963984443\\FileRecv\\职业信息ok.xls"),1);

    }

    public Map<String,Object> doImport(File file,Integer type){
        String handleString=null;
        long startTime = System.currentTimeMillis();

            try {
                List list= ImportExcelUtil.doImport(file);
                //处理获得的list
                if(type==0) {
                innerHandleImport(list);
                }else {
                    innerHandleImport1(list);
                }
            } catch (Exception e) {
                throw  new BizException("","读取数据异常");
            }
        long endTime = System.currentTimeMillis();

        Map<String,Object> map = new HashMap<>();
        map.put("开始时间",startTime);
        map.put("结束时间",endTime);
        map.put("耗时",endTime-startTime);
        map.put("错误信息",handleString);
        return map;
    }

    //职业门类
    protected String  innerHandleImport(List<Map<String,String>> list){
        int i= 0;
        int errInt=1;
        List<String> errlist=new ArrayList<>();
        for(Map<String,String> map:list){
            errInt++;
            try {
                Map<String, Object> map1 = new HashMap();
                map1.put("professionType", map.get("所属行业").toString().trim());
                ProfessionType professionType1 = (ProfessionType) professionTypeService.queryOne(map1);
                if (professionType1 == null) {
                    professionType1 = new ProfessionType();
                    professionType1.setProfessionType(map.get("所属行业").toString().trim());
                    professionTypeService.insert(professionType1);
                    professionType1 = (ProfessionType) professionTypeService.queryOne(map1);
                }
                map1 = new HashMap();
                map1.put("professionType", map.get("职业分类").toString().trim());
                map1.put("pid", professionType1.getId());
                ProfessionType professionType2 = (ProfessionType) professionTypeService.queryOne(map1);
                if (professionType2 == null) {
                    professionType2 = new ProfessionType();
                    professionType2.setProfessionType(map.get("职业分类").toString().trim());
                    professionType2.setPid(professionType1.getId());
                    professionTypeService.insert(professionType2);
                    professionType2 = (ProfessionType) professionTypeService.queryOne(map1);
                }
                map1 = new HashMap();
                map1.put("professionName", map.get("职业名称").toString().trim());
                Profession profession = (Profession) professionService.queryOne(map1);
                if (profession == null) {
                    profession = new Profession();
                    profession.setProfessionName(map.get("职业名称").toString().trim());
                    profession.setProfessionType(professionType2.getPid().toString());
                    profession.setProfessionSubType(professionType2.getId().toString());
                    profession.setIdDelete(false);
                    profession.setProfessionShort("简介");
                    professionService.insert(profession);
                }
            }catch (Exception e){
                e.printStackTrace();
                errlist.add(String.valueOf(errInt));
                continue;
            }

            System.out.println("当前处理进度："+getPercent(i++,list.size()));
        }
        for(String s : errlist){
            System.out.println(s+",");
        }
        return "true";
    }

    /**
     * 职业导入处理
     * @param list
     * @return
     */
    protected String  innerHandleImport1(List<Map<String,String>> list){
        int i= 0;
        List<String> errlist=new ArrayList<>();
        int errInt=1;
        for(Map<String,String> map:list){
            errInt++;
            try {
                Map<String, Object> map1 = new HashMap();
                map1.put("professionType", map.get("所属行业").toString().trim());
                ProfessionType professionType1 = (ProfessionType) professionTypeService.queryOne(map1);
                map1 = new HashMap();
                map1.put("professionName", map.get("职业名称").toString().trim());
                Profession profession = (Profession) professionService.queryOne(map1);
                if (profession != null) {
                    String str1 = map.get("简介").toString().trim();
                    if (str1.length() > 100) {
                        str1 = str1.substring(0, 100);
                    }
                    profession.setProfessionShort(str1);
                    professionService.update(profession);
                    ProfessionDetail professionDetail = new ProfessionDetail();
                    professionDetail.setId(profession.getId());
                    if(map.get("就业前景")!=null)
                        professionDetail.setCareerProspects(map.get("就业前景").toString());
                    if(map.get("从业要求及条件")!=null)
                        professionDetail.setVocationalDemand(map.get("从业要求及条件").toString());
                    if(map.get("从业要求及条件")!=null)
                        professionDetail.setWorkContent(map.get("从业要求及条件").toString());
                    if(map.get("简介")!=null)
                    professionDetail.setIntroduction(map.get("简介").toString());
                    if(map.get("相关专业")!=null)
                    professionDetail.setRelateMajor(map.get("相关专业").toString());
                    professionDetail.setIsDelete(false);
                    professionDetailService.insert(professionDetail);
                }

            }catch (Exception e){
                e.printStackTrace();
                errlist.add(String.valueOf(errInt));
                continue;
            }
            System.out.println("当前处理进度："+getPercent(i++,list.size()));
        }
        for(String s : errlist){
            System.out.println(s+",");
        }
        return "true";
    }
    public final List getList(List<Map<String,String>> list){
        Map<String,String> corrMap=this.corrMap();
        List<Map<String,Object>> list1 = new ArrayList<>();
        for(Map map : list){
            Map<String,Object> keyMap=new HashMap<>();
            for(String s:corrMap.keySet()){
                if(map.containsKey(s)){
                    keyMap.put(corrMap.get(s),map.get(s));
                }
                list1.add(keyMap);
            }
        }
        return list1;
    }

    protected Map<String,String> corrMap(){
        return new HashMap<>();
    }




    public String getPercent(int x,int total){
        String result="";//接受百分比的值
        double x_double=x*1.0;
        double total_double=total*1.0;
        double tempresult=x_double/total_double;
        DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
        result= df1.format(tempresult);
        return result;
    }
}
