package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.AddressEntity;

import java.util.List;

public interface AddressService {
    AddressEntity findAddressById(Long id);
    AddressEntity save(AddressEntity address);
    void delete(Long id);
    List<AddressEntity> getAllByUserId(Long id);
    AddressEntity getAddressDefaultOfUser(Long id);
}
