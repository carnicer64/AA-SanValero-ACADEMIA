import com.svalero.academia.domain.User;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserTests {
    private String username;
    private String password;
    private int idUser;
    private String role;
    private String userFirstName;
    private String userLastName;
    private String userTlp;
    private String userEmail;
    User user = new User("username", "password", 111, "admin", "ASDF", "ASDF", "111", "ASDF@");


    @Test
    public void UserTest1(){
        username = "username";
        Assert.assertEquals(username, user.getUsername());
    }
    @Test
    public void UserTest2(){
        password = "password";
        Assert.assertEquals(password, user.getPassword());
    }
    @Test
    public void UserTest3(){
        idUser = 111;
        Assert.assertEquals(idUser, user.getIdUser());
    }
    @Test
    public void UserTest4(){
        role = "admin";
        Assert.assertEquals(role, user.getRole());
    }
    @Test
    public void UserTest5(){
        userFirstName = "ASDF";
        Assert.assertEquals(userFirstName, user.getUserFirstName());
    }
    @Test
    public void UserTest6(){
        userLastName = "ASDF";
        Assert.assertEquals(userLastName, user.getUserLastName());
    }
    @Test
    public void UserTest7(){
        userTlp = "111";
        Assert.assertEquals(userTlp, user.getUserTlp());
    }
    @Test
    public void UserTest8(){
        userEmail = "ASDF@";
        Assert.assertEquals(userEmail, user.getUserEmail());
    }
}
