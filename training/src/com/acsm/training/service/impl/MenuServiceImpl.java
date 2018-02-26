package com.acsm.training.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsm.training.dao.MenuDao;
import com.acsm.training.model.Menu;
import com.acsm.training.service.MenuService;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> queryList() {
        return menuDao.queryList();
    }
}
