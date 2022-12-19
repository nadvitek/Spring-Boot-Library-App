package com.example.cv01.dao;

import com.example.cv01.entity.Address;
import com.example.cv01.entity.Library;
import com.example.cv01.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface AddressDao {
    public Address create(String street, String number, String town, String psc, Library library, Publisher publisher);
    public Optional<Address> read(Long id);
    public List<Address> readAll();
    public void update(Address value, Long id);
    public void delete(Long id);
}
