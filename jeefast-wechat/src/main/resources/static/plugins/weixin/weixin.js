
if(typeof WeixinJSBridge == "undefined") {
    // 检查是否引入微信JS
    if(typeof wx.config == 'undefined')
    {
        document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"><\/script>');
    }

    var _H5Browser = {

        /**
         * 微信权限验证
         * debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
         * appId: '', // 必填，公众号的唯一标识
         * timestamp: , // 必填，生成签名的时间戳
         * nonceStr: '', // 必填，生成签名的随机串
         * signature: '',// 必填，签名，见附录1
         * jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，这里只写支付的
         */
        authValidate : function (debug,appId,timestamp,nonceStr,signature,jsApiList,success,error) {
            wx.config({
                debug: debug,
                appId: appId,
                timestamp: timestamp,
                nonceStr: nonceStr,
                signature:signature,
                jsApiList: ['chooseWXPay']
            });
            wx.error(function(res){
                console.info(res);
                error(res);
            });
        },
        /**
         * 判断当前客户端版本是否支持指定JS接口
         */
        checkJsApi : function () {
            wx.checkJsApi({
                jsApiList: ['chooseWXPay'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
                success: function(res) {
                    console.info(res);
                    // 以键值对的形式返回，可用的api值true，不可用为false
                    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                }
            });
        },

        /**
         * 预支付通过后设置同意支付的基础设置
         * @param appId     必填，生成签名的时间戳
         * @param timestamp 必填，生成签名的随机串
         * @param nonceStr  必填，签名，见附录1
         * @param signature 必填，JS微信支付接口
         */
        setConfig : function (appId,timestamp,nonceStr,signature) {
            wx.config({
                appId: appId, // 必填，公众号的唯一标识
                timestamp: timestamp, // 必填，生成签名的时间戳
                nonceStr: nonceStr, // 必填，生成签名的随机串
                signature: signature,// 必填，签名，见附录1
                jsApiList: ['chooseWXPay'] // 必填，JS微信支付接口
            });
        },

        /**
         * 微信支付
         * @param timestamp
         * @param nonceStr
         * @param package
         * @param signType  默认MD5
         * @param paySign
         */
        pay : function (timestamp,nonceStr,package,signType,paySign) {
            wx.chooseWXPay({
                timestamp: timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                nonceStr: nonceStr, // 支付签名随机串，不长于 32 位
                package: package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                paySign: paySign, // 支付签名
                success: function (res) {
                    console.info(res);
                    // 支付成功后的回调函数
                }
            });
        }
    }

} else {
    /**
     * 微信浏览器支付
     * @param appId         商户注册具有支付权限的公众号成功后即可获得
     * @param timeStamp     时间戳，自1970年以来的秒数
     * @param nonceStr      随机字符串，不长于32位
     * @param package       统一下单接口返回的prepay_id参数值
     * @param signType      签名算法，暂支持MD5
     * @param paySign       签名
     * @param callbackOk    成功的回调
     * @param callbackError 失败的回调
     */
    function pay(appId,timeStamp,nonceStr,package,signType,paySign,callbackOk,callbackError) {

        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId":appId,              //公众号名称，由商户传入
                "timeStamp":timeStamp,      //时间戳，自1970年以来的秒数
                "nonceStr":nonceStr,        //随机串
                "package":package,
                "signType":"MD5",           //微信签名方式：默认MD5
                "paySign":signType          //微信签名
            },
            function(res){
                // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                    callbackOk()
                }
                //支付过程中用户取消
                if(res.err_msg == "get_brand_wcpay_request:cancel" ) {
                    callbackError()
                }
                if(res.err_msg == "get_brand_wcpay_request:fail" ) {
                    callbackError()
                }
            }
        );
    }

}


