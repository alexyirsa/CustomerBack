package org.sotobotero.cutomer;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sotobotero.customer.controller.CustomerController;
import org.sotobotero.customer.entities.Customer;
import org.sotobotero.customer.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import all static  method from org.springframework.test.web.servlet.result.MockMvcResultMatchers
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import all static  method from org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomerControllerTests extends AbstractIntegrationTest{

    //Allowing us to test controller methods simulating a request like a any client would do using a browser or postman
    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;


    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "John Doe", "johndoe@example.com", "123456789",
                "123 Main Street", "Anytown", "Anystate", "US", "12345",
                "ABC Inc.", "CEO", "www.example.com", "twitter_handle", "facebook_url",
                "linkedin_profile", "github_handle", "instagram_handle", "youtube_url",
                "tiktok_handle", "snapchat_handle", "twitch_handle", "other", "notes",34));

        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customer")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[0].email", is("johndoe@example.com")))
                .andExpect(jsonPath("$[0].phone", is("123456789")))
                .andExpect(jsonPath("$[0].address", is("123 Main Street")))
                .andExpect(jsonPath("$[0].city", is("Anytown")))
                .andExpect(jsonPath("$[0].state", is("Anystate")))
                .andExpect(jsonPath("$[0].country", is("US")))
                .andExpect(jsonPath("$[0].zip", is("12345")))
                .andExpect(jsonPath("$[0].company", is("ABC Inc.")))
                .andExpect(jsonPath("$[0].position", is("CEO")))
                .andExpect(jsonPath("$[0].website", is("www.example.com")))
                .andExpect(jsonPath("$[0].twitter", is("twitter_handle")))
                .andExpect(jsonPath("$[0].facebook", is("facebook_url")))
                .andExpect(jsonPath("$[0].linkedin", is("linkedin_profile")))
                .andExpect(jsonPath("$[0].github", is("github_handle")))
                .andExpect(jsonPath("$[0].instagram", is("instagram_handle")))
                .andExpect(jsonPath("$[0].youtube", is("youtube_url")))
                .andExpect(jsonPath("$[0].tiktok", is("tiktok_handle")))
                .andExpect(jsonPath("$[0].snapchat", is("snapchat_handle")))
                .andExpect(jsonPath("$[0].twitch", is("twitch_handle")))
                .andExpect(jsonPath("$[0].other", is("other")))
                .andExpect(jsonPath("$[0].notes", is("notes")));
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        Long customerId = 1L;
        String customerIdString = customerId.toString();
        Customer customer = new Customer(customerId, "John Doe", "johndoe@example.com", "123456789",
                "123 Main Street", "Anytown", "Anystate", "US", "12345",
                "ABC Inc.", "CEO", "www.example.com", "twitter_handle", "facebook_url",
                "linkedin_profile", "github_handle", "instagram_handle", "youtube_url",
                "tiktok_handle", "snapchat_handle", "twitch_handle", "other", "notes",34);

        when(customerRepository.findById(customerIdString)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/v1/customer/{id}", customerId)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("johndoe@example.com")))
                .andExpect(jsonPath("$.phone", is("123456789")))
                .andExpect(jsonPath("$.address", is("123 Main Street")))
                .andExpect(jsonPath("$.city", is("Anytown")))
                .andExpect(jsonPath("$.state", is("Anystate")))
                .andExpect(jsonPath("$.country", is("US")))
                .andExpect(jsonPath("$.zip", is("12345")))
                .andExpect(jsonPath("$.company", is("ABC Inc.")))
                .andExpect(jsonPath("$.position", is("CEO")))
                .andExpect(jsonPath("$.website", is("www.example.com")))
                .andExpect(jsonPath("$.twitter", is("twitter_handle")))
                .andExpect(jsonPath("$.facebook", is("facebook_url")))
                .andExpect(jsonPath("$.linkedin", is("linkedin_profile")))
                .andExpect(jsonPath("$.github", is("github_handle")))
                .andExpect(jsonPath("$.instagram", is("instagram_handle")))
                .andExpect(jsonPath("$.youtube", is("youtube_url")))
                .andExpect(jsonPath("$.tiktok", is("tiktok_handle")))
                .andExpect(jsonPath("$.snapchat", is("snapchat_handle")))
                .andExpect(jsonPath("$.twitch", is("twitch_handle")))
                .andExpect(jsonPath("$.other", is("other")))
                .andExpect(jsonPath("$.notes", is("notes")));
    }

    @Test
    public void createCustomerTest() throws Exception {
        Customer customer = new Customer(null, "John Doe", "johndoe@example.com", "123456789",
                "123 Main Street", "Anytown", "Anystate", "US", "12345",
                "ABC Inc.", "CEO", "www.example.com", "twitter_handle", "facebook_url",
                "linkedin_profile", "github_handle", "instagram_handle", "youtube_url",
                "tiktok_handle", "snapchat_handle", "twitch_handle", "other", "notes",60);

        mockMvc.perform(post("/api/v1/customer")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated());
    }

     
    @Test
    public void givenInvalidCustomerId_UpdateCustomer_NotFound() throws Exception {
        // Given
        String url = "/customers/100";
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("janedoe@gmail.com");
        customer.setPhone("0987654321");
        customer.setCity("Newtown");
        customer.setState("Newstate");
        customer.setCountry("Newcountry");
        customer.setZip("54321");
        customer.setAge(40);
        String requestbody = "{ \"name\": \"" + customer.getName() + "\", \"email\": \"" + customer.getEmail() + "\", \"phone\": \"" + customer.getPhone() + "\", \"address\": \"" + customer.getAddress() + "\", \"city\": \"" + customer.getCity() + "\", \"state\": \"" + customer.getState() + "\", \"country\": \"" + customer.getCountry() + "\", \"zip\": \"" + customer.getZip() + "\", \"company\": \"" + customer.getCompany() + "\", \"position\": \"" + customer.getPosition() + "\", \"website\": \"" + customer.getWebsite() + "\", \"twitter\": \"" + customer.getTwitter() + "\", \"facebook\": \"" + customer.getFacebook() + "\", \"linkedin\": \"" + customer.getLinkedin() + "\", \"github\": \"" + customer.getGithub() + "\", \"instagram\": \"" + customer.getInstagram() + "\", \"youtube\": \"" + customer.getYoutube() + "\", \"tiktok\": \"" + customer.getTiktok() + "\", \"snapchat\": \"" + customer.getSnapchat() + "\", \"twitch\": \"" + customer.getTwitch() + "\", \"other\": \"" + customer.getOther() + "\", \"notes\": \"" + customer.getNotes() + "\", \"age\": " + customer.getAge() + " }";
        
        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        
        // Then
        assertEquals(404, mvcResult.getResponse().getStatus());
    }
   @Test
   public void updateCustomerTest() throws Exception {
       
        Long customerId = 1L;
        String customerIdString = customerId.toString();      
        Customer customer = new Customer(customerId, "Jane Doe", "johndoe@example.com", "123456789",
                "123 Main Street", "Anytown", "Anystate", "US", "12345",
                "ABC Inc.", "CEO", "www.example.com", "twitter_handle", "facebook_url",
                "linkedin_profile", "github_handle", "instagram_handle", "youtube_url",
                "tiktok_handle", "snapchat_handle", "twitch_handle", "other", "notes",30);
               
                when(customerRepository.findById(customerIdString)).thenReturn(Optional.of(customer));
                when(customerRepository.save(customer)).thenReturn(customer);

             

                 Customer body = customerController.updatecustomer(customer).getBody();
                 assertEquals(customer.getName(), body.getName());
    }

    @Test
    public void updateCustomerNameTest() throws Exception {
        Long customerId = 1L;
        String customerIdString = customerId.toString();
        String newName = "Jane Doe";
        Customer customer = new Customer(customerId, newName, "johndoe@example.com", "123456789",
                "123 Main Street", "Anytown", "Anystate", "US", "12345",
                "ABC Inc.", "CEO", "www.example.com", "twitter_handle", "facebook_url",
                "linkedin_profile", "github_handle", "instagram_handle", "youtube_url",
                "tiktok_handle", "snapchat_handle", "twitch_handle", "other", "notes",30);

        when(customerRepository.findById(customerIdString)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        mockMvc.perform(patch("/api/v1/customer/{id}", customerId)
                .contentType("application/json")
                .param("name", newName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newName)))
                .andExpect(jsonPath("$.email", is("johndoe@example.com")))
                .andExpect(jsonPath("$.phone", is("123456789")))
                .andExpect(jsonPath("$.address", is("123 Main Street")))
                .andExpect(jsonPath("$.city", is("Anytown")))
                .andExpect(jsonPath("$.state", is("Anystate")))
                .andExpect(jsonPath("$.country", is("US")))
                .andExpect(jsonPath("$.zip", is("12345")))
                .andExpect(jsonPath("$.company", is("ABC Inc.")))
                .andExpect(jsonPath("$.position", is("CEO")))
                .andExpect(jsonPath("$.website", is("www.example.com")))
                .andExpect(jsonPath("$.twitter", is("twitter_handle")))
                .andExpect(jsonPath("$.facebook", is("facebook_url")))
                .andExpect(jsonPath("$.linkedin", is("linkedin_profile")))
                .andExpect(jsonPath("$.github", is("github_handle")))
                .andExpect(jsonPath("$.instagram", is("instagram_handle")))
                .andExpect(jsonPath("$.youtube", is("youtube_url")))
                .andExpect(jsonPath("$.tiktok", is("tiktok_handle")))
                .andExpect(jsonPath("$.snapchat", is("snapchat_handle")))
                .andExpect(jsonPath("$.twitch", is("twitch_handle")))
                .andExpect(jsonPath("$.other", is("other")))
                .andExpect(jsonPath("$.notes", is("notes")));
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        Long customerId = 1L;
        String customerIdString = customerId.toString();
        Customer customer = new Customer(customerId, "John Doe", "johndoe@example.com", "123456789",
                "123 Main Street", "Anytown", "Anystate", "US", "12345",
                "ABC Inc.", "CEO", "www.example.com", "twitter_handle", "facebook_url",
                "linkedin_profile", "github_handle", "instagram_handle", "youtube_url",
                "tiktok_handle", "snapchat_handle", "twitch_handle", "other", "notes",18);
        
        //mock the real object with the mock object on spring context
        when(customerRepository.findById(customerIdString)).thenReturn(Optional.of(customer));

        mockMvc.perform(delete("/api/v1/customer/{id}", customerId)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}

