package cn.valjean.fxlearn.service;

import cn.valjean.fxlearn.domain.Klass;
import cn.valjean.fxlearn.domain.Student;
import lombok.Data;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@Data
public class AnnotationBean {

    @Resource(name = "valjean")
    private Student valjean;

    @Resource(name = "student_1")
    private Student st1;

    @Resource(name = "klass")
    private Klass klass;

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(AnnotationBean.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/config.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找并且创建 Bean
        AnnotationBean bean = applicationContext.getBean(AnnotationBean.class);

        System.out.println("this.klass = " + bean.getKlass());
        System.out.println("this.student-1 = " + bean.getSt1());

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean(name = "valjean")
    public Student getStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("valjean");
        return student;
    }
}
