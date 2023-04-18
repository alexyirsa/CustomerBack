package org.sotobotero.customer.repository;

import org.sotobotero.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String> {


}
