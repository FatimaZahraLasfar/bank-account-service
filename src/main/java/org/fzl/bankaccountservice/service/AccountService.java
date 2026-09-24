package org.fzl.bankaccountservice.service;

import org.fzl.bankaccountservice.dto.BankAccountRequestDTO;
import org.fzl.bankaccountservice.dto.BankAccountResponseDTO;
import org.fzl.bankaccountservice.entities.BankAccount;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO requestDTO);
    List<BankAccount> getAllAccounts();
    BankAccount getAccount(String id);
    BankAccount updateAccount(BankAccount bankAccount);
    void deleteAccount(String id);
}