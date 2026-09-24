package org.fzl.bankaccountservice.mappers;

import org.fzl.bankaccountservice.dto.BankAccountRequestDTO;
import org.fzl.bankaccountservice.dto.BankAccountResponseDTO;
import org.fzl.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return  bankAccountResponseDTO;
    };
}
