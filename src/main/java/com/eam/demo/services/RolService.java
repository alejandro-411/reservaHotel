package com.eam.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eam.demo.models.Rol;
import com.eam.demo.repository.IRolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private IRolRepository rolRepository;

    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> findRolById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol updateRol(Long id, Rol rolDetails) {
        return rolRepository.findById(id)
                .map(rol -> {
                    rol.setRolName(rolDetails.getRolName());
                    return rolRepository.save(rol);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public boolean deleteRol(Long id) {
        return rolRepository.findById(id)
                .map(rol -> {
                    rolRepository.delete(rol);
                    return true;
                })
                .orElse(false);
    }
}
