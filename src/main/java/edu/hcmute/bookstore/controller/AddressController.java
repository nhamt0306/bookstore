package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.model.AddressEntity;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class AddressController {
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    UserDetailService userDetailService;


    @GetMapping("/user/address/getAll")
    public Object getAllAdressByUser()
    {
        return addressService.getAllByUserId(userDetailService.getCurrentUser().getId());
    }

    @PostMapping("/user/address/create")
    public Object createAddress(@RequestBody AddressEntity address) throws ParseException {
        address.setUserEntity(userDetailService.getCurrentUser());
        return addressService.save(address);
    }

    @PostMapping("/user/address/change")
    public Object changAddress(@RequestBody AddressEntity address) throws ParseException {
        AddressEntity addressEntity = addressService.findAddressById(address.getId());
        if (!address.getAddress().equals(null))
        {
            addressEntity.setAddress(address.getAddress());
        }
        else if (!address.getPhoneNumber().equals(null))
        {
            addressEntity.setPhoneNumber(address.getPhoneNumber());
        }
        else if (!address.getName().equals(null))
        {
            addressEntity.setName(address.getName());
        }
        address.setUserEntity(userDetailService.getCurrentUser());
        addressService.save(addressEntity);
        return "Update address success!";
    }

    @DeleteMapping("/user/address/{id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable long id)
    {
        addressService.delete(id);
        return ResponseEntity.ok("Delete address success!");
    }

    @PostMapping("/user/address/setDefault/{id}")
    public ResponseEntity<?> setAddressDefault(@PathVariable long id)
    {
        List<AddressEntity> addressEntities = addressService.getAllByUserId(userDetailService.getCurrentUser().getId());
        for (AddressEntity address: addressEntities)
        {
            address.setAdd_default("1");
            addressService.save(address);
        }
        AddressEntity addressDefault = addressService.findAddressById(id);
        addressDefault.setAdd_default("0");
        addressService.save(addressDefault);
        return ResponseEntity.ok("Set address default success!");
    }
}
