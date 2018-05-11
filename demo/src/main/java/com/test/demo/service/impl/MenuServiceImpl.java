package com.test.demo.service.impl;

import com.test.demo.entity.Menu;
import com.test.demo.mapper.MenuMapper;
import com.test.demo.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> selMenu() {

        return menuMapper.selMenu();
    }
}
