package test;

import annotation.utils.CheckBeanUtils;
import annotation.FieldNotNull;
import annotation.FieldNumberScope;

/**
 * Created by huhongsen on 2018/12/7.
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person();
        person.setName("asdfasdf");
        person.setAge(101);
        CheckBeanUtils.checkBean(person);
    }

    public static class Person {
        @FieldNotNull
        private String name;

        @FieldNotNull
        @FieldNumberScope(from = 100, to = 200)
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
