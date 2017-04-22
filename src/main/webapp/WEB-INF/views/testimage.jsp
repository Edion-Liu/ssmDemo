<%--
  Created by IntelliJ IDEA.
  User: Edion
  Date: 2016/12/22
  Time: 10:44
  To change this template use File | Settings | File Templates
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
</head>
<body>

<%--<form name="upload" action="/rest/api/users/upload2" enctype="multipart/form-data" method="post">

    广告类型：<br>
    <input type="text" name="adtype" value="1-首页广告，2-窝友头条" size="100%"><br>
    超链接：<br>
    <input type="text" name="adlink" value="http://www.roveover.com/web/portal.php" size="100%"><br>

    <input type="file" name="thefile" /> <input type="submit" value="上传文件" />
</form>--%>

<button onclick="ajaxtest()">ajax测试</button>


<script>
    var serverurl="<%=basePath%>"+"rest/manage/yueban/ajaxtest";
    function ajaxtest() {
       // alert("<%=basePath%>");

        $.ajax({
            url:serverurl,       //跨域到http://www.wp.com，另，http://test.com也算跨域
            dataType:'json',                          //指定为jsonp类型
            data:{"id":236},                //数据参数
            json:'callback',                          //服务器端获取回调函数名的key，对应后台有$_GET['callback']='getName';callback是默认值
            jsonCallback:'getimageyueban',                   //回调函数名

            success:function(result){                  //成功执行处理，对应后台返回的getName(data)方法。


                alert(JSON.stringify(result));                 //执行成功


            },
            error:function(msg){
                alert('执行错误');                 //执行成功
                alert(msg.toSource());                 //执行错误
            }
        });


      alert("你好顶顶顶顶");
    }


</script>

</body>
</html>
