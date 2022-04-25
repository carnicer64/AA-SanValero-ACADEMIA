import com.svalero.academia.domain.Enroll;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class EnrollTests {
    private int idEnroll;
    private int enPrice;
    private int enQuotes;
    private String enNotes;
    private int enIdSubject;
    private int enIdStudent;
    Enroll enroll = new Enroll(111, 111, 111, 111, 111, "ASDF");

    @Test
    public void EnrollTest1(){
        idEnroll = 111;
        Assert.assertEquals(idEnroll, enroll.getIdEnroll());
    }
    @Test
    public void EnrollTest2(){
        enPrice = 111;
        Assert.assertEquals(enPrice, enroll.getEnPrice());
    }
    @Test
    public void EnrollTest3(){
        enQuotes = 111;
        Assert.assertEquals(enQuotes, enroll.getEnQuotes());
    }
    @Test
    public void EnrollTest4(){
        enNotes = "ASDF";
        Assert.assertEquals(enNotes, enroll.getEnNotes());
    }
    @Test
    public void EnrollTest5(){
        enIdSubject = 111;
        Assert.assertEquals(enIdSubject, enroll.getEnIdSubject());
    }
    @Test
    public void EnrollTest6(){
        enIdStudent = 111;
        Assert.assertEquals(enIdStudent, enroll.getEnIdStudent());
    }
}
