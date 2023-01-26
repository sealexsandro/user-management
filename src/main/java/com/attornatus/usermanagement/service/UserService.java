package com.attornatus.usermanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User saveUser(User user) {
		return userRepository.save(user);
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

	// Buscar todos os endereços de um user
	public List<Address> findAddresses(Long userId) {
		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Não existe um usuário com código %d", userId));
		} else {
			return userOpt.get().getAddresses();
		}
	}

	// Buscar um endereço por ID
	public Address findAddressById(Long addressId) {
		Optional<Address> address = addressRepository.findById(addressId);

		if (address.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Não existe um endereço com código %d", addressId));
		} else {
			return address.get();
		}
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

	/*
	 * No metodo abaixo, além de ativar o endereço principal, eu busco a lista de
	 * endereços do dono do endereço que se quer ativar, para que, se houver outro
	 * endereço ativado/marcado como principal, ele será desativado, fazendo com que
	 * fique apenas um endereço principal
	 */
	public Address activateMainAddress(Long userId, Long addressId) {

		try {
			User user = findUserById(userId);
			Address address = findAddressById(addressId);

			for (Address addr : user.getAddresses()) {
				if (!addr.equals(address) && addr.isActive()) {
					addr.deactivateMainAddress();
				}
			}
			address.activeMainAddress();
			return addressRepository.save(address);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	public Address findActiveAddress(Long uderId) {

		try {
			User user = findUserById(uderId);
			Address address = null;
			for (Address addr : user.getAddresses()) {
				if (addr.isActive()) {
					address = addr;
					break;
				}
			}

			if (address != null) {
				return address;
			} else {
				throw new ResourceNotFoundException(
						String.format("Não existe um endereço Principal para este usuário"));
			}
		} catch (ResourceNotFoundException ex) {
			throw ex;
		}

	}

}
