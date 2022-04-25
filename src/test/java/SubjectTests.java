import com.svalero.academia.domain.Subject;
import junit.framework.Assert;

import org.junit.jupiter.api.Test;



public class SubjectTests {
    private int idSubject;
    private String subName;
    private int subVacancies;
    private String subTeacher;
    private int subPrice;
    Subject subject = new Subject(111, "ASDF", 111, "ASDF", 111);

    @Test
    public void SubjectTest1(){
        idSubject = 111;
        Assert.assertEquals(idSubject, subject.getIdSubject());
    }
    @Test
    public void SubjectTest2(){
        subName = "ASDF";
        Assert.assertEquals(subName, subject.getSubName());
    }
    @Test
    public void SubjectTest3(){
        subVacancies = 111;
        Assert.assertEquals(subVacancies, subject.getSubVacancies());
    }
    @Test
    public void SubjectTest4(){
        subTeacher = "ASDF";
        Assert.assertEquals(subTeacher, subject.getSubTeacher());
    }
    @Test
    public void SubjectTest5(){
        subPrice = 111;
        Assert.assertEquals(subPrice, subject.getSubPrice());
    }
}
