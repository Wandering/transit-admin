package cn.thinkjoy.grab.common;

import cn.thinkjoy.grab.domain.Menu;
import cn.thinkjoy.grab.dto.MenuDto;
import org.apache.commons.lang.StringUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wdong on 15/7/31.
 */
public class MenuUtils{
    public static List<MenuDto> getTreeMenu(List<Menu> list)
    {

        Collections.sort(list, Collections.reverseOrder(new StrLenComp()));
        List<MenuDto> dtoList = new ArrayList<>();
        MenuDto menuDto = null;
        for(Menu menu:list)
        {
            menuDto = new MenuDto();
            if(menu.getParentId()==0||menu.getParentId()==null)
            {
                menuDto.setMenu(menu);
                menuDto.setMenuDtos(getList(list, menu.getId()));
                dtoList.add(menuDto);
            }
        }
        return dtoList;
    }
    private static List<MenuDto> getList(List<Menu> list,int parentId)
    {
        List<MenuDto> dtoList = new ArrayList<>();
        MenuDto menuDto = null;
        for(Menu menu:list)
        {
            menuDto = new MenuDto();
            if(menu.getParentId()==parentId)
            {
                menuDto.setMenu(menu);
                menuDto.setMenuDtos(getList(list,menu.getId()));
                dtoList.add(menuDto);
            }
        }
        return dtoList;
    }
    static class StrLenComp implements Comparator<Menu>{

        @Override
        public int compare(Menu o1, Menu o2) {
            return o1.getOrderNum()-o2.getOrderNum();
        }
    }
}
