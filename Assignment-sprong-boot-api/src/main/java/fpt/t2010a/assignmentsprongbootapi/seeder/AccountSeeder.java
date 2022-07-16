package fpt.t2010a.assignmentsprongbootapi.seeder;

import fpt.t2010a.assignmentsprongbootapi.entity.Account;
import fpt.t2010a.assignmentsprongbootapi.entity.myenum.AccountStatus;
import fpt.t2010a.assignmentsprongbootapi.repository.AccountRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountSeeder {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    Faker faker = new Faker();
    public static List<Account> accountList = new ArrayList<>();
    public static final int NUMBER_OF_ACCOUNT = 10;
    public void generate(){
        for(int i = 0; i < NUMBER_OF_ACCOUNT; i++){
            Account account = new Account();
            account.setId(UUID.randomUUID().toString());
            account.setAddress(faker.address().fullAddress());
            account.setRole("USER");
            account.setDetail(faker.lorem().sentence());
            account.setEmail(faker.internet().emailAddress());
            account.setFirstName(faker.name().firstName());
            account.setLastName(faker.name().lastName());
            account.setUsername(faker.leagueOfLegends().champion());
            account.setPassword(passwordEncoder.encode(faker.artist().name()));
            account.setPhone(faker.phoneNumber().cellPhone());
            account.setThumbnail(faker.avatar().image());
            account.setStatus(AccountStatus.ACTIVE);
            accountList.add(account);
        }
        accountRepository.saveAll(accountList);
    }
}
