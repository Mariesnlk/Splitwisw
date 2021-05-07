package com.mariia.syne.splitwise.service;

import com.mariia.syne.splitwise.entity.Transactions;
import com.mariia.syne.splitwise.entity.Users;
import com.mariia.syne.splitwise.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;


    public List<Transactions> getAllTransactions() {

        List<Transactions> transactions = new ArrayList<>();
        transactionsRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public List<Transactions> getRegularTransactionsByUserAndMonth(Integer user_id) {
        Users user = new Users();
        user.setId_users(user_id);
        List<Transactions> reg = transactionsRepository.getAllByIdUser(user).stream().
                filter(t -> transactionFilter(t)).collect(Collectors.toList());

        return reg;
    }

    public boolean transactionFilter(Transactions t) {
        if(t.getId_type_transaction().getId_type_transaction() == 2){
        Calendar from = Calendar.getInstance();
        from.setTime(t.getPeriod_from());
        Calendar to = Calendar.getInstance();
        to.setTime(t.getPeriod_to());
        Calendar now=Calendar.getInstance();
        now.setTime(new Date());
        return  from.before(now) && to.after(now);}
return false;

    }

    public List<String> getDescription() {
        List<String> transactions = new ArrayList<>();
        transactionsRepository.getDescription().forEach(transactions::add);
        return transactions;
    }

    public Transactions getTransaction(Integer id) {
        return transactionsRepository.findById(id).orElse(null);
    }

    public List<Transactions> getTransactionsByUser(Integer user_id) {
        Users user = new Users();
        user.setId_users(user_id);
        return transactionsRepository.getAllByIdUser(user);
    }

    public Double getSumUserTransactions(Integer user_id) {

        double sumIrreg = transactionsRepository.getSumUserTransactions(user_id);
        List<Transactions> regTransactions = transactionsRepository.getAllByIdUser(new Users(user_id)).stream().
                filter(t -> t.getId_type_transaction().getId_type_transaction() == 2).collect(Collectors.toList());
        double identity = 0;


        double reg = regTransactions.stream().reduce(identity, (sum, y) -> sum + getSumByRegular(y), Double::sum);

        return sumIrreg + reg;
    }

    public double getSumByRegular(Transactions t) {
        Calendar cal = Calendar.getInstance();
        Calendar calnow = Calendar.getInstance();
        cal.setTime(t.getPeriod_from());
        calnow.setTime(new Date());
        int month = calnow.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
        return t.getSum() * month;


    }

    public Double getSumGroupTransactions(Integer group_id) {
        return transactionsRepository.getSumUserGroupTransactions(group_id) +
                transactionsRepository.findAllTransactionsByGroup(group_id).stream().
                        filter(t -> t.getId_type_transaction().getId_type_transaction() == 2).
                        reduce(0.0, (sum, y) -> sum + getSumByRegular(y), Double::sum);
    }

    public List<Transactions> findAllByDateBetweenByIdUser(Integer user_id, Date start, Date end) {
        return transactionsRepository.findAllByDateBetween(start, end).stream().
                filter(elem -> elem.getIdUser().getId_users().equals(user_id)).collect(Collectors.toList());
    }

    public List<Transactions> findAllByDateBetweenByIdGroup(Integer group_id, Date start, Date end) {
        return transactionsRepository.findAllByDateBetween(start, end).stream().
                filter(elem -> elem.getIdUser().getId_group().getId_groups().equals(group_id)).collect(Collectors.toList());
    }

    public void addTransaction(Transactions transactions) {

        transactionsRepository.save(transactions);
    }

    public void updateTransaction(Transactions transactions, Integer id) {

        transactionsRepository.save(transactions);
    }

    public void deleteTransaction(Integer id) {
        transactionsRepository.deleteById(id);
    }

    public List<Transactions> getTransactionsByGroup(Integer id) {
        return transactionsRepository.findAllTransactionsByGroup(id);
    }
}
