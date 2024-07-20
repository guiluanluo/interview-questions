package mc.microservices.graphql.resolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import mc.microservices.graphql.model.Account;
import mc.microservices.graphql.model.Bank;
import mc.microservices.graphql.repository.BankRepository;

@Component
public class AccountResolver implements GraphQLResolver<Account> {
	private static final Logger LOGGER = LogManager.getLogger(AccountResolver.class);
	
	@Autowired
	private BankRepository bankRepository;

	public AccountResolver(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	public Bank getBank(Account account) {
		LOGGER.info("getBank(): account:" + account);

		return bankRepository.findById(account.getBank().getId()).orElseThrow(null);
	}
}
