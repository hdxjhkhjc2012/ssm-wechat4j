<#setting classic_compatible=true>
<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${base}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${base}/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${base}/css/style.css" />
    <script type="text/javascript" src="${base}/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${base}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${base}/js/ckform.js"></script>
    <script type="text/javascript" src="${base}/js/common.js"></script>
    <script src="${base}/layer/layer.js"></script>

 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="index.html" method="get">    
    用户名称：
    <input type="text" name="username" id="username"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增用户</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>subscribe</th>
        <th>openid</th>
        <th>nickname</th>
        <th>sex</th>
        <th>操作</th>
    </tr>
    </thead>
    <#list listUser as user>
        <tr>
            <td>${user.subscribe}</td>
            <td>${user.openid}</td>
            <td>${user.nickname}</td>
            <td>
            <#if user.sex = 1>男</#if>
            <#if user.sex = 0>女(或未编辑)</#if>
            </td>
            <td>
                <a class="send" onclick="send('${user.openid}','${user.nickname} ')">发送消息</a>
            </td>
        </tr>
    </#list>

</table>
<input type="hidden" class="openid" name="openid">
<input type="hidden" class="nikename" name="nikename">
<style>
    .pop span:first-child{width: 60px; position: relative; display:block}
    .pop p{overflow: hidden}
    .pop p span{float:left}
    .send:hover{cursor: pointer}
</style>
<script>

    function send(openid,nikename){
        $(".openid").val(openid);
        $(".nikename").val(nikename);
        layer.open({
            type: 1,
            area: ['400px', '220px'],
            shadeClose: true, //点击遮罩关闭
            content: '<div style="padding:20px;" class="pop"><p><span>接受者 ： </span><span>'+ nikename +'</span> </p> <p><span>内容 ：</span><textarea class="t1"></textarea></p><p><button class="btn btn-primary" onclick="sub();">发送</button></p></div>'
        });
    }
    function sub() {
        $.ajax({
            url:"${base}/user/sendMsg",
            method:"post",
            data:{openid:$(".openid").val(),content:$($($(".pop")[0]).find(".t1")[0]).val()},
            success:function(data){
                layer.alert(data,function(){
                    layer.closeAll();
                });
            },
            error:function(){

            }

        })
    }
    
    $(function () {
        $('#addnew').click(function(){
            window.location.href="add.html";
        });
    });
</script>
</body>
</html>
