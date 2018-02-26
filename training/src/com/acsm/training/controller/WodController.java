package com.acsm.training.controller;/**
 * Created by lq on 2017/11/30.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acsm.training.model.User;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.BoxService;
import com.acsm.training.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.model.Box;
import com.acsm.training.model.Content;
import com.acsm.training.model.CourseType;
import com.acsm.training.model.Section;
import com.acsm.training.model.Setting;
import com.acsm.training.model.User;
import com.acsm.training.model.Wod;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.WodRecord;
import com.acsm.training.model.WodSection;
import com.acsm.training.model.WodTopRelation;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.basic.PagniationBottom;
import com.acsm.training.model.page.ContentContent;
import com.acsm.training.model.page.WodActionModel;
import com.acsm.training.model.page.WodModel;
import com.acsm.training.model.page.WodPageModel;
import com.acsm.training.model.page.WodSectionModel;
import com.acsm.training.model.page.wodList.WodContentPageModel;
import com.acsm.training.model.page.wodList.WodListPageModel;
import com.acsm.training.model.page.wodList.WodOnePageModel;
import com.acsm.training.model.page.wodList.WodSectionPageModel;
import com.acsm.training.service.BoxService;
import com.acsm.training.service.ContentService;
import com.acsm.training.service.CourseService;
import com.acsm.training.service.CourseTypeService;
import com.acsm.training.service.SectionService;
import com.acsm.training.service.SettingService;
import com.acsm.training.service.WodRecordService;
import com.acsm.training.service.WodService;
import com.acsm.training.util.ClassScheduleUtil;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.WodUtil;
import com.google.gson.Gson;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
@Controller
@RequestMapping("/wod")
public class WodController extends BaseController {

    @Autowired
    WodService wodService;

    @Autowired
    BoxService boxService;

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    SectionService sectionService;

    @Autowired
    ContentService contentService;

    @Autowired
    CourseService courseService;

    @Autowired
    SettingService settingService;
    @Autowired
    WodRecordService wodRecordService;
    @RequestMapping("/queryWodList")
    public String queryWodList(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
        long startTime=System.currentTimeMillis();
        User user = getUser(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("MMM", Locale.ENGLISH);

        User boss = getLoginUserOfBoss(user.getId());
        //查询wod设置默认显示几周
        Setting wodSetting = settingService.queryWodSetting(boss.getId());
        Integer weekType = 1;
        if(null != wodSetting && null != wodSetting.getWodShowWeek() ){
            weekType = wodSetting.getWodShowWeek();
        }
        //显示日期
        Date beginMondyDate = null;
        beginMondyDate = WodUtil.getDateWeekMonday(new Date());
        String beginDate = sdf.format(beginMondyDate);
        String endDate = sdf.format(WodUtil.getLastDate(beginMondyDate,weekType));
        //馆
        Integer boxId = null;
        if(request.getParameter("boxId") != null && "".equals(request.getParameter("boxId"))){
            boxId = Integer.valueOf(request.getParameter("boxId") );
        }
        List<Box> boxList= getBoxListOfUser(request);
        if(null == boxId && null != boxList && boxList.size() > 0){
            boxId = boxList.get(0).getId();
        }
        //课程类型
        Integer courseTypeId = null;
        if(request.getParameter("courseTypeId") != null && "".equals(request.getParameter("courseTypeId"))){
            courseTypeId = Integer.valueOf(request.getParameter("courseTypeId") );
        }
        List<CourseType> courseTypeList= courseTypeService.queryCourseTypListOfUser(boss.getId());
        if(null == courseTypeId && null != courseTypeList && courseTypeList.size() > 0){
            courseTypeId = courseTypeList.get(0).getId();
        }
//        Map<String,Object> param = new HashMap<>();
//        param.put("beginDate",beginDate);
//        param.put("endDate",endDate);
//        param.put("boxId",boxId);
//        param.put("courseId",courseTypeId);
//        param.put("weekType", weekType);
//        List<WodModel> wodModelList = wodService.queryWodList(param);
        request.setAttribute("weekTypeMap",getWodWeekType());
        request.setAttribute("weekType",weekType);
        request.setAttribute("boxId",boxId);
        request.setAttribute("courseTypeId",courseTypeId);
        request.setAttribute("beginDate", beginDate);
        request.setAttribute("endDate", endDate);
        //request.setAttribute("wodModelList",wodModelList);
        request.setAttribute("boxList",boxList);
        request.setAttribute("courseTypeList", courseTypeList);
        request.setAttribute("today",sdf.format(new Date()));

        Date dateBegin = sdf.parse(beginDate);
        Date dateEnd = sdf.parse(endDate);
        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(dateBegin);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(dateEnd);
        String title = null;
        if(beginCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)){
            title = df.format(dateBegin) + " " + beginCal.get(Calendar.DAY_OF_MONTH) + "-"
                    + df.format(dateEnd) + " " + endCal.get(Calendar.DAY_OF_MONTH) +
                    "," + beginCal.get(Calendar.YEAR);
        }else{
            title = df.format(dateBegin) + " " + beginCal.get(Calendar.DAY_OF_MONTH) +  "," + beginCal.get(Calendar.YEAR)
                    + "-"
                    + df.format(dateEnd) + " " + endCal.get(Calendar.DAY_OF_MONTH) +  "," + endCal.get(Calendar.YEAR);
        }

        request.setAttribute("sessionBoxId",session.getAttribute("wod_list_box"));
        request.setAttribute("sessionCourseType",session.getAttribute("wod_list_courseTypeId"));
//        request.setAttribute("title",title);
        request.setAttribute("title",beginDate.replace("-","/")+"-"+endDate.replace("-","/"));
        //执行方法
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间："+excTime+"s");
        return "/wod/wodList";

    }

    @RequestMapping(value="/searchWodList",method= RequestMethod.GET)
    @ResponseBody
    public String searchWodList(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{

        User user = getUser(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("MMM", Locale.ENGLISH);
        String beginDate = ServletRequestUtils.getStringParameter(request, "beginDate", null);
        String endDate = ServletRequestUtils.getStringParameter(request, "endDate", null);
        Integer weekType = ServletRequestUtils.getIntParameter(request, "weekType", 1);
        Integer lastOrNextWeek = ServletRequestUtils.getIntParameter(request,"lastOrNextWeek",0);
        Integer boxId = ServletRequestUtils.getIntParameter(request,"boxId",0);
        Integer courseTypeId = ServletRequestUtils.getIntParameter(request,"courseType",0);
        //用于显示
        session.setAttribute("wod_list_box",boxId);
        session.setAttribute("wod_list_courseTypeId",courseTypeId);
        if(lastOrNextWeek == -1){//上一周
            beginDate = sdf.format(WodUtil.getLastWeekBeginDate(sdf.parse(beginDate)));
        }else if(lastOrNextWeek == 1){//下一周
            beginDate = sdf.format(WodUtil.getNextWeekBeginDate(sdf.parse(beginDate)));
        }else if(lastOrNextWeek == 0){//回到今天
            beginDate = sdf.format(WodUtil.getDateWeekMonday(new Date()));
        }

        Date beginMondyDate = WodUtil.getDateWeekMonday(sdf.parse(beginDate));
        endDate = sdf.format(WodUtil.getLastDate(beginMondyDate,weekType));

        Map<String,Object> param = new HashMap<>();
        param.put("beginDate",beginDate);
        param.put("endDate",endDate);
        param.put("boxId",boxId == 0 ? null : boxId);
        param.put("courseId",courseTypeId == 0 ? null : courseTypeId);
        param.put("weekType", weekType);
        List<WodModel> wodModelList = wodService.queryWodList(param);
        //拼接返回的json
        List<WodOnePageModel> wodList = new ArrayList<>();
        for(WodModel wodModel:wodModelList){
            WodOnePageModel wod = new WodOnePageModel();
            wod.setDay(wodModel.getDay());
            wod.setIndex(wodModel.getIndex());
            wod.setDate(wodModel.getDate());
            wod.setWodId(wodModel.getWod() == null ? null : wodModel.getWod().getId() );
            wod.setWodName(wodModel.getWod() == null ? null : wodModel.getWod().getName());
            List<WodSectionPageModel> wodSectionPageModelList = new ArrayList<>();
            if(null != wodModel.getWod() && null != wodModel.getSectionModelList()){
                for(WodSectionModel wodSectionModel : wodModel.getSectionModelList()){
                    WodSectionPageModel section = new WodSectionPageModel();
                    section.setWodSectionId(wodSectionModel.getWodSection().getWodSectionId());
                    section.setWodSectionName(wodSectionModel.getWodSection().getSection() == null ? "临时" : wodSectionModel.getWodSection().getSection().getTitle());
                    section.setComment(wodSectionModel.getWodSection().getRemark());
                    section.setType(wodSectionModel.getWodSection().getType());
                    List<WodContentPageModel> wodContentPageModelList= new ArrayList<>();
                    if(wodSectionModel.getWodContentList() != null){
                        for(WodContent wodContentModel : wodSectionModel.getWodContentList()){
                            WodContentPageModel wodContent = new WodContentPageModel();
                            wodContent.setWodContentId(wodContentModel.getWodContentId());
                            int contentType = wodContentModel.getContentType();
                            wodContent.setWodContentName(wodContentModel.getContentTitle());
                            if(contentType < 6){
                                wodContent.setWodContentName(wodContentModel.getContentEntity().getName());
                            }
                            wodContent.setDescript(wodContentModel.getDescript());
                            wodContent.setComment(wodContentModel.getRemark());
                            wodContent.setWodContentType(contentType);
                            wodContent.setSeriaNum(wodContentModel.getSeriaNum());
                            wodContent.setRepsScheme(wodContentModel.getRepsScheme());
                            wodContent.setContentRecordTypeName(wodContentModel.getContentRecordTypeName());
                            wodContentPageModelList.add(wodContent);
                        }
                    }
                    section.setWodContentPageModelList(wodContentPageModelList);
                    wodSectionPageModelList.add(section);
                }
            }
            wod.setSectionModelList(wodSectionPageModelList);
            wodList.add(wod);
        }
        WodListPageModel wodListPageModel = new WodListPageModel();
        wodListPageModel.setWodPageModelList(wodList);
        wodListPageModel.setBeginDate(beginDate);
        wodListPageModel.setEndDate(endDate);
        wodListPageModel.setBoxId(boxId);
        wodListPageModel.setCourseTypeId(courseTypeId);
        wodListPageModel.setWeekType(weekType);
        wodListPageModel.setLastOrNextWeek(0);
        wodListPageModel.setToday(sdf.format(new Date()));

        //title
        Date dateBegin = sdf.parse(beginDate);
        Date dateEnd = sdf.parse(endDate);
        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(dateBegin);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(dateEnd);
        String title = null;
        if(beginCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)){
            title = df.format(dateBegin) + " " + beginCal.get(Calendar.DAY_OF_MONTH) + "-"
                    + df.format(dateEnd) + " " + endCal.get(Calendar.DAY_OF_MONTH) +
                    "," + beginCal.get(Calendar.YEAR);
        }else{
            title = df.format(dateBegin) + " " + beginCal.get(Calendar.DAY_OF_MONTH) +  "," + beginCal.get(Calendar.YEAR)
                    + "-"
                    + df.format(dateEnd) + " " + endCal.get(Calendar.DAY_OF_MONTH) +  "," + endCal.get(Calendar.YEAR);
        }
//        wodListPageModel.setTitle(title);
        wodListPageModel.setTitle(beginDate.replace("-","/")+"-"+endDate.replace("-","/"));
        Gson gson = new Gson();
        return gson.toJson(wodListPageModel);
    }

    /**
     * 删除wod
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/previewWod",method= RequestMethod.POST)
    @ResponseBody
    public String previewWod(HttpServletRequest request, HttpServletResponse response){
        int wodId = ServletRequestUtils.getIntParameter(request,"wodId",0);
        WodModel wodModel = wodService.queryWodModelByWodId(wodId);
        return new Gson().toJson(wodModel);
    }

    /**
     * 删除wod
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/deleteWod",method= RequestMethod.POST)
    @ResponseBody
    public String deleteWod(HttpServletRequest request, HttpServletResponse response){
        int wodId = Integer.valueOf(request.getParameter("wodId"));
        wodService.deleteWod(wodId);
        List<WodTopRelation> wodTopRelationList = wodService.queryWodTopRelations(wodId);
        JSONObject json = new JSONObject();
        int boxId = wodTopRelationList.get(0).getBoxId();
        int courseTypeId = wodTopRelationList.get(0).getCourseId();
        json.put("boxId",boxId);
        json.put("courseTypeId",courseTypeId);
        return json.toString();
    }
    /**
     * 编辑wod
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toEditWodPage")
    public String toAddWodPage(HttpServletRequest request, HttpServletResponse response){
        Integer wodId = Integer.parseInt(request.getParameter("wodId"));
        User user = getUser(request);
        User boss = getLoginUserOfBoss(user.getId());
        //查询wod信息
        Wod wod = wodService.queryWodById(wodId);
        request.setAttribute("wod",wod);
        Date date = wod.getwDate();
        //wod设置
        Setting wodSetting = settingService.queryWodSetting(boss.getId());
        Date showDate = new Date();
        if(null != wodSetting && null != wodSetting.getAppShowDay() && 0 != wodSetting.getAppShowDay()){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH,0-wodSetting.getAppShowDay());
            showDate = calendar.getTime();
        }else{
            showDate = wod.getwDate();
        }

        List<CourseType> courseTypeList = courseTypeService.queryCourseTypListOfUser(boss.getId());
        request.setAttribute("courseTypes", courseTypeList);
        List<Box> boxList = getBoxListOfUser(request);
        request.setAttribute("boxs",boxList);

        //查询wod所属的课程类型
        List<WodTopRelation> wodTopRelationList = wodService.queryWodTopRelations(wodId);
        int courseTypeId = wodTopRelationList.get(0).getCourseId();
        request.setAttribute("courseType",courseTypeId);
        request.setAttribute("courseTypeName",courseTypeService.queryById(courseTypeId).getName());
        request.setAttribute("wodRelation",wodTopRelationList);
        String boxName = "";
        for(WodTopRelation wodTopRelation:wodTopRelationList){
            int boxId = wodTopRelation.getBoxId();
            boxName += boxService.queryById(boxId).getName()+"，";
        }
        request.setAttribute("boxName",boxName.substring(0,boxName.length()-1));
        List<Section> sectionList = sectionService.querySectionOfBoss(boss.getId());
        List<Content> contentList = contentService.queryListOfBoss(boss.getId());
        //页面信息
        WodModel wodModel = wodService.queryWodModelByWodId(wodId);
        request.setAttribute("showDate",showDate);
        request.setAttribute("wodModel",wodModel);
        request.setAttribute("date",date);
        request.setAttribute("wodId",wodId);
        request.setAttribute("sectionList",sectionList);
        request.setAttribute("generalContentList",contentList);
        request.setAttribute("customizeContentTypeMap",WodUtil.customizeContentTypeMap);
        return "/wod/wodEdit";
    }

    /**
     * 添加section
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/toAddSection",method= RequestMethod.POST)
    @ResponseBody
    public int toAddSection(HttpServletRequest request, HttpServletResponse response){
        int sectionId = Integer.valueOf(request.getParameter("sectionId"));

        Integer wodId = Integer.valueOf(request.getParameter("wodId"));
        List<WodSection> wodSectionList = wodService.queryWodScetionOfWod(wodId);
        WodSection wodSection = new WodSection();
        wodSection.setIsDeleted(0);
        wodSection.setWodId(wodId);
        wodSection.settIndex(1);
        wodSection.setSectionId(sectionId);
        if(null != wodSectionList && wodSectionList.size() > 0){
            wodSection.settIndex(wodSectionList.get(0).gettIndex() + 1);
        }
        wodSection.setType(1);
        wodService.addWodSection(wodSection);
        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodId);
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return wodSection.getWodSectionId();
    }

    /**
     * 编辑section
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/toEditSection",method= RequestMethod.POST)
    @ResponseBody
    public JSONObject toEditSection(HttpServletRequest request, HttpServletResponse response){
        int wodSectionId = Integer.valueOf(request.getParameter("wodSectionId"));
        WodSection wodSection = wodService.queryWodSectionById(wodSectionId);
        JSONObject json = new JSONObject();
        json.put("sectionId",wodSection.getSection().getSectionId());
        json.put("remark",wodSection.getRemark()==null ? "":wodSection.getRemark());
        return json;
    }

    /**
     * 编辑section
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/editSection",method= RequestMethod.POST)
    @ResponseBody
    public String editSection(HttpServletRequest request, HttpServletResponse response){
        int wodSectionId = ServletRequestUtils.getIntParameter(request, "wodSectionId", 0);
        int sectionId = ServletRequestUtils.getIntParameter(request, "sectionId", 0);
        String remark = ServletRequestUtils.getStringParameter(request, "remark", "");
        WodSection wodSection = wodService.queryWodSectionById(wodSectionId);
        wodSection.setRemark("".equals(remark) ? null : remark);
        Section section = new Section();
        section.setSectionId(sectionId);
        wodSection.setSectionId(sectionId);
        wodSection.setSection(section);
        wodService.updateWodSection(wodSection);
        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodSection.getWodId());
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return wodSection.getSection().getTitle();
    }

    /**
     * 删除section
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/delSection",method= RequestMethod.POST)
    @ResponseBody
    public String delSection(HttpServletRequest request, HttpServletResponse response){
        int wodSectionId = ServletRequestUtils.getIntParameter(request, "wodSectionId", 0);
        wodService.deleteWodSection(wodSectionId);
        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodService.queryWodSectionById(wodSectionId).getWodId());
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return "success";
    }

    /**
     * 添加常规content
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/toAddGerenalContent",method= RequestMethod.POST)
    @ResponseBody
    public String toAddGerenalContent(HttpServletRequest request, HttpServletResponse response){

        Integer contentId = ServletRequestUtils.getIntParameter(request, "contentId", -1);
        Integer wodId = ServletRequestUtils.getIntParameter(request, "wodId", -1);
        Integer wodSectionId = ServletRequestUtils.getIntParameter(request, "wodSectionId", -1);
        WodContent wodContent = new WodContent();
        wodContent.setWodId(wodId);
        wodContent.setWodSectionId(wodSectionId);
        WodContent newWodContent = wodService.addgeneralWodContent(wodContent,contentId);
        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodId);
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return new Gson().toJson(newWodContent);
    }


    /**
     *选择metcon和popular
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/selectContentMetcon",method= RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectContentMetcon(HttpServletRequest request, HttpServletResponse response){
        Integer contentId = ServletRequestUtils.getIntParameter(request,"contentId",0);
        Content content = contentService.queryContentById(contentId);
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSONObject.parseObject(new Gson().toJson(content).toString());
        jsonObject.put("recordName",queryRecordName(content.getRecordType()));
        return jsonObject.toString();
    }

    /**
     *查询wodContent
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/toEditContent",method= RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toEditContent(HttpServletRequest request, HttpServletResponse response){
        Integer wodContentId = Integer.valueOf(request.getParameter("wodContentId"));
        WodContent newWodContent = wodService.queryWodContentById(wodContentId);
        return new Gson().toJson(newWodContent);
    }
    /**
     *编辑保存content
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/updateContent",method= RequestMethod.POST)
    @ResponseBody()
    public String updateContent(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new Gson();
        int wodContentId = ServletRequestUtils.getIntParameter(request, "wodContentId", 0);
        Integer contentId = ServletRequestUtils.getIntParameter(request, "contentId", 0);
        String description = ServletRequestUtils.getStringParameter(request, "description", null);
        String comment = ServletRequestUtils.getStringParameter(request,"comment",null);
        String seriaNum = ServletRequestUtils.getStringParameter(request, "seriaNum", "");
        String repsScheme = ServletRequestUtils.getStringParameter(request, "repsScheme", null);
        WodContent wodContent = wodService.queryWodContentById(wodContentId);
        if(wodContent.getContentType() == 1){
            wodContent.setRemark(comment);
            wodContent.setContentId(contentId);
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
        }else if(wodContent.getContentType() == 2){//体操只有sets
            wodContent.setRepsScheme(repsScheme);
            Integer sets = ServletRequestUtils.getIntParameter(request, "sets", 1);

            ContentContent content = new ContentContent();
            content.setSets(sets);
            wodContent.setContent(gson.toJson(content));
            wodContent.setRemark(comment);
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
            wodContent.setContentId(contentId);
        }else if(wodContent.getContentType() == 3){
            wodContent.setContentId(contentId);
            wodContent.setRepsScheme(repsScheme);
            Integer sets = ServletRequestUtils.getIntParameter(request, "sets", 1);
            Integer reps = ServletRequestUtils.getIntParameter(request, "reps", 1);
            Integer unit = ServletRequestUtils.getIntParameter(request, "unit", 1);
            ContentContent content = new ContentContent();
            content.setSets(sets);
            content.setResps(reps);
            content.setUnit(unit);
            wodContent.setContent(gson.toJson(content));
            wodContent.setRemark(comment);
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
        }else if(wodContent.getContentType() == 5 || wodContent.getContentType() == 4 ){
            wodContent.setContentId(contentId);
            Integer isRx = ServletRequestUtils.getIntParameter(request, "isRx", 0);
            ContentContent content = new ContentContent();
            content.setIsRx(isRx);
            wodContent.setContent(gson.toJson(content));
            wodContent.setRemark(comment);
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
        }else if(wodContent.getContentType() == 6){
            wodContent.setRemark(comment);
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
            wodContent.setDescript(description);
        }else if(wodContent.getContentType() == 7){
            String contentSaveTitle = ServletRequestUtils.getStringParameter(request, "contentTitle", null);
            wodContent.setRemark(comment);
            Integer sets = ServletRequestUtils.getIntParameter(request, "sets", 1);
            ContentContent content = new ContentContent();
            content.setSets(sets);
            wodContent.setRepsScheme(repsScheme);
            wodContent.setDescript(description);
            wodContent.setContent(gson.toJson(content));
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
            wodContent.setContentTitle(contentSaveTitle);
        }else if(wodContent.getContentType() == 8){
            String contentSaveTitle = ServletRequestUtils.getStringParameter(request, "contentTitle", null);
            wodContent.setRemark(comment);
            Integer sets = ServletRequestUtils.getIntParameter(request, "sets", 1);
            Integer reps = ServletRequestUtils.getIntParameter(request, "reps", 1);
            ContentContent content = new ContentContent();
            content.setSets(sets);
            content.setResps(reps);
            wodContent.setRepsScheme(repsScheme);
            wodContent.setDescript(description);
            wodContent.setContent(gson.toJson(content));
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
            wodContent.setContentTitle(contentSaveTitle);
        }else if(wodContent.getContentType() == 9){
            String contentSaveTitle = ServletRequestUtils.getStringParameter(request, "contentTitle", null);
            wodContent.setRemark(comment);
            Integer metconType = ServletRequestUtils.getIntParameter(request, "metconType", 1);
            ContentContent content = new ContentContent();
            content.setMetconType(metconType);
            if(metconType == 10){
                content.setUnit( ServletRequestUtils.getIntParameter(request, "unit", 1));
                content.setIsRx(ServletRequestUtils.getIntParameter(request, "isRx", 1));
            }else if(metconType == 5){
                content.setUnit( ServletRequestUtils.getIntParameter(request, "unit", 1));
                content.setIsRx(ServletRequestUtils.getIntParameter(request, "isRx", 1));
                Integer measures = ServletRequestUtils.getIntParameter(request, "measure", 1);
                content.setMeasures(measures);
                if(measures == 3 || measures == 4){
                    content.setIn(ServletRequestUtils.getIntParameter(request, "wUnit", 1));
                }
            }else if(metconType == 6){
                content.setUnit(ServletRequestUtils.getIntParameter(request, "unit", 1));

            }else{
                content.setIsRx(ServletRequestUtils.getIntParameter(request, "isRx", 1));
            }
            wodContent.setDescript(description);
            wodContent.setContent(gson.toJson(content));
            wodContent.setSeriaNum("".equals(seriaNum) ? null : seriaNum);
        }
        wodService.updateWodContent(wodContent);

        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodContent.getWodId());
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return gson.toJson(wodContent);
    }

    /**
     * 删除content
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/deleteContent",method= RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteContent(HttpServletRequest request, HttpServletResponse response){
        Integer wodContentId = Integer.valueOf(request.getParameter("wodContentId"));
        wodService.deleteWodContent(wodContentId);
        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodService.queryWodContentById(wodContentId).getWodId());
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return "success";
    }
    @RequestMapping(value="/toAddCustomContent",method= RequestMethod.POST)
    @ResponseBody
    public String toAddCustomContent(HttpServletRequest request, HttpServletResponse response){
        User user = getUser(request);
        Integer wodId = Integer.valueOf(request.getParameter("wodId"));
        Integer wodSectionId = Integer.valueOf(request.getParameter("wodSectionId"));
        Integer wodContentType = Integer.valueOf(request.getParameter("custonContentType"));
        //降序查询wod_section下的wod_content，用来排序
        //List<WodContent> wodContentList = wodService.queryWodContentOfSectionDesc(wodSectionId,wodId);
        WodContent wodContent = new WodContent();
        wodContent.setWodId(wodId);
        wodContent.setWodSectionId(wodSectionId);
        WodContent newWodContent = wodService.addCustonmWodContent(wodContent, user, wodContentType);

        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodId);
        wodRecord.setType(2);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        return new Gson().toJson(newWodContent);
    }

    /**
     * wod_contentp排序
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/contentSort",method= RequestMethod.POST)
    @ResponseBody
    public String contentSort(HttpServletRequest request,HttpServletResponse response){
        Integer wodContentId = Integer.valueOf(request.getParameter("wodContentId"));
        Integer oldWodSectionId = Integer.valueOf(request.getParameter("oldWodSectionId"));
        Integer newWodSectionId = Integer.valueOf(request.getParameter("newWodSectionId"));
        Integer preWodContentId = null;
        if(!request.getParameter("preWodContentId").equals("")){
            preWodContentId = Integer.valueOf(request.getParameter("preWodContentId"));
        }
        wodService.updateSortWodContent(wodContentId,oldWodSectionId,newWodSectionId,preWodContentId);
        return "success";
    }


    /**
     * 拖动section排序
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/sectionSort",method= RequestMethod.POST)
    @ResponseBody
    public String sectionSort(HttpServletRequest request,HttpServletResponse response){
        int newPreSectionId = Integer.valueOf(request.getParameter("newPreSectionId"));
        String newContentIds = request.getParameter("newContentIds");
        int wodSectionId = Integer.valueOf(request.getParameter("wodSectionId"));
        wodService.updateSortWodSection(wodSectionId,newPreSectionId,newContentIds);
        return "success";
    }
    public Map<Integer,String> getWodWeekType(){
        Map<Integer,String> map = new HashMap<>();
        map.put(1, "一周");
        map.put(2,"二周");
        map.put(3,"三周");
        map.put(4,"四周");
        return map;
    }

    /**
     *@Author : RedCometJ
     *@Description :
     *@params  [request, model]
     *@return java.lang.String
     *@Date : 2017/12/1
     */
    @RequestMapping("/initTraining")
    public String addTraining(HttpServletRequest request,  Model model) throws Exception{
        User boss = getLoginUserOfBoss(getUser(request).getId());
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2  = new SimpleDateFormat("yyyy/MM/dd");
        List<CourseType> courseTypeList = courseTypeService.queryCourseTypListOfUser(super.getUser(request).getId());
        Integer courseTypeId = Integer.parseInt(request.getParameter("courseTypeId"));
        String wodTime = request.getParameter("wodTime");
        Setting wodSetting = settingService.queryWodSetting(boss.getId());
        int time = 2;
        int munitus = 0;
        int day = 0;
        if(null != wodSetting && wodSetting.getAppShowDay() != null){
            time = wodSetting.getAppShowHour();
            munitus = wodSetting.getAppShowMinus();
            day = wodSetting.getAppShowDay();
        }
        Date date = sdf.parse(wodTime);
        model.addAttribute("wodTime",sdf2.format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,0-day);
        Date newShowTime = calendar.getTime();
        model.addAttribute("showTime",sdf2.format(newShowTime));
        model.addAttribute("courseTypes",courseTypeList);
        model.addAttribute("courseTypeId",courseTypeId);
        model.addAttribute("time",time);
        model.addAttribute("munitus",munitus);
        List<Box> boxList = boxService.queryBoxListOfUser(super.getUser(request).getId());
        model.addAttribute("boxs",boxList);
        return "/wod/addTraining";
    }
    /**
     *@Author : RedCometJ
     *@Description : wod基本信息保存
     *@params  [request, response]
     *@return java.lang.String
     *@Date : 2017/12/1
     */
    @RequestMapping(value="/saveTraining",method= RequestMethod.POST)
    @ResponseBody
    public Integer addTraining(HttpServletRequest request,HttpServletResponse response) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String wodName = request.getParameter("wodName");
        String remark = request.getParameter("remark");
        String day = request.getParameter("day");
        String hours = request.getParameter("hours");
        String minus = request.getParameter("minus");
        String courseTypeId = request.getParameter("courseTypeId");
        String boxIds = request.getParameter("boxsIds");
        Wod wod = new Wod();
        wod.setName(wodName);
        wod.setIsDeleted(0);
        wod.setwDate(sdf.parse(day));
        wod.setRemark(remark);
        wod.setAppShowHour(Integer.valueOf(hours));
        wod.setAppShowMinuts(Integer.valueOf(minus));
        List<Integer> boxIdList = new ArrayList<Integer>();
        if(boxIds.length()>0){
            String[] boxsIdsStr = boxIds.substring(0,boxIds.length()-1).replaceAll("on,","").split(",");
            for (int i = 0; i < boxsIdsStr.length; i++) {
                boxIdList.add(Integer.valueOf(boxsIdsStr[i]));
            }
        }
        Integer wodId = wodService.addWod(wod, Integer.valueOf(courseTypeId),boxIdList);
        WodRecord wodRecord = new WodRecord();
        wodRecord.setWodId(wodId);
        wodRecord.setType(1);
        wodRecord.setInsertTime(new Date());
        wodRecord.setUserId(getUser(request).getId());
        wodRecordService.addWodRecord(wodRecord);
        /**
         * 自动创建一个没有意义的wod_section,用于排序
         */
        WodSection nullSection = new WodSection();
        nullSection.setWodId(wodId);
        nullSection.setSectionId(0);
        nullSection.settIndex(1);
        nullSection.setIsDeleted(0);
        nullSection.setInsertTime(new Date());
        nullSection.setType(0);
        wodService.addWodSection(nullSection);
        return wodId;
    }

    /**
     * 进入wod编辑页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/toEditWoding",method= RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toEditWoding(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User boss = getLoginUserOfBoss(getUser(request).getId());
        Integer wodId = Integer.valueOf(request.getParameter("wodId"));
        String showDate = request.getParameter("showDate");
        WodPageModel wodPageModel = wodService.queryWodPageModel(wodId);
        if(wodPageModel.getTime() == null && wodPageModel.getMinitus() == null){
            //wod设置
            Setting wodSetting = settingService.queryWodSetting(boss.getId());
            if(null != wodSetting){
                wodPageModel.setTime(wodSetting.getAppShowHour());
                wodPageModel.setMinitus(wodSetting.getAppShowMinus());
            }else{
                wodPageModel.setTime(2);
                wodPageModel.setMinitus(1);
            }
        }
        if(wodPageModel.getTime() < 10){
            showDate += " 0" + wodPageModel.getTime();
        }else {
            showDate += " " + wodPageModel.getTime();
        }
        if(wodPageModel.getMinitus() == 0){
            showDate += ":00";
        }else if(wodPageModel.getMinitus() == 1){
            showDate += ":15";
        }else if(wodPageModel.getMinitus() == 2){
            showDate += ":30";
        }else if(wodPageModel.getMinitus() == 3){
            showDate += ":45";
        }
        boolean isCanEdit = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if(new Date().getTime() - sdf.parse(showDate).getTime() > 0){
            isCanEdit = false;
        }
        wodPageModel.setIsCanEdit(isCanEdit);
        return new Gson().toJson(wodPageModel);
    }

    /**
     * 查询训练计划修改记录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/queryWodRecord",method= RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryWodRecord(HttpServletRequest request, HttpServletResponse response){
        Integer wodId = ServletRequestUtils.getIntParameter(request,"wodId",-1);
        List<WodRecord> resultList = new ArrayList<>();
        //取创建的
        List<WodRecord> wodRecordList2 = wodRecordService.queryRecordOfWord(wodId,2);
        if(null != wodRecordList2 && wodRecordList2.size() > 0){
            resultList.add(wodRecordList2.get(0));
        }
        //取最后一次编辑的
        List<WodRecord> wodRecordList1 = wodRecordService.queryRecordOfWord(wodId,1);
        if(null != wodRecordList1 && wodRecordList1.size() > 1){
            resultList.add(wodRecordList1.get(0));
        }
        return new Gson().toJson(resultList);
    }
    /**
     * 编辑保存
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/updateWoding",method= RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateWoding(HttpServletRequest request, HttpServletResponse response){
        int wodId = Integer.parseInt(request.getParameter("wodId"));
        List<WodTopRelation> wodTopRelationList = wodService.queryWodTopRelations(wodId);
        for(WodTopRelation wodTopRelation:wodTopRelationList){
            wodTopRelation.setIsDeleted(-1);
            wodService.deletedWodTopRelation(wodTopRelation);
        }
        String wodName = request.getParameter("wodName");
//        String remark = request.getParameter("remark");
        String courseTypeId = request.getParameter("courseTypeId");
        String boxIds = request.getParameter("boxsIds");
        String remark = request.getParameter("comment");
        Integer hours = Integer.parseInt(request.getParameter("hours"));
        Integer minitus = Integer.parseInt(request.getParameter("minitus"));
        Wod wod = wodService.queryWodById(wodId);
        wod.setName(wodName);
        wod.setIsDeleted(0);
        wod.setUserId(super.getUser(request).getId());
        wod.setAppShowHour(hours);
        wod.setAppShowMinuts(minitus);
        wod.setRemark(remark);
        List<Integer> boxIdList = new ArrayList<Integer>();
        if(boxIds.length()>0){
            String[] boxsIdsStr = boxIds.substring(0,boxIds.length()-1).replaceAll("on,","").split(",");
            for (int i = 0; i < boxsIdsStr.length; i++) {
                boxIdList.add(Integer.valueOf(boxsIdsStr[i]));
            }
        }
        wodService.updateWodTopRelation(wod, Integer.valueOf(courseTypeId),boxIdList);
        return "success";
    }
    /**
     *@Author : RedCometJ
     *@Description : 动作元素初始化页面
     *@params  [request, response]
     *@return java.lang.String
     *@Date : 2017/12/4
     */
    @RequestMapping(value="/actions",method=RequestMethod.GET)
    public String initActions(HttpServletRequest request,HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("currentUser");
        Integer searchType = Integer.valueOf(request.getParameter("searchType").toString());
        String keyword = "";
        PageHelper<WodActionModel> pager = getSectionData(10, 1,getLoginUserOfBoss(super.getUser(request).getId()).getId(),searchType,keyword);
//      request.setAttribute("sectionList",sectionService.querySectionListChange(user.getId(),searchType),);
        request.setAttribute("pager",pager);
        PagniationBottom pagerBottom = new PagniationBottom(pager);
        request.setAttribute("pagerBottom", pagerBottom);
        request.setAttribute("pageCount",pager.getTotalRow() == 0 ? 1 : pager.getTotalRow());
        return "/wod/actions";
    }

    /**
     *@Author : RedCometJ
     *@Description : section列表查询
     *@params  [request, model]
     *@return java.lang.String
     *@Date : 2017/12/4
     */
    @RequestMapping(value="/sectionSearch",method=RequestMethod.POST)
    @ResponseBody
    public String sectionSearch(HttpServletRequest request,  Model model){
        Integer searchType = Integer.valueOf(request.getParameter("searchType").toString());
        String keyword = request.getParameter("keyword").toString();
        Integer pageIndex = null != request.getParameter("pagess") && !"".equals(request.getParameter("pagess")) ? Integer.valueOf(request.getParameter("pagess").toString()) : 1;
        PageHelper<WodActionModel> pager = getSectionData(10, pageIndex,getLoginUserOfBoss(super.getUser(request).getId()).getId(),searchType,keyword);
        model.addAttribute("pager", pager);
//        PagniationBottom pagerBottom = new PagniationBottom(pager);
//        request.setAttribute("pagerBottom", pagerBottom);
        return JSON.toJSONString(pager);
    }
    /**
    *@Author : RedCometJ
    *@Description : Section数据组装
    *@params
    *@return
    *@Date : 2017/12/4
    */
    private PageHelper<WodActionModel> getSectionData(int pageSize, int pageIndex, Integer userId, Integer searchType,String keyword) {
        PageHelper<WodActionModel> pager = sectionService.querySectionListChange(userId,searchType,pageSize,pageIndex,keyword);
        return pager;
    }

    /**
     *@Author : RedCometJ
     *@Description : content列表
     *@params  [request, model]
     *@return java.lang.String
     *@Date : 2017/12/4
     */
    @RequestMapping(value="/contentSearch",method=RequestMethod.POST)
    @ResponseBody
    public String contentSearch(HttpServletRequest request,  Model model){
        Integer searchType = Integer.valueOf(request.getParameter("searchType").toString());
        Integer contentType = Integer.valueOf(request.getParameter("contentType").toString());
        String keyword = request.getParameter("keyword").toString();
        Integer pageIndex = null != request.getParameter("pagess") && !"".equals(request.getParameter("pagess")) ? Integer.valueOf(request.getParameter("pagess").toString()) : 1;
        PageHelper<WodActionModel> pager = getContentData(10, pageIndex,getLoginUserOfBoss(super.getUser(request).getId()).getId(),searchType,contentType,keyword);
        model.addAttribute("pager", pager);
        PagniationBottom pagerBottom = new PagniationBottom(pager);
        request.setAttribute("pagerBottom", pagerBottom);
        return JSON.toJSONString(pager);
    }

    /**
     *@Author : RedCometJ
     *@Description : Content数据组装
     *@params
     *@return
     *@Date : 2017/12/4
     */
    private PageHelper<WodActionModel> getContentData(int pageSize, int pageIndex, Integer userId, Integer searchType,Integer contentType,String keyword) {
        PageHelper<WodActionModel> pager = sectionService.queryContentListChange(userId,searchType,pageSize,pageIndex,contentType,keyword);
        return pager;
    }

    @RequestMapping(value="/sectionEdit",method=RequestMethod.POST)
    @ResponseBody
    public String updateSection(HttpServletRequest request){
        Integer sectionId = Integer.valueOf(request.getParameter("sectionId"));
        String title = request.getParameter("title");
        sectionService.updateSection(sectionId,title);
        return "success";
    }

    @RequestMapping(value="/sectionAdd",method=RequestMethod.POST)
    @ResponseBody
    public String addSection(HttpServletRequest request){
        String title = request.getParameter("title");

        sectionService.addSection(title, getLoginUserOfBoss(super.getUser(request).getId()).getId());
        return "success";
    }

    /**
     *@Author : RedCometJ
     *@Description :  content添加
     *@params  [request]
     *@return java.lang.String
     *@Date : 2017/12/5
     */
    @RequestMapping(value="/contentAdd",method=RequestMethod.POST)
    @ResponseBody
    public String addContent(HttpServletRequest request){
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer contentType = Integer.valueOf(request.getParameter("contentType").toString());
        Integer isRx = null != request.getParameter("isRx") && !"".equals(request.getParameter("isRx")) ? Integer.valueOf(request.getParameter("isRx")) : null ;

        Content content = new Content();
        content.setName(title);
        content.setDescription(description);
        content.setContentType(contentType);
        //warm-up
        if(contentType == 1){
            content.setRecordType(8);
        }else if(contentType == 2){
            content.setRecordType(9);
        }else if(contentType == 3){
            content.setRecordType(10);
        }else if(contentType == 4 || contentType == 5){
            Integer recordType = null != request.getParameter("recordType") && !"".equals(request.getParameter("recordType")) ? Integer.valueOf(request.getParameter("recordType")) : null ;
            content.setRecordType(recordType);
            if(recordType == 5){
                content.setEachMeasure(null != request.getParameter("eachRoundRecordNum") && !"".equals(request.getParameter("eachRoundRecordNum")) ? Integer.valueOf(request.getParameter("eachRoundRecordNum")) : null);
                content.setEachRecordType(null != request.getParameter("eachRoundRecordType") && !"".equals(request.getParameter("eachRoundRecordType")) ? Integer.valueOf(request.getParameter("eachRoundRecordType")) : null );
            }
        }
        if(null != isRx){
            content.setIsRx(isRx);
        }

//        if(contentType == 5){
//            content.setFamousWodRecordType(Integer.valueOf(request.getParameter("recordTypeFamousWod")));
//        }
        content.setUserId(getLoginUserOfBoss(super.getUser(request).getId()).getId());
        content.setIsDeleted(0);
        contentService.addContent(content);
        return "success";
    }

    /**
     *@Author : RedCometJ
     *@Description :  content添加
     *@params  [request]
     *@return java.lang.String
     *@Date : 2017/12/5
     */
    @RequestMapping(value="/contentEdit",method=RequestMethod.POST)
    @ResponseBody
    public String updateContent(HttpServletRequest request){
        Integer contentId = Integer.valueOf(request.getParameter("contentId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer contentType = Integer.valueOf(request.getParameter("contentType").toString());
        Integer isRx = null != request.getParameter("isRx") && !"".equals(request.getParameter("isRx")) ? Integer.valueOf(request.getParameter("isRx")) : null ;
        Integer recordType = null;
        Integer eachRoundNum = null;
        Integer eachRoundRecordType = null;
      if(contentType == 4 || contentType == 5){
          recordType = null != request.getParameter("recordType") && !"".equals(request.getParameter("recordType")) ? Integer.valueOf(request.getParameter("recordType")) : null ;
          if(recordType == 5){
              eachRoundNum = null != request.getParameter("eachRoundRecordNum") && !"".equals(request.getParameter("eachRoundRecordNum")) ? Integer.valueOf(request.getParameter("eachRoundRecordNum")) : null;
              eachRoundRecordType = null != request.getParameter("eachRoundRecordType") && !"".equals(request.getParameter("eachRoundRecordType")) ? Integer.valueOf(request.getParameter("eachRoundRecordType")) : null;
          }
        }
        Integer recordTypeFamousWod = null != request.getParameter("recordTypeFamousWod") && !"".equals(request.getParameter("recordTypeFamousWod")) ? Integer.valueOf(request.getParameter("recordTypeFamousWod")) : null ;

        contentService.updateContent(contentId,title,description,recordType,isRx,recordTypeFamousWod,eachRoundNum,eachRoundRecordType);
        return "success";
    }

    @RequestMapping(value="/sectionDel",method=RequestMethod.POST)
    @ResponseBody
    public String delSection(HttpServletRequest request){
        String id = request.getParameter("id");
        sectionService.delSection(Integer.valueOf(id));
        return "success";
    }

    @RequestMapping(value="/contentDel",method=RequestMethod.POST)
    @ResponseBody
    public String delContent(HttpServletRequest request){
        String id = request.getParameter("id");
        contentService.delContent(Integer.valueOf(id));
        return "success";
    }

    /**
     * wod设置页
     * @param request
     * @return
     */
    @RequestMapping(value="/wodSetting",method= RequestMethod.GET)
    public String wodSetting(HttpServletRequest request){
        User loginUser = getUser(request);
        User boss = getLoginUserOfBoss(loginUser.getId());
        Setting wodSetting = settingService.queryWodSetting(boss.getId());
        String appTime = "0天前02时00分";
        String wodWeek = "一周";
        if(null != wodSetting && null != wodSetting.getAppShowDay()){
            appTime = wodSetting.getAppShowDay()+"天前"+
                    (wodSetting.getAppShowHour()<10 ? "0"+wodSetting.getAppShowHour(): wodSetting.getAppShowHour()) + "点"+
                    (ClassScheduleUtil.minusKeyToValueMap.get(wodSetting.getAppShowMinus()))+"分";
        }
        if(null != wodSetting && null != wodSetting.getWodShowWeek()){
            wodWeek = weekMap.get(wodSetting.getWodShowWeek());
        }
        request.setAttribute("appTime",appTime);
        request.setAttribute("wodWeek",wodWeek);
        request.setAttribute("wodSetting",wodSetting);
        request.setAttribute("dayMap",dayMap);
        request.setAttribute("hourMap",hourMap);
        return "/wod/wodSetting";
    }

    /**
     * 查询wodSetting
     * @param request
     * @return
     */
    @RequestMapping(value="/queryWodSetting",method=RequestMethod.GET)
    @ResponseBody
    public String queryWodSetting(HttpServletRequest request){
        User loginUser = getUser(request);
        User boss = getLoginUserOfBoss(loginUser.getId());
        Setting wodSetting = settingService.queryWodSetting(boss.getId());
        return new Gson().toJson(wodSetting);
    }

    /**
     * 保存wodSetting
     * @param request
     * @return
     */
    @RequestMapping(value="/saveWodSetting",method=RequestMethod.GET)
    @ResponseBody
    public String saveWodSetting(HttpServletRequest request){
        User loginUser = getUser(request);
        User boss = getLoginUserOfBoss(loginUser.getId());
        Setting wodSetting = settingService.queryWodSetting(getLoginUserOfBoss(loginUser.getId()).getId());
        Integer appShowDay = ServletRequestUtils.getIntParameter(request,"day",-1);
        Integer appShowHour = ServletRequestUtils.getIntParameter(request,"hour",-1);
        Integer appShowMinus = ServletRequestUtils.getIntParameter(request,"minus",-1);
        Integer wodWeek = ServletRequestUtils.getIntParameter(request,"week",-1);

        if(null == wodSetting){
            wodSetting = new Setting();
        }
        if(-1 != appShowDay){
            wodSetting.setAppShowDay(appShowDay);
        }
        if(-1 != appShowHour){
            wodSetting.setAppShowHour(appShowHour);
        }
        if(-1 != appShowMinus){
            wodSetting.setAppShowMinus(appShowMinus);
        }
        if(-1 != wodWeek){
            wodSetting.setWodShowWeek(wodWeek);
        }
        wodSetting.setUserId(boss.getId());
        settingService.saveWodSetting(wodSetting);
        return "success";
    }
    private String queryRecordName(Integer recordTypeId){
        String recordName = "";
        switch (recordTypeId){
            case 1 : recordName = "Time"; break;
            case 2 : recordName = "AMRAP-Rounds"; break;
            case 3 : recordName = "AMRAP-Reps"; break;
            case 4 : recordName = "AMRAP-Rounds and Reps"; break;
            case 5 : recordName = "each round"; break;
            case 6 : recordName = "distance"; break;
            case 7 : recordName = "Calories"; break;
            case 8 : recordName = "No Measure"; break;
            case 9 : recordName = "reps"; break;
            case 10 : recordName = "weight"; break;
        }
        return  recordName;
    }


    /**
     * 获取天
     */
    private Map<Integer,String> dayMap = new HashMap<Integer,String>(){{
        for(int i=0 ;i<8; i++){
            put(i,i+"天");
        }
    }};
    /**
     * 小时
     */
    private Map<Integer,String> hourMap = new HashMap<Integer,String>(){{
        for(int i=0 ;i<=23; i++){
            put(i,i+"时");
        }
    }};

    private Map<Integer,String> weekMap = new HashMap<Integer,String>(){{
        put(1, "一周");
        put(2,"二周");
        put(3,"三周");
        put(4,"四周");
    }};
}
