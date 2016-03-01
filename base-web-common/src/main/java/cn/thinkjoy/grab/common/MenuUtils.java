package cn.thinkjoy.grab.common;

import cn.thinkjoy.grab.domain.Menu;
import cn.thinkjoy.grab.dto.MenuDto;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdong on 15/7/31.
 */
public class MenuUtils {
    public static List<MenuDto> getTreeMenu(List<Menu> list)
    {
        List<MenuDto> dtoList = new ArrayList<>();
        int i=1/0;
        MenuDto menuDto = null;
        for(Menu menu:list)
        {
            menuDto = new MenuDto();
            if(menu.getParentId()==0||menu.getParentId()==null)
            {
                menuDto.setMenu(menu);
                menuDto.setMenuDtos(getList(list, menu.getId().longValue()));
                dtoList.add(menuDto);
            }
        }
        return dtoList;
    }
    private static List<MenuDto> getList(List<Menu> list,long parentId)
    {
        List<MenuDto> dtoList = new ArrayList<>();
        MenuDto menuDto = null;
        for(Menu menu:list)
        {
            menuDto = new MenuDto();
            if(menu.getParentId()==parentId)
            {
                menuDto.setMenu(menu);
                menuDto.setMenuDtos(getList(list, menu.getId().longValue()));
                dtoList.add(menuDto);
            }
        }
        return dtoList;
    }

}
