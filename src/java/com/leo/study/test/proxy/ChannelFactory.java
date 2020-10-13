package com.leo.study.test.proxy;

import java.lang.reflect.Proxy;

/**
 * @date: 2020/9/24
 */
public class ChannelFactory implements SellPerfume {
    @Override
    public void sellPerfume(double price) {
        System.out.println("成功购买 Channel 品牌香水，价格是" + price + "元");

    }
}
