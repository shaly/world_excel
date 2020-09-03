package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.AreaMiniMode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@RequestMapping("/json")
@RestController
public class JsonSqlController {


    public void setList(List<String> list, AreaMiniMode areaMiniMode){

        if(areaMiniMode==null){
            return;
        }else if(areaMiniMode.getChildren()==null){
            list.add("INSERT INTO `miniOrg` (pid,name,id)VALUES ( 653200,'和田市', 653201);");
        }else{

        }


    }


    @RequestMapping(value = "/jsonSql", method = RequestMethod.POST)
    public List<String >  transLateSql(@RequestBody List<AreaMiniMode> areaMiniMode, HttpServletResponse response, HttpServletRequest request) {
        try {
            System.out.println(JSONObject.toJSONString(areaMiniMode));
            List<String > sqlStrList=new LinkedList<String>();

            for(int p=0;p<areaMiniMode.size();p++){
                //循环省
                AreaMiniMode province=areaMiniMode.get(p);
                sqlStrList.add("INSERT INTO `miniOrg` (pid,name,id)VALUES ( 0,'"+province.getName()+"', "+province.getCode()+");");
                List<AreaMiniMode> cityList = province.getChildren();
                if(cityList ==null || cityList.size()<1){
                    continue;
                }else {
                    for (int c = 0; c < cityList.size(); c++) {
                        //循环市
                        AreaMiniMode city = cityList.get(c);
                        sqlStrList.add("INSERT INTO `miniOrg` (pid,name,id)VALUES ( "+province.getCode()+",'"+city.getName()+"', "+city.getCode()+");");
                        List<AreaMiniMode> areaList = city.getChildren();
                        if(areaList ==null || areaList.size()<1){
                            continue;
                        }else {
                            //循环区
                            for(int a=0;a<areaList.size();a++){
                                AreaMiniMode area=areaList.get(a);
                                sqlStrList.add("INSERT INTO `miniOrg` (pid,name,id)VALUES ( "+city.getCode()+",'"+area.getName()+"', "+area.getCode()+");");
                                if(area.getChildren() !=null && area.getChildren().size()>0){
                                    System.out.println("******区域子集："+JSONObject.toJSONString(area));
                                }
                            }
                        }

                    }
                }
            }
            System.out.println("================= Success to export dealer balance Excel 数量："+sqlStrList.size());
            return sqlStrList;
        } catch (Exception e) {
            System.out.println("=================  Start to export empty dealer detail Excel :" + e);
            return null;
        }
    }



}
