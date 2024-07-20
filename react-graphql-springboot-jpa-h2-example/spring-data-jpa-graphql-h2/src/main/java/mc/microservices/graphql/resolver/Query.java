package mc.microservices.graphql.resolver;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import mc.microservices.graphql.model.Account;
import mc.microservices.graphql.model.Bank;
import mc.microservices.graphql.repository.AccountRepository;
import mc.microservices.graphql.repository.BankRepository;

@Component
public class Query implements GraphQLQueryResolver {
	private static final Logger LOGGER = LogManager.getLogger(Query.class);

	private BankRepository bankRepository;
	private AccountRepository accountRepository;

	@Autowired
	public Query(BankRepository bankRepository, AccountRepository accountRepository) {
		this.bankRepository = bankRepository;
		this.accountRepository = accountRepository;
	}

	public Iterable<Bank> findAllBanks() {
		LOGGER.info("findAllBanks()");

		return bankRepository.findAll();
	}
	
	public List<Account> findAllAccounts() {
		LOGGER.info("findAllAccounts()");

		List<Account> accounts = accountRepository.findAll();
//		for(Account account: accounts) {
//			LOGGER.info("findAllAccounts(), account:" + account);
//		}
		LOGGER.info("findAllAccounts() return account:" + accounts.size());

		return accounts;
	}

//	public Iterable<Account> findAllAccounts() {
//		LOGGER.info("findAllAccounts()");
//
//		List<Account> accounts = accountRepository.findAll();
////		for(Account account: accounts) {
////			LOGGER.info("findAllAccounts(), account:" + account);
////		}
//		LOGGER.info("findAllAccounts() return account:" + accounts.size());
//
//		return accounts;
//	}

	public long countBanks() {
		LOGGER.info("countBanks()");

		return bankRepository.count();
	}

	public long countAccounts() {
		LOGGER.info("countAccounts()");

		return accountRepository.count();
	}

}
