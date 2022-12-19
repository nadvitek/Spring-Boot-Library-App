package com.example.cv01.service;

import com.example.cv01.entity.Address;
import com.example.cv01.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;

    @BeforeEach
    public void setup(){
        address = Address.builder()
                .id(1L)
                .street("Osvobození")
                .number("1")
                .town("Litoměřice")
                .psc("41201")
                .build();
    }

    @DisplayName("JUnit test for finding Address by id method")
    @Test
    public void saveAddress_givenAddressSaved_returnsAddress(){
        //ARRANGE
        given(addressRepository.save(address)).willReturn(address);

        //ACT
        Address savedAddress = addressService.saveAddress(address);

        //ASSERT
        assertThat(savedAddress).isNotNull();
    }
    @DisplayName("JUnit test for saveAddress method saving null throws Exception")
    @Test
    public void saveAddress_savingNullAddress_throwsException(){
        //ARRANGE

        //ACT
        assertThrows(NullPointerException.class, () -> addressService.saveAddress(null));

        //ASSERT
        verify(addressRepository, never()).save(any(Address.class));
    }

    @DisplayName("JUnit test for getAllAddresses method")
    @Test
    public void getAllAddresses_haveListOfTwo_getsListOfTwo(){
        //ARRANGE

        Address address1 = Address.builder()
                .id(2L)
                .street("Husova")
                .number("2")
                .town("Litoměřice")
                .psc("41201")
                .build();

        given(addressRepository.findAll()).willReturn(List.of(address, address1));

        //ACT
        List<Address> addressList = addressService.getAllAddresses();

        //ASSERT
        assertThat(addressList).isNotNull();
        assertThat(addressList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findById_whenGetAddressById_thenReturnAddressObject(){
        //ARRANGE
        given(addressRepository.findById(1L)).willReturn(Optional.of(address));

        //ACT
        Address savedAddress = addressService.findBy(address.getId());

        //ASSERT
        assertThat(savedAddress).isNotNull();
    }

    @DisplayName("JUnit test for updateAddress method")
    @Test
    public void updateAddress_changesAttributes_returnsUpdatedAddress(){
        //ARRANGE
        given(addressRepository.save(address)).willReturn(address);
        address.setTown("Praha");
        address.setPsc("15800");

        //ACT
        Address updatedAddress = addressService.saveAddress(address);

        //ASSERT
        assertThat(updatedAddress.getTown()).isEqualTo("Praha");
        assertThat(updatedAddress.getPsc()).isEqualTo("15800");
    }

    @DisplayName("JUnit test for deleteAddress method")
    @Test
    public void givenAddressId_whenDeleteAddress_thenNothing(){
        //ARRANGE
        long addressId = 1L;

        willDoNothing().given(addressRepository).deleteById(addressId);

        //ACT
        addressService.deleteAddress(addressId);

        //ASSERT
        verify(addressRepository, times(1)).deleteById(addressId);
    }



}
