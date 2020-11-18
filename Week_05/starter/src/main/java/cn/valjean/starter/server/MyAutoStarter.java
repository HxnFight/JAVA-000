package cn.valjean.starter.server;

import cn.valjean.starter.domain.Klass;
import cn.valjean.starter.domain.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;

@Configuration
@ConditionalOnClass(Klass.class)
@EnableConfigurationProperties(Student.class)
public class MyAutoStarter {

    @Resource
    private Student student;

    @Bean
    @ConditionalOnMissingBean
    public Klass getKlassBean() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(student);
        return new Klass(list);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
