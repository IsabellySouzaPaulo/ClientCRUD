package com.tads.clientcrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tads.clientcrud.dto.ClientDTO;
import com.tads.clientcrud.entity.Client;
import com.tads.clientcrud.repository.ClientRepository;
import com.tads.clientcrud.service.exception.DatabaseException;
import com.tads.clientcrud.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

// SERVICE é onde fica a regra de negócio
@Service
public class ClientService {

	// O Spring injeta o repository automaticamente
	@Autowired
	private ClientRepository repository;

	// BUSCA PAGINADA devolve uma página de clientes em vez de todos de uma vez

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		// map converte cada Client da página em ClientDTO
		return list.map(x -> new ClientDTO(x));
	}

	// BUSCA POR ID se não existir, lança ResourceNotFoundException (404)
	@Transactional
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found!!"));
		return new ClientDTO(entity);
	}

	// INSERIR cria um Client novo, copia os dados do DTO e salva
	// O id  o banco gera sozinho.
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	// ATUALIZAR pega uma referência ao cliente do id informado, troca os dados e salva

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found! " + id);
		}
	}

	// DELETAR paga pelo id
	// EmptyResultDataAccessException = id não existe -> 404
	// DataIntegrityViolationException = não pode apagar por caus de vínculo no banco
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id Not Found! " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
