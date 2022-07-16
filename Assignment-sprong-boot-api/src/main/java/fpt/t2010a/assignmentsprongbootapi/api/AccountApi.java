package fpt.t2010a.assignmentsprongbootapi.api;

import fpt.t2010a.assignmentsprongbootapi.entity.Account;
import fpt.t2010a.assignmentsprongbootapi.entity.dto.AccountDto;
import fpt.t2010a.assignmentsprongbootapi.entity.dto.CredentialDto;
import fpt.t2010a.assignmentsprongbootapi.entity.dto.LoginDto;
import fpt.t2010a.assignmentsprongbootapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api/v1/accounts")
@RequiredArgsConstructor
public class AccountApi {
    final AccountService accountService;

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountDto accountDto) {
        if (!accountDto.getPassword().equals(accountDto.getRePass())){
            return ResponseEntity.badRequest().body("password not match");
        }
        Account account = accountService.register(accountDto);
        if (account == null) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        UserDetails userDetail = accountService.loadUserByUsername(loginDto.getUsername());
        if (userDetail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account not found");
        }
        if (accountService.matchPassword(loginDto.getPassword(), userDetail.getPassword())) {
            CredentialDto credentialDto = accountService.generateCredential(userDetail, request);
            return ResponseEntity.status(HttpStatus.OK).body(credentialDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login fails");
    }
}
