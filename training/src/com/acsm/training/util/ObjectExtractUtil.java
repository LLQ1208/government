package com.acsm.training.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import com.acsm.training.vo.CourseMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acsm.training.vo.CourseMember;

/**
 * 属性提取工具类
 * @author yuchao
 *
 */
public class ObjectExtractUtil{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public static <T>Object[] extractPropertiesEntity(String[] propertyNames,T t){
		Object[] objects=new Object[propertyNames.length];		
		for(int i=0;i<propertyNames.length;i++){
			String propertyName=propertyNames[i];
			String getMethodName="get"+propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
			String[] methodList=propertyName.split("\\.");
			try {
				if(methodList.length==1){
					Method getMethod=t.getClass().getMethod(getMethodName);
					Object object=getMethod.invoke(t);
					if(object!=null&&object instanceof Date){
						objects[i]=DateUtil.format((Date)object,DateUtil.YYYY_MM_DD_HH_MM);
					}else if(object!=null){
						objects[i]=object.toString();
					}else{
						objects[i]="";
					}
				}else{
					Object tempObject=t;
					for(int j=0;j<methodList.length;j++){					
						String methodName="get"+methodList[j].substring(0, 1).toUpperCase() + methodList[j].substring(1);
						Method method=tempObject.getClass().getMethod(methodName);
						tempObject=method.invoke(tempObject);
						if(null == tempObject) {
							break;
						}
					}
					
					if(tempObject!=null&&tempObject instanceof Date){
						objects[i]=DateUtil.format((Date)tempObject,DateUtil.YYYY_MM_DD_HH_MM);
					}else if(tempObject!=null){
						objects[i]=tempObject.toString();
					}else{
						objects[i]="";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				objects[i]="";
			}
			
		}			
		
		return objects;
	}
	
	public static <T>Object[][] extractSheetData(Object[] title,String[] propertyNames,List<T> list){
		Object[][] sheeData=new Object[list.size()+1][title.length];
		sheeData[0]=title;
		for(int i=0;i<list.size();i++){
			T entity=list.get(i);
			Object[] objects = extractPropertiesEntity(propertyNames,entity);
			sheeData[i+1]=objects;
		}
		return sheeData;		
	}
	
	/**
	 * @param title
	 * @param propertyNames
	 * @param list
	 * @param rowSize poepleNum+1
	 * @return
	 */
	public static <T>Object[][] extractSheetDataForVertical(List<CourseMember> list,int rowSize){
		Object[][] sheeData=new Object[rowSize][list.size()];
		for(int i=0;i<list.size();i++){
			CourseMember courseMember = list.get(i);
			sheeData[0][i] = courseMember.getName();
			for(int j=1;j<rowSize;j++){
				if(courseMember.getMemberList().size()>j-1){
					sheeData[j][i] = courseMember.getMemberList().get(j-1);
				}
			}
		}
		return sheeData;		
	}
	
}
