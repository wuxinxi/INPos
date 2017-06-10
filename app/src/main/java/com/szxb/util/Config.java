package com.szxb.util;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class Config {
    /**
     * 加密秘钥
     */
    public static final String key = "00aaf5bded978566ffd79e6801e0fd922baf4171dcd5797d9dc6c2deb0da1bebc0265be9c345e7ca8c5f9ad220771fdbced7fbc706a3d29f1db337fb7ab21c49ac64319deb2292145b4ffcdb044a8e5339d4a8717183b165fad13083e368c6a03189e95b1df77fc7cda4488eac13098c8a11e57a5b632395a9fb2a4d75b94ed49f";

    /**
     * 下单支付
     */
    public static final String ORDER_PAY = "http://train.wizarpos.com/wizarposStatement-server/v1_0/saleOrder/orderPay";
    /**
     * 预下单
     */
    public static final String PRE_PAY = "http://train.wizarpos.com/wizarposStatement-server/v1_0/saleOrder/prePay";
    /**
     * 订单查询
     */
    public static final String ORDER_QUERY = "http://train.wizarpos.com/wizarposStatement-server/v1_0/saleOrder/orderQuery";

    /**
     * 订单关闭
     */
    public static final String ORDER_CLOSE = "http://train.wizarpos.com/wizarposStatement-server/v1_0/saleOrder/close";

    /**
     * 订单退款
     */
    public static final String ORDER_REVOKE = "http://train.wizarpos.com/wizarposStatement-server/v1_0/saleOrder/revoke";


    public static final String mid = "100105100000173";

    public static final String notity = "http://112.74.102.125:8088/notity/servlet/Callback";



    /**
     * 查询班次
     */
    public static final String GAS_URL = "http://www.szxiaobing.cn/Gas/servlet/QueryInfo";


    /**
     * 查询前5条
     */
    public static final String GAS_YouPin_URL = "http://www.szxiaobing.cn/Gas/servlet/Query";

    /**
     * 修改支付信息
     */
    public static final String GAS_Update_URL = "http://www.szxiaobing.cn/Gas/servlet/Update";
}
