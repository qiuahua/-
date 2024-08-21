package com.itheima.case2.utils;

//工厂类用来创建对象

import java.util.HashMap;
import java.util.ResourceBundle;

/**实现步骤
 * 1.创建马屁集合存储创建的对象
 * 2.定义静态方法创建具体类的对象
 * 3.在静态方法中判断map集合的key是否有值，如果没有值，说明第一创建对象
 * 4.直接使用放射+读取配置文件的方式创建对象
 * 5.将创建对象作为map集合的value和key存到map中
 * 6.放回给调用者创建的对象
 */
public class BeansFactory {
//    **实现步骤

// * 1.创建马屁集合存储创建的对象
   private static HashMap<String,Object> map  = new HashMap<String,Object>();
// * 2.定义静态方法创建具体类的对象
    public synchronized static<T> T getInstance(String key) throws Exception{
        // * 3.在静态方法中判断map集合的key是否有值，如果没有值，说明第一创建对象
        Object obj = map.get(key);
        // * 4.直接使用放射+读取配置文件的方式创建对象
        if(obj == null){

            // * 5.将创建对象作为map集合的value和key存到map中
            //使用反射和读取配置文件创建对象
            ResourceBundle beans = ResourceBundle.getBundle("beans"); //参数beans表示要关联配置文件的名字，不能书写后缀
//
            String classNamStr = beans.getString(key);

            //使用放射创建对象
            Class<?> aClass = Class.forName(classNamStr);
            obj = aClass.newInstance();

            map.put(key,obj);

        }

        return(T) obj;
    }


// * 5.将创建对象作为map集合的value和key存到map中
// * 6.放回给调用者创建的对象
// */
}
