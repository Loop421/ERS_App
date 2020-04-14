package com.ex.persistence;

import java.util.List;

public interface Persistable<T, Account>
{
    T getByAccount(Account account);
    List<T> getAll();
    Account save(T obj);
    void update(T obj);
    void delete(T obj);
    void persist();
}
