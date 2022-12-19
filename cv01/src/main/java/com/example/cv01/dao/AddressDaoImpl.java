package com.example.cv01.dao;

import com.example.cv01.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public AddressDaoImpl() {
        emf = Persistence.createEntityManagerFactory("addresses");
        entityManager = emf.createEntityManager();
    }

    public Address create(String street, String number, String town, String psc, Library library, Publisher publisher) {
        return new Address(street, number, town, psc, library, publisher);
    }

    @Override
    public Optional<Address> read(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Override
    public List<Address> readAll() {
        //Query query = entityManager.createQuery("SELECT e FROM Book");
        //return query.getResultList();
        return null;
    }

    @Override
    public void update(Address value, Long id) {
        Address address = read(id).get();
        if (address != null) {
            if (value.getStreet() != null) {
                address.setStreet(value.getStreet());
            }
            if (value.getNumber() != null) {
                address.setNumber(value.getNumber());
            }
            if (value.getTown() != null) {
                address.setTown(value.getTown());
            }
            if (value.getPsc() != null) {
                address.setPsc(value.getPsc());
            }
            if (value.getLibrary() != null) {
                address.setLibrary(value.getLibrary());
            }
            if (value.getPublisher() != null) {
                address.setPublisher(value.getPublisher());
            }
        }
    }

    @Override
    public void delete(Long id) {
        Address address = entityManager.find(Address.class, id);
        entityManager.remove(address);
    }
}
