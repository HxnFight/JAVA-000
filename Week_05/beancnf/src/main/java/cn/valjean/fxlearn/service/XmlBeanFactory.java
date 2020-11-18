package cn.valjean.fxlearn.service;

import cn.valjean.fxlearn.domain.School;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlBeanFactory {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/config.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
//        School school = beanFactory.getBean(School.class);
//        System.out.println(school);

        Object bean = beanFactory.getBean("school");
        if (bean instanceof School)
            System.out.println((School) bean);

        System.out.println("error = ");
    }
}
