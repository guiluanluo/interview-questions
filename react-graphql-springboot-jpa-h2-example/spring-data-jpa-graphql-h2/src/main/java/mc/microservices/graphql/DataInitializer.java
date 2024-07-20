package mc.microservices.graphql;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import mc.microservices.graphql.model.Bank;
import mc.microservices.graphql.resolver.Mutation;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private Mutation mutation;

	@Override
	public void run(String... args) {

		Bank bank1 = mutation.createBank("CF Bank1", 123456789);
		mutation.createAccount(new BigDecimal(10.00), "CFB1 account11", bank1.getId());
		mutation.createAccount(new BigDecimal(11.00), "CFB1 account12", bank1.getId());
		mutation.createAccount(new BigDecimal(12.00), "CFB1 account13", bank1.getId());
		mutation.createAccount(new BigDecimal(13.00), "CFB1 account14", bank1.getId());
		
		
		Bank bank2 = mutation.createBank("CF Bank2", 223456789);
		mutation.createAccount(new BigDecimal(20.00), "CFB2 account21", bank2.getId());
		mutation.createAccount(new BigDecimal(21.00), "CFB2 account22", bank2.getId());
		mutation.createAccount(new BigDecimal(22.00), "CFB2 account23", bank2.getId());
		mutation.createAccount(new BigDecimal(23.00), "CFB2 account24", bank2.getId());
		
		
		Bank bank3 = mutation.createBank("CF Bank3", 333456789);
		mutation.createAccount(new BigDecimal(30.00), "CFB3 account31", bank3.getId());
		mutation.createAccount(new BigDecimal(31.00), "CFB3 account32", bank3.getId());
		mutation.createAccount(new BigDecimal(32.00), "CFB3 account33", bank3.getId());
		mutation.createAccount(new BigDecimal(33.00), "CFB3 account34", bank3.getId());
		
	}

}