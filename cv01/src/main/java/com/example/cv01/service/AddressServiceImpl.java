package com.example.cv01.service;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import com.example.cv01.aspect.ResourceBodyFactory;
import com.example.cv01.entity.Address;
import com.example.cv01.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final static Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        LOG.info("AddressServiceImpl constructed");
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "addresses.json")
    @CacheEvict(value = "addresses", allEntries = true)
    public Address findBy(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for getting an Address");
            throw new IllegalArgumentException("No id for Address found.");
        }

        return addressRepository.findById(id).orElseThrow(() -> new NullPointerException("No author found for this id"));
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "addresses.json")
    @CachePut("addresses")
    public Address saveAddress(Address address) {
        if (address == null) {
            LOG.error("There was no address provided for saving an Address");
            throw new NullPointerException("No Address");
        }

        return addressRepository.save(address);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "addresses.json")
    @CacheEvict(value = "addresses", allEntries = true)
    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "addresses.json")
    public void deleteAddress(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for deleting an Address");
            throw new IllegalArgumentException("No id for Address found.");
        }

        addressRepository.deleteById(id);
    }


}
