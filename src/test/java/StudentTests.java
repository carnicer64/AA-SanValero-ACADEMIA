import com.svalero.academia.domain.Student;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class StudentTests {
    private int idStudent;
    private String sName;
    private String sLastName;
    private String sAddress;
    private String sTlp;
    private String sEmail;
    Student student = new Student(111, "ASDF", "ASDF", "ASDF", "111", "ASDF@");

    @Test
    public void StudentTest1(){
        idStudent = 111;
        Assert.assertEquals(idStudent, student.getIdStudent());
    }
    @Test
    public void StudentTest2(){
        sName = "ASDF";
        Assert.assertEquals(sName, student.getsName());
    }
    @Test
    public void StudentTest3(){
        sLastName = "ASDF";
        Assert.assertEquals(sLastName, student.getsLastName());
    }
    @Test
    public void StudentTest4(){
        sAddress = "ASDF";
        Assert.assertEquals(sAddress, student.getsAddress());
    }
    @Test
    public void StudentTest5(){
        sTlp = "111";
        Assert.assertEquals(sTlp, student.getsTlp());
    }
    @Test
    public void StudentTest6(){
         sEmail = "ASDF@";
        Assert.assertEquals(sEmail, student.getsEmail());
    }
}
