package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Role;
import com.acsm.training.model.Role;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月14日 下午9:51:41
 * */
public interface RoleDao extends BaseDao<Role>{
    public List<Role> queryListByBoxId(int boxId);

    public Role queryById(int id);

}
