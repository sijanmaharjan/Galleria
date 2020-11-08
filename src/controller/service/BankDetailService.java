package controller.service;

import bean.entity.User;

public interface BankDetailService {
    void createOrUpdateDetails(User user, String bankName, String accountName, String accountNumber);
}
