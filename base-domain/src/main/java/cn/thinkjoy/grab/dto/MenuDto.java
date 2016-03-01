package cn.thinkjoy.grab.dto;

import cn.thinkjoy.grab.domain.Menu;

import java.util.List;

/**
 * Created by admin on 2016/2/29.
 */
public class MenuDto {
    private Menu menu;
    private List<MenuDto> menuDtos;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<MenuDto> getMenuDtos() {
        return menuDtos;
    }

    public void setMenuDtos(List<MenuDto> menuDtos) {
        this.menuDtos = menuDtos;
    }
}
