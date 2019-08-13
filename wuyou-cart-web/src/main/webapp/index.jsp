<%--
  Created by IntelliJ IDEA.
  User: ll
  Date: 2019/5/15
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>index</title>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h1>Hello world</h1>
<button>提交</button>
</body>
<script>
    $(function () {
        $("button").click(function () {
            $.ajax({
                url: "http://localhost:9107/alipay/pay.do", success: function (result) {
                    var div = document.createElement('div')
                    /* 此处form就是后台返回接收到的数据 */
                    div.innerHTML = result
                    document.body.appendChild(div)
                    document.forms[0].submit()
                }
            });
        });
    })
</script>
</html>
