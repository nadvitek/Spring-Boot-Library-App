package com.example.cv01.service;

import com.example.cv01.entity.Address;

import java.util.List;

public interface AddressService{
    Address findBy(Long id);
    List<Address> getAllAddresses();
    Address saveAddress(Address address);
    void deleteAddress(Long id);
}
