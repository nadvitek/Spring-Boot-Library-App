package com.example.cv01.controller;

import com.example.cv01.dto.AddressDTO;
import com.example.cv01.dto.DTOMapper;
import com.example.cv01.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final static Logger LOG = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    public AddressController(AddressService addressService) {
        LOG.info("AddressController initialization");
        this.addressService = addressService;
    }

    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
    @GetMapping("/address/{id}")
    public ResponseEntity<AddressDTO> getAddressEntity(@PathVariable Long id) {
        return ResponseEntity.ok(DTOMapper.INSTANCE.addressToDTO(addressService.findBy(id)));
    }
}
