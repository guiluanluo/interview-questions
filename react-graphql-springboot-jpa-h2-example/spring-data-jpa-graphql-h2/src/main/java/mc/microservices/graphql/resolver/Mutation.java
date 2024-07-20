package mc.microservices.graphql.resolver;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import mc.microservices.graphql.model.Account;
import mc.microservices.graphql.model.Bank;
import mc.microservices.graphql.repository.AccountRepository;
import mc.microservices.graphql.repository.BankRepository;

@Component
public class Mutation implements GraphQLMutationResolver {
	private static final Logger LOGGER = LogManager.getLogger(Mutation.class);

	private BankRepository bankRepository;
	private AccountRepository accountRepository;

	@Autowired
	public Mutation(BankRepository bankRepository, AccountRepository accountRepository) {
		this.bankRepository = bankRepository;
		this.accountRepository = accountRepository;
	}

	public Bank createBank(String name, Integer routing) {
		LOGGER.info("createBank()");

		Bank bank = new Bank();
		bank.setName(name);
		bank.setRouting(routing);
		bankRepository.save(bank);
		return bank;
	}

	public Account createAccount(BigDecimal balance, String description, Long bankId) {
		LOGGER.info("createAccount()");

		Account book = new Account();
		book.setBank(new Bank(bankId));
		book.setBalance(balance);
		book.setDescription(description);
		accountRepository.save(book);
		return book;
	}

	public boolean deleteAccount(Long id) {
		LOGGER.info("deleteAccount()");

		accountRepository.deleteById(id);
		return true;
	}

	public Account updateAccount(Long id, BigDecimal balance, String description) throws Exception {
		LOGGER.info("updateAccount()");

		Optional<Account> optionalAccount = accountRepository.findById(id);
		if (optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			if (balance != null)
				account.setBalance(balance);
			if (description != null)
				account.setDescription(description);
			accountRepository.save(account);
			return account;
		}
		throw new Exception("No account found to update.");
	}
}
