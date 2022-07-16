package fpt.t2010a.assignmentsprongbootapi.repository;

import fpt.t2010a.assignmentsprongbootapi.entity.Account;
import fpt.t2010a.assignmentsprongbootapi.entity.myenum.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
//    Page<Account> findAllByFirstNameOrLastNameOrAddressOrEmailOrPhoneOrUsernameContains(String search, Pageable pageable);
    Page<Account> findAllByStatusEquals(AccountStatus status, Pageable pageable);
    Page<Account> findAllByRoleEquals(String role, Pageable pageable);
    Page<Account> findAllByCreateAtBetween(LocalDateTime min, LocalDateTime max, Pageable pageable);
    Page<Account> findAllByUpdateAtBetween(LocalDateTime min, LocalDateTime max, Pageable pageable);
    Page<Account> findAllByDeleteAtBetween(LocalDateTime min, LocalDateTime max, Pageable pageable);
    Page<Account> findAll(Pageable pageable);
}
