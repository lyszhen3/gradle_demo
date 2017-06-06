package test;

import test.beans.AbstractF;
import test.beans.AbstractS;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pc on 2017-05-16.
 *
 * @author pc
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
public class BeanCopy {



    public static void beanCopy(Object source,Object target){

        Class<?> sClazz=source.getClass();
        Class<?> tClazz=target.getClass();
        Map<String,PropertyDescriptor> descriptorMap=new LinkedHashMap<>();


        try {
            BeanInfo infos=Introspector.getBeanInfo(sClazz);
            PropertyDescriptor[] pdss=infos.getPropertyDescriptors();
            for(PropertyDescriptor pd:pdss){
                if(!pd.getName().equals("class")){
                    descriptorMap.put(pd.getName(),pd);
                }
            }

            BeanInfo info= Introspector.getBeanInfo(tClazz);
            PropertyDescriptor[] pds=info.getPropertyDescriptors();
            for(PropertyDescriptor pd:pds){
                if(!pd.getName().equals("class")){
                    PropertyDescriptor sourcePd=descriptorMap.get(pd.getName());
                    if(sourcePd!=null) {
                        Method readMethod = sourcePd.getReadMethod();
                        Object value = readMethod.invoke(source);
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(target, value);
                    }
                }
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        AbstractF a1=new AbstractF(){};
        a1.setName("天天");
        a1.setId("1");
        AbstractS a2=new AbstractS() {
        };

        beanCopy(a1,a2);
        System.out.println(a2.getName());


    }

}
