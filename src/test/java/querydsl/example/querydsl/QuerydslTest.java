package querydsl.example.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import querydsl.example.querydsl.entity.School;
import querydsl.example.querydsl.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static querydsl.example.querydsl.entity.QSchool.school;

@SpringBootTest
@Transactional
public class QuerydslTest {

    @PersistenceContext
    EntityManager entityManager;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void before(){
        queryFactory = new JPAQueryFactory(entityManager);

        School school = School.builder()
                .name("School1")
                .build();
        Student student = Student.builder()
                .name("chan")
                .age(24)
                .school(school)
                .build();
        entityManager.persist(school);
        entityManager.persist(student);
    }

    @Test
    void test1() throws Exception{
        School school1 = queryFactory
                .select(school)
                .from(school)
                .where(school.name.eq("School1"))
                .fetchOne();
        assertEquals(school1.getName(), "School1");
    }


}
