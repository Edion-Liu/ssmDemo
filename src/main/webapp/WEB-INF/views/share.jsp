<%--
  Created by IntelliJ IDEA.
  User: Edion
  Date: 2017/3/17
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.eliteams.quick4j.web.model.VO.TResortVO" %>
<%@ page import="com.eliteams.quick4j.web.model.VO.TCampsiteCustom" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <%
        TResortVO tResortVO=null;
        TCampsiteCustom tCampsiteCustom=null;
       String type= (String) request.getAttribute("type");
        if(type.equals("20")){
            tResortVO= (TResortVO) request.getAttribute("resortdetail");
        }else {
            tCampsiteCustom = (TCampsiteCustom) request.getAttribute("campsitedetail");
        }
    %>

    <!-- 禁止浏览器从本地缓存中调阅页面。-->
    <meta http-equiv="pragram" content="no-cache">
    <!--网页不保存在缓存中，每次访问都刷新页面。-->
    <meta http-equiv="cache-control" content="no-cache, must-revalidate">
    <!--同上面意思差不多，必须重新加载页面-->
    <!--网页在缓存中的过期时间为0，一旦网页过期，必须从服务器上重新订阅-->
    <meta http-equiv="expires" content="0">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <style type="text/css">
        * {
            margin:0;
            padding:0;
        }
        html,
        body,
        #box {
            height:100%;
            font:small/1.5 "宋体", serif;
        }
        body {
            text-align:center;
        }
        #box {
            text-align:left;
            background:#666;
            display:table;
            width:100%;
            margin:0 auto;
            position:relative;
        }
        #box > div {
            display:table-row;
        }
        #header,
        #footer {
            background:#fcc;
            height:50px;
            vertical-align:bottom;
        }
        #main {
            background:#ccf;
        }
        #main #wrap {
            display:table-cell;
            background:#ffc;
            /* vertical-align:middle;*/
        }
        #text {
            font-size: xx-large;
            text-align:center;
        }

        #imageid{
            width: 100%;
            height: 60%;
        }
    </style>
    <!--[if IE]>
    <style type="text/css">
        #header,
        #footer {
            width:100%;
            z-index:3;
            position:relative;
        }

        #main {
            height:100%;
            z-index:1;
            position:relative;
        }
        #main #wrap {
            position:relative;
            top:50%;
            width:100%;
            text-align:left;
        }
        #main #text {
            width:100%;
            background:#ccc;
        }
    </style>
    <![endif]-->
</head>
<body>
<div id="box">
    <!--<div id="header">header</div>-->
    <div id="main">
        <div id="wrap">
            <img id="imageid" src="http://oss-cn-shenzhen.aliyuncs.com/woyouzhijia/2517_14585405237043"/>
            <div id="text">
                <%
                if(type.equals("20")){
                %>
                <p>营地描述：<%=tResortVO.getDescription()%></p>


                  <%}else {%>

                <p>窝窝描述：<%=tCampsiteCustom.gettCampsite().getDescription()%></p>

                <%}%>

            </div>
        </div>
    </div>

    &lt;%&ndash;<div id="footer">footer</div>&ndash;%&gt;
</div>
<div style="height: 50px">dddddd</div>
</body>
</html>--%>


<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <%
        TResortVO tResortVO=null;
        TCampsiteCustom tCampsiteCustom=null;
        String type= (String) request.getAttribute("type");
        if(type.equals("20")){
            tResortVO= (TResortVO) request.getAttribute("resortdetail");
        }else {
            tCampsiteCustom = (TCampsiteCustom) request.getAttribute("campsitedetail");
        }
    %>


    <meta charset="UTF-8">
    <title>详情</title>
    <style type="text/css">
        #text {
            font-size: xx-large;
            text-align:center;
        }

        #imageid{
            width: 100%;
            height: 60%;
        }
        </style>
    <style>
        html,body{
            margin:0;
        }
        .topNav{
            border:solid 1px red;
            background: red;
            position: fixed;
            width:100%;
            height:60px;
            line-height:60px;
            text-align: center;
            color:#fff;
        }
        .centerContext{
            border:solid 1px blue;
            background: #ccc;
            position: absolute;
            width:100%;
            bottom: 40px;
            top:0px;
            text-align: center;
            color:#fff;    left:0;
            right:0;
            margin:0 auto;
            z-index: -1;
        }
        .footer{
            border:solid 1px green;
            background: green;
            height:80px;
            line-height:40px;
            position: fixed;
            bottom: 0;
            width:100%;
            text-align: center;
            color:#fff;
            font-size: xx-large;
        }
        .left{
            border:solid 1px #669ae1;
            background: #669ae1;
            width:300px;
            float:left;
            height:100%;
            margin-bottom: 40px;
            overflow: auto;
            color:#fff;
        }
        .right{
            border: solid 1px #1d5464;
            height:100%;
            background: #1d5464;
            color:#fff;
            overflow: auto;
            margin: 0 auto;
        }
        .right div{height:100%}
    </style>
</head>
<body>
<!--<div class="topNav">头部</div>-->
<div class="centerContext">
    <!-- <div class="left">左边</div>-->
    <div class="right">

            <div id="text">
                <%
                    if(type.equals("20")){
                %>
                <img id="imageid" src='<%=tResortVO.gettResortImageList().get(0).getImageUrl()%>' />
                <p>营地描述：<%=tResortVO.getDescription()%></p>


                <%}else {%>
                <img id="imageid" src='<%=tCampsiteCustom.gettCampsiteImageList().get(0).getImageUrl()%>'/>
                <p>窝窝描述：<%=tCampsiteCustom.gettCampsite().getDescription()%></p>

                <%}%>

            </div>

    </div>
</div>
<div class="footer"><a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.roveover.wowo">下载窝友之家软件</a></div>
</body>
</html>