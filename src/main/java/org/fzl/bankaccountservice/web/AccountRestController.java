package org.fzl.bankaccountservice.web;

import org.fzl.bankaccountservice.entities.BankAccount;
import org.fzl.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts (){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts (@PathVariable String id){
        return bankAccountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        if (bankAccount.getId() == null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository
                .findById(id)
                .orElseThrow();
        if (bankAccount.getBalance() != null ) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() != null ) account.setCreatedAt(new Date());
        if (bankAccount.getType() != null ) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency() != null ) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }

}
