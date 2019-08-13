<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>结算页</title>

    <link rel="stylesheet" type="text/css" href="http://localhost:9107/css/webbase.css"/>
    <link rel="stylesheet" type="text/css" href="http://localhost:9107/css/pages-getOrderInfo.css"/>
</head>

<body>
<!--head-->
<div class="top">
    <div class="py-container">
        <div class="shortcut">
            <ul class="fl">
                <li class="f-item">无忧欢迎您！</li>
                <li class="f-item">请登录　<span><a href="#">免费注册</a></span></li>
            </ul>
            <ul class="fr">
                <li class="f-item">我的订单</li>
                <li class="f-item space"></li>
                <li class="f-item">我的品优购</li>
                <li class="f-item space"></li>
                <li class="f-item">品优购会员</li>
                <li class="f-item space"></li>
                <li class="f-item">企业采购</li>
                <li class="f-item space"></li>
                <li class="f-item">关注品优购</li>
                <li class="f-item space"></li>
                <li class="f-item">客户服务</li>
                <li class="f-item space"></li>
                <li class="f-item">网站导航</li>
            </ul>
        </div>
    </div>
</div>
<div class="cart py-container">
    <!--logoArea-->
    <div class="logoArea">
        <div class="fl logo"><span class="title">结算页</span></div>
        <div class="fr search">
            <form class="sui-form form-inline">
                <div class="input-append">
                    <input type="text" type="text" class="input-error input-xxlarge" placeholder="品优购自营"/>
                    <button class="sui-btn btn-xlarge btn-danger" type="button">搜索</button>
                </div>
            </form>
        </div>
    </div>
    <!--主内容-->
    <div class="checkout py-container">
        <div class="checkout-tit">
            <h4 class="tit-txt">填写并核对订单信息</h4>
        </div>
        <div class="checkout-steps">
            <!--收件人信息-->
            <div class="step-tit">
                <h5>收件人信息<span><a data-toggle="modal" data-target=".edit" data-keyboard="false"
                                  class="newadd">新增收货地址</a></span></h5>
            </div>
            <div class="step-cont">
                <div class="Info">
                    <ul class="addr-detail">
                        <li class="addr-item">
                            <c:forEach items="${addresslist}" var="item">
                                <div>
                                    <div class="con name"><a href="javascript:;">${item.contact}<span title="点击取消选择"/>&nbsp;</a>
                                    </div>
                                    <div class="con address">${item.contact} ${item.address} <span>${item.mobile}</span>
                                        <span class="edittext">&nbsp;&nbsp;<a
                                                onclick="shanchu(${item.id})">删除</a></span>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </c:forEach>
                        </li>


                    </ul>
                    <!--添加地址-->
                    <div tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade edit">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×
                                    </button>
                                    <h4 id="myModalLabel" class="modal-title">添加收货地址</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="" class="sui-form form-horizontal">
                                        <div class="control-group">
                                            <label class="control-label">收货人：</label>
                                            <div class="controls">
                                                <input type="text" class="input-medium" id="contact">
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label">详细地址：</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" id="address">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">联系电话：</label>
                                            <div class="controls">
                                                <input type="text" class="input-medium" id="mobile">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">邮箱：</label>
                                            <div class="controls">
                                                <input type="text" class="input-medium" id="">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">地址别名：</label>
                                            <div class="controls">
                                                <input type="text" class="input-medium">
                                            </div>
                                            <div class="othername">
                                                建议填写常用地址：<a href="#" class="sui-btn btn-default">家里</a>　<a href="#"
                                                                                                           class="sui-btn btn-default">父母家</a>　<a
                                                    href="#" class="sui-btn btn-default">公司</a>
                                            </div>
                                        </div>

                                    </form>


                                </div>
                                <div class="modal-footer">
                                    <button type="button" data-ok="modal" class="sui-btn btn-primary btn-large"
                                            onclick="addaddress()">确定
                                    </button>
                                    <button type="button" data-dismiss="modal" class="sui-btn btn-default btn-large">
                                        取消
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--确认地址-->
                </div>
                <div class="hr"></div>

            </div>
            <div class="hr"></div>
            <!--支付和送货-->
            <div class="payshipInfo">
                <div class="step-tit">
                    <h5>支付方式</h5>
                </div>
                <div class="step-cont">
                    <ul class="payType">
                        <li class="selected">微信付款<span title="点击取消选择"></span></li>
                        <li>货到付款<span title="点击取消选择"></span></li>
                    </ul>
                </div>
                <div class="hr"></div>
                <div class="step-tit">
                    <h5>订单号：${order_id}</h5>
                </div>
                <div class="step-cont">

                    <ul class="send-detail">
                        <li>

                            <div class="sendGoods">
                                <c:forEach items="${OrderItem}" var="item">
                                    <ul class="yui3-g">
                                        <li class="yui3-u-1-6">
                                            <span><img width="200" height="200" src="${item.picPath}"/></span>
                                        </li>
                                        <li class="yui3-u-7-12">
                                            <div class="desc">${item.title}</div>
                                            <div class="seven">7天无理由退货</div>
                                        </li>
                                        <li class="yui3-u-1-12">
                                            <div class="price">￥${item.price}</div>
                                        </li>
                                        <li class="yui3-u-1-12">
                                            <div class="num">X${item.num}</div>
                                        </li>
                                        <li class="yui3-u-1-12">
                                            <div class="exit">有货</div>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </div>
                        </li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
                <div class="hr"></div>
            </div>
            <div class="linkInfo">
                <div class="step-tit">
                    <h5>发票信息</h5>
                </div>
                <div class="step-cont">
                    <span>普通发票（电子）</span>
                    <span>个人</span>
                    <span>明细</span>
                </div>
            </div>
            <div class="cardInfo">
                <div class="step-tit">
                    <h5>使用优惠/抵用</h5>
                </div>
            </div>
        </div>
    </div>
    <div class="order-summary">
        <div class="static fr">
            <div class="list">
                <span><i class="number">1</i>件商品，总商品金额</span>
                <em class="allprice">${totalPrice}</em>
            </div>
            <div class="list">
                <span>返现：</span>
                <em class="money">0.00</em>
            </div>
            <div class="list">
                <span>运费：</span>
                <em class="transport">0.00</em>
            </div>
        </div>
    </div>
    <div class="submit">
        <button class="sui-btn btn-danger btn-xlarge" onclick="pay(${order_id},${totalPrice})">提交订单</button>
    </div>
</div>
<!-- 底部栏位 -->
<!--页面底部-->
<div class="clearfix footer">
    <div class="py-container">
        <div class="footlink">
            <div class="Mod-service">
                <ul class="Mod-Service-list">
                    <li class="grid-service-item intro  intro1">

                        <i class="serivce-item fl"></i>
                        <div class="service-text">
                            <h4>正品保障</h4>
                            <p>正品保障，提供发票</p>
                        </div>

                    </li>
                    <li class="grid-service-item  intro intro2">

                        <i class="serivce-item fl"></i>
                        <div class="service-text">
                            <h4>正品保障</h4>
                            <p>正品保障，提供发票</p>
                        </div>

                    </li>
                    <li class="grid-service-item intro  intro3">

                        <i class="serivce-item fl"></i>
                        <div class="service-text">
                            <h4>正品保障</h4>
                            <p>正品保障，提供发票</p>
                        </div>

                    </li>
                    <li class="grid-service-item  intro intro4">

                        <i class="serivce-item fl"></i>
                        <div class="service-text">
                            <h4>正品保障</h4>
                            <p>正品保障，提供发票</p>
                        </div>

                    </li>
                    <li class="grid-service-item intro intro5">

                        <i class="serivce-item fl"></i>
                        <div class="service-text">
                            <h4>正品保障</h4>
                            <p>正品保障，提供发票</p>
                        </div>

                    </li>
                </ul>
            </div>
            <div class="clearfix Mod-list">
                <div class="yui3-g">
                    <div class="yui3-u-1-6">
                        <h4>购物指南</h4>
                        <ul class="unstyled">
                            <li>购物流程</li>
                            <li>会员介绍</li>
                            <li>生活旅行/团购</li>
                            <li>常见问题</li>
                            <li>购物指南</li>
                        </ul>

                    </div>
                    <div class="yui3-u-1-6">
                        <h4>配送方式</h4>
                        <ul class="unstyled">
                            <li>上门自提</li>
                            <li>211限时达</li>
                            <li>配送服务查询</li>
                            <li>配送费收取标准</li>
                            <li>海外配送</li>
                        </ul>
                    </div>
                    <div class="yui3-u-1-6">
                        <h4>支付方式</h4>
                        <ul class="unstyled">
                            <li>货到付款</li>
                            <li>在线支付</li>
                            <li>分期付款</li>
                            <li>邮局汇款</li>
                            <li>公司转账</li>
                        </ul>
                    </div>
                    <div class="yui3-u-1-6">
                        <h4>售后服务</h4>
                        <ul class="unstyled">
                            <li>售后政策</li>
                            <li>价格保护</li>
                            <li>退款说明</li>
                            <li>返修/退换货</li>
                            <li>取消订单</li>
                        </ul>
                    </div>
                    <div class="yui3-u-1-6">
                        <h4>特色服务</h4>
                        <ul class="unstyled">
                            <li>夺宝岛</li>
                            <li>DIY装机</li>
                            <li>延保服务</li>
                            <li>品优购E卡</li>
                            <li>品优购通信</li>
                        </ul>
                    </div>
                    <div class="yui3-u-1-6">
                        <h4>帮助中心</h4>
                        <img src="http://localhost:9107/img/wx_cz.jpg">
                    </div>
                </div>
            </div>
            <div class="Mod-copyright">
                <ul class="helpLink">
                    <li>关于我们<span class="space"></span></li>
                    <li>联系我们<span class="space"></span></li>
                    <li>关于我们<span class="space"></span></li>
                    <li>商家入驻<span class="space"></span></li>
                    <li>营销中心<span class="space"></span></li>
                    <li>友情链接<span class="space"></span></li>
                    <li>关于我们<span class="space"></span></li>
                    <li>营销中心<span class="space"></span></li>
                    <li>友情链接<span class="space"></span></li>
                    <li>关于我们</li>
                </ul>
                <p>地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096 电话：400-618-4000 传真：010-82935100</p>
                <p>京ICP备08001421号京公网安备110108007702</p>
            </div>
        </div>
    </div>
</div>
<!--页面底部END-->

<script type="text/javascript" src="http://localhost:9107/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="http://localhost:9107/js/plugins/jquery.easing/jquery.easing.min.js"></script>
<script type="text/javascript" src="http://localhost:9107/js/plugins/sui/sui.min.js"></script>

<script type="text/javascript" src="http://localhost:9107/js/pages/getOrderInfo.js"></script>
</body>
<script>

    function pay(oderid, total) {
        $.ajax({
            type: "post",
            data: {
                oderid: oderid,
                total: total,
            },
            url: "http://261k49040o.qicp.vip:52979/alipay/pay.do",
            success: function (result) {
                console.log(result.data)
                var div = document.createElement('div')
                console.log(div)
                /* 此处form就是后台返回接收到的数据 */
                div.innerHTML = result
                document.head.appendChild(div)
                document.forms[0].submit()
            },
            error: function () {
                alert("失败")
            }
        })
    }

    function shanchu(idd) {
        $.ajax({
            url: "http://localhost:8080/address/Delete.do",
            data: {id: idd},
            success: function (result) {
                alert(result.message);
                location.reload()
            }
        })
    }

    function addaddress() {
        var address = {
            userId: localStorage.getItem("user"),
            contact: document.getElementById("contact").value,
            address: document.getElementById("address").value,
            mobile: document.getElementById("mobile").value,
        };
        $.ajax({
            url: "http://localhost:8080/address/add.do",
            dataType: "json",
            type: "POST",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(address),
            success: function (result) {
                alert(result.message);
                location.reload();
            }

        })
    }

</script>
</html>