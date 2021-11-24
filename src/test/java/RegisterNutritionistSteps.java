import com.nutrix.command.infra.Nutritionist;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterNutritionistSteps {

    @LocalServerPort
    private int port;
    private String uid;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="https://nutrix-nutritionist-service.mybluemix.net/";
    //private String postUrl="http://localhost:8081/";
    private String error= "Ingrese un codigo valido";

    public String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array);

        return generatedString;
    }

    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I can sign up as a nutritionist")
    public void i_can_sign_up_as_a_nutritionist() throws Throwable {
        String url=postUrl + "nutritionist/";
        List<Nutritionist> all=restTemplate.getForObject(url, List.class);
        log.info(all);
        assertTrue(!all.isEmpty());
    }

    @Given("^I sending register form to be created with my cnp code(\\d+)$")
    public void i_sending_register_form_to_be_created_with_my_cnp_code(int arg1) throws Throwable {
        Date date= new Date();
        Nutritionist nutritionist = new Nutritionist("uid", "username", "password","Luis" ,"Panta", "randomString()",arg1, date);
        String url=postUrl + "nutritionist/";
        Nutritionist newNutritionist =restTemplate.postForObject(url,nutritionist,Nutritionist.class);
        uid = newNutritionist.getId();
        log.info(newNutritionist);
        assertNotNull(newNutritionist);
    }

    @Then("I should be able to see my newly created account")
    public void i_should_be_able_to_see_my_newly_created_account() throws Throwable {
        String url=postUrl+"nutritionist/" + uid;
        Nutritionist nutritionist=restTemplate.getForObject(url,Nutritionist.class);
        log.info(nutritionist);
        assertNotNull(nutritionist);
    }

    @Given("I sending register form to be created without cnp code(\\d+)$")
    public void i_sending_register_form_to_be_created_without_cnp_code0(int arg1) {
        Date date= new Date();
        Nutritionist nutritionist = new Nutritionist("uid", "username", "password","Luis" ,"Panta", randomString(),arg1, date);
        String url=postUrl + "nutritionist/";

        try
        {
            Nutritionist newNutritionist =restTemplate.postForObject(url,nutritionist,Nutritionist.class);
            log.info(newNutritionist);
        }catch(RestClientException e){
            error="Ingrese un codigo valido";
        }
        assertEquals(error,"Ingrese un codigo valido");

    }

    @Then("I should be able to see an error message\"Ingrese un codigo valido\"")
    public void i_should_be_able_to_see_an_error_message_ingrese_un_codigo_valido() {
        assertEquals("Ingrese un codigo valido",error);
    }
}
