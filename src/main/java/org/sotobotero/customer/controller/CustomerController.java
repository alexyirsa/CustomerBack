package org.sotobotero.customer.controller;

import org.sotobotero.customer.entities.Customer;
import org.sotobotero.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "customer API", description = "customer API")
@CrossOrigin(origins = "*")
public class CustomerController {
    // generate all methos for a rest appi with spring boot for a customer entity
    @Autowired
    private CustomerRepository prsRepository;

    @Value( "${db.password}" )
     private String DB_PASSWORD;
    
     @Operation(summary = "Test property")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Found the customers"),
             @ApiResponse(responseCode = "404", description = "Not found the customers"),
     })
    @GetMapping("/testproperty")
    public ResponseEntity<String> getTestValue() {    
        return new ResponseEntity<>(DB_PASSWORD, HttpStatus.OK);
    }

    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customers"),
            @ApiResponse(responseCode = "404", description = "Not found the customers"),
    })
    @GetMapping
    public ResponseEntity<List<Customer>> getAllcustomers() {
        List<Customer> customers = prsRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Get a customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer"),
            @ApiResponse(responseCode = "404", description = "Not found the customer"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getcustomerById(@PathVariable("id") Long id) {
        Optional<Customer> customer = prsRepository.findById(id.toString());
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create the customer"),
            @ApiResponse(responseCode = "404", description = "Not create the customer"),
    })  

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createcustomer(@RequestBody Customer customer) {
        Customer newcustomer = prsRepository.save(customer);
        return new ResponseEntity<>(newcustomer, HttpStatus.CREATED);
    }
//generate update method for org.sotobotero.customer.entities.Customer entity
    @Operation(summary = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the customer"),
            @ApiResponse(responseCode = "404", description = "Not update the customer"),
    })
  /*
take care with swagger code generator if you does not indicate here @RequestBody anotation, 
the generate code no take body payload and yo could get this exception "Required request body is missing: 
public org.springframework.http.ResponseEntity<org.sotobotero.customer.entities.Customer> 
org.sotobotero.customer.controller.CustomerController.createcustomer(org.sotobotero.customer.entities.Customer)"
*/
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updatecustomer(@RequestBody Customer customer) {
        Optional<Customer> optionalcustomer = prsRepository.findById(customer.getId().toString());
        if (optionalcustomer.isPresent()) {
            Customer newcustomer = optionalcustomer.get();
            newcustomer.setName(customer.getName());
            newcustomer.setEmail(customer.getEmail());
            newcustomer.setPhone(customer.getPhone());
            newcustomer.setAddress(customer.getAddress());
            newcustomer.setCity(customer.getCity());
            newcustomer.setState(customer.getState());
            newcustomer.setCountry(customer.getCountry());
            newcustomer.setZip(customer.getZip());
            newcustomer.setCompany(customer.getCompany());
            newcustomer.setPosition(customer.getPosition());
            newcustomer.setWebsite(customer.getWebsite());
            newcustomer.setTwitter(customer.getTwitter());
            newcustomer.setFacebook(customer.getFacebook());
            newcustomer.setLinkedin(customer.getLinkedin());
            newcustomer.setGithub(customer.getGithub());
            newcustomer.setInstagram(customer.getInstagram());
            newcustomer.setYoutube(customer.getYoutube());
            newcustomer.setTiktok(customer.getTiktok());
            newcustomer.setSnapchat(customer.getSnapchat());
            newcustomer.setTwitch(customer.getTwitch());
            newcustomer.setOther(customer.getOther());
            newcustomer.setNotes(customer.getNotes());
            newcustomer.setAge(customer.getAge());
            newcustomer = prsRepository.save(newcustomer);
            return new ResponseEntity<>(newcustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//generate update method for name attribute of org.sotobotero.customer.entities.Customer entity
    @Operation(summary = "Update a customer name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the customer name"),
            @ApiResponse(responseCode = "404", description = "Not update the customer name"),
    })
  


    @PatchMapping("/{id}")
    public ResponseEntity<?> updatecustomerName(String name, @PathVariable("id") Long id) {
        Optional<Customer> optionalcustomer = prsRepository.findById(id.toString());
        if (optionalcustomer.isPresent()) {
            Customer newcustomer = optionalcustomer.get();
            newcustomer.setName(name);
            newcustomer = prsRepository.save(newcustomer);
            return new ResponseEntity<>(newcustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deletecustomer(@PathVariable("id") Long id) {
        Optional<Customer> optionalcustomer = prsRepository.findById(id.toString());
        if (optionalcustomer.isPresent()) {
            prsRepository.delete(optionalcustomer.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }  

}