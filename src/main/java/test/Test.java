package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pc on 2017-04-07.
 *
 * @author pc
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
public class Test {

    public static AtomicInteger age;
    public static void main(String[] args) {
        Car car =new Car();
       car.setIndex(1);
       car.setName("我的天");
        Field(car);

    }

    private static void Field(Object obj){

        Class<?> clazz=obj.getClass();

        Field[] fields = clazz.getDeclaredFields();
//        for(Field f:fields){
//            try {
//                f.setAccessible(true);
//                Object val = f.get(obj);
//                System.out.println(val.toString());
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

        Method[] methods = clazz.getMethods();
        for(Method m:methods){
            if(m.getName().startsWith("get")){

                try {
                    System.out.println("methodName:"+m.getName());
                    System.out.println("value:"+m.invoke(obj).toString());

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
