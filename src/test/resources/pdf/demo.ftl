<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <style type="text/css">
        body {
            font-family: SimSun;
        }
    </style>
    <link rel="stylesheet" href="${staticPath}/reset.css"/>
    <link rel="stylesheet" href="${staticPath}/demo.css"/>
</head>
<body>
<div>
    <#--页眉-->
    <div class="header">
        <span class="small">${company}</span>
        <div class="line"/>
    </div>
    <#--页脚-->
    <div class="footer">
        <#--当前页/总页数-->
        <span class="currpage"/>/<span class="pagecount"/>
    </div>
    <div class="center">
        <div class="center">阿尔法鱼</div>
        <#--显示本地图片-->
        <img style="width: 200px;height: 200px; text-align: center;" src="logo.png"/>
        <div class="center">
            <div class="center">菜品测试报告</div>
            <div class="center">${coverInfo.dishName}</div>
            <div class="center">${coverInfo.reportPerson}</div>
            <div class="center"> ${coverInfo.reportTime}</div>
        </div>
    </div>
</div>
<#--强制分页-->
<div class="break"/>
<div>
    <div class="footer">
        <span class="currpage"/>/<span class="pagecount"/>
    </div>
    page2
</div>
</body>
</html>