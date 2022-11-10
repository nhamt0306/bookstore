package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.AddressEntity;
import edu.hcmute.bookstore.repository.AddressRepository;
import edu.hcmute.bookstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Override
    public AddressEntity findAddressById(Long id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public AddressEntity save(AddressEntity address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressEntity> getAllByUserId(Long id) {
        return addressRepository.getAllByUserEntityId(id);
    }
}
