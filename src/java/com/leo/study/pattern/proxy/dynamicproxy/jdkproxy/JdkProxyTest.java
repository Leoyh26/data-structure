package com.leo.study.pattern.proxy.dynamicproxy.jdkproxy;

import com.leo.study.pattern.proxy.Person;

public class JdkProxyTest {

    public static void main(String[] args) {
        try {
            Person obj = (Person)new JdkMeipo().getInstance(new Girl());
            obj.findLove();
            // Object obj = new JdkMeipo().getInstance(new Girl());
            // obj.getClass().getDeclaredMethod("findLove", null).invoke(obj, null);

            /*byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            FileOutputStream os = new FileOutputStream("");
            os.write(bytes);
            os.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
