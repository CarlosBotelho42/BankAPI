package com.carlos.bankAPI.services;

import com.carlos.bankAPI.entities.User;
import com.carlos.bankAPI.repositories.UserRepository;
import com.carlos.bankAPI.services.exceptions.ConstraintException;
import com.carlos.bankAPI.services.exceptions.CpfAlreadyExistException;
import com.carlos.bankAPI.services.exceptions.DatabaseException;
import com.carlos.bankAPI.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {

        try {
          return  repository.save(obj);
        }
        catch (DataIntegrityViolationException e){
            throw new CpfAlreadyExistException(e.getMessage());
        }
        catch (ConstraintViolationException e){
            throw new ConstraintException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw  new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            User entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw  new ResourceNotFoundException(id);
        }

    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setCpf(obj.getCpf());
        entity.setDateOfBirth(obj.getDateOfBirth());
    }

}
