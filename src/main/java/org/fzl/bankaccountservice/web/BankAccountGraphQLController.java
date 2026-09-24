package org.fzl.bankaccountservice.web;

import org.fzl.bankaccountservice.entities.BankAccount;
import org.fzl.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @QueryMapping
    public List<BankAccount> accountsList (){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById (@Argument String id){
        return bankAccountRepository.findById( id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    public BankAccount addAccount(@Argument BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }
}
record BankAccountDTO (Double balance,String type, String currency){
}
