package mc.microservices.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mc.microservices.graphql.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
