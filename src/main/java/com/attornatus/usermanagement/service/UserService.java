package com.attornatus.usermanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.attornatus.usermanagement.entities.Address;
import com.attornatus.usermanagement.entities.User;
import com.attornatus.usermanagement.repository.AddressRepository;
import com.attornatus.usermanagement.repository.UserRepository;
import com.attornatus.usermanagement.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	public UserService() {
	}

	public User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Não existe um usuário com código %d", id)));
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	// Buscar todos os endereços de um user
	public List<Address> findAddresses(Long userId) {
		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Não existe um usuário com código %d", userId));
		} else {
			return userOpt.get().getAddresses();
		}
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User saveAddress(Long userId, Address address) {
		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Não existe um usuário com código %d", userId));
		} else {
			User user = userOpt.get();
			user.addAdress(address);
			return saveUser(user);
		}
	}

	public User updateUser(Long id, User newUser) {

		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Não existe um usuário com código %d", id));
		} else {
			User oldUser = userOpt.get();
			newUser.setId(oldUser.getId());
			return saveUser(newUser);

		}
	}

	public Address activateMainAddress(@PathVariable Long addressId) {
		Optional<Address> addressOpt = addressRepository.findById(addressId);

		if (addressOpt.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Não existe um endereço com código %d", addressId));
		} else {
			Address address = addressOpt.get();
			address.activeMainAddress();
			return address;
		}

	}

}
