package com.acsm.training.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.acsm.training.model.MemberBox;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.MemberBoxDao;
import com.acsm.training.model.Member;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;

@Repository
public class MemberBoxDaoImpl extends BaseDaoImpl<MemberBox> implements MemberBoxDao {

    @Override
    public List<MemberBox> queryListByMemberId(int memberId) {
        String hql = "from MemberBox where memberId=? order by isMain desc";
        return this.list(hql, memberId);
    }

    @Override
    public List<MemberBox> queryListByBoxId(int boxId) {
        String hql = "from MemberBox where boxId=?";
        return this.list(hql, boxId);
    }

    @Override
    public MemberBox queryByBoxIdAndMemberId(int boxId, int memberId) {
        String hql = "from MemberBox where boxId=? and memberId=?";
        Object[] obj = {boxId, memberId};
        return (MemberBox) this.Queryobject(hql, obj);
    }

    @Override
    public MemberBox queryInfoByBoxIdAndMemberId(int boxId, int memberId) {
        String hql = "select mb,m from MemberBox as mb, Member as m where mb.memberId=m.id and mb.boxId="+boxId+" and mb.memberId="+memberId;
        Session session = getSession();
        Query q = session.createQuery(hql);
        List list = q.list();
        List<MemberBox> memberBoxList = new ArrayList<>();
        if(list != null && list.size()>=0){
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object[] ob = (Object[])it.next();
                MemberBox memberBox = (MemberBox) ob[0];
                Member member = (Member) ob[1];
                memberBox.setMember(member);
                memberBoxList.add(memberBox);
            }
        }
        if(memberBoxList!=null && memberBoxList.size()>0){
            return memberBoxList.get(0);
        }
        return null;
    }

    @Override
    public List<MemberBox> queryAll() {
        String hql = "from MemberBox";
        return this.list(hql);
    }

    @Override
    public PageHelper<MemberBox> queryPageByConditions(Map<String, Object> conditions) {
        String hql="select mb,m from MemberBox as mb, Member as m where mb.memberId=m.id";
        String hqlCount="select count(*) from MemberBox as mb, Member as m where mb.memberId=m.id";
        int pageSize = 8;
        int pageIndex = 1;
        String keyword = "";
        List<Integer> boxIds = new ArrayList<Integer>();
        if(conditions.get("pageSize")!=null){
            pageSize = (Integer) conditions.get("pageSize");
        }
        if(conditions.get("pageIndex")!=null){
            pageIndex = (Integer) conditions.get("pageIndex");
        }
        if(conditions.get("keyword")!=null){
            keyword = (String) conditions.get("keyword");
        }
        if(conditions.get("boxId")!=null){
            boxIds = (List<Integer>) conditions.get("boxId");
        }
        if(StringUtils.isNotEmpty(keyword)){
            hql += " and  (m.name like '%"+keyword+"%' or m.phone like '%"+keyword+"%' or m.email like '%"+keyword+"%' or m.pinCode like '%"+keyword+"%')";
            hqlCount += " and  (m.name like '%"+keyword+"%' or m.phone like '%"+keyword+"%' or m.email like '%"+keyword+"%' or m.pinCode like '%"+keyword+"%')";;
        }
        hql+=" and m.type=1 and mb.boxId in:ids";
        hqlCount+=" and m.type=1 and mb.boxId in:ids";
        hql+=" order by mb.createTime desc";
        Session session = getSession();
        Query q =null;
        Query qCount = null;
        q = session.createQuery(hql);
        q.setParameterList("ids", boxIds);
        qCount = session.createQuery(hqlCount);
        qCount.setParameterList("ids", boxIds);
        //获取数据总条数
        int total = Integer.parseInt(qCount.uniqueResult().toString());
        q.setFirstResult((pageIndex-1)*pageSize);
        q.setMaxResults(pageSize);
        List list = q.list();
        List<MemberBox> memberBoxList = new ArrayList<>();
        if(list != null && list.size()>=0){
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object[] ob = (Object[])it.next();
                MemberBox memberBox = (MemberBox) ob[0];
                Member member = (Member) ob[1];
                memberBox.setMember(member);
                memberBoxList.add(memberBox);
            }
        }
        //数据列表
        PageHelper<MemberBox> page = new PageHelper<MemberBox>();
        page.setList(memberBoxList);
        page.setTotalRow(total);
        return page;
    }
}
