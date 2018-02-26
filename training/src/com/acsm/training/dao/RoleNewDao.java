package com.acsm.training.dao;

import com.acsm.training.model.RoleNew;
import com.acsm.training.model.RoleNew;

import java.util.List;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月14日 下午9:51:41
 * */
public interface RoleNewDao extends BaseDao<RoleNew>{
    public List<RoleNew> queryListByBoxId(int boxId);

    public RoleNew queryById(int id);

    public RoleNew queryByName(String name, int boxId);
}
