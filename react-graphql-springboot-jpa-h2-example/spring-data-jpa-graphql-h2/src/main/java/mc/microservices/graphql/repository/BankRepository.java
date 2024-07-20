package mc.microservices.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mc.microservices.graphql.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}