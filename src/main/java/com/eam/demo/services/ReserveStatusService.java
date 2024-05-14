package com.eam.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eam.demo.models.ReserveStatus;
import com.eam.demo.repository.IReserveStatusRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReserveStatusService {

    @Autowired
    private IReserveStatusRepository reserveStatusRepository;

    public List<ReserveStatus> findAllReserveStatuses() {
        return reserveStatusRepository.findAll();
    }

    public Optional<ReserveStatus> findReserveStatusById(Long id) {
        return reserveStatusRepository.findById(id);
    }

    public ReserveStatus createReserveStatus(ReserveStatus reserveStatus) {
        return reserveStatusRepository.save(reserveStatus);
    }

    public ReserveStatus updateReserveStatus(Long id, ReserveStatus reserveStatusDetails) {
        return reserveStatusRepository.findById(id)
                .map(status -> {
                    status.setNameReserveStatus(reserveStatusDetails.getNameReserveStatus());
                    return reserveStatusRepository.save(status);
                })
                .orElseThrow(() -> new RuntimeException("Reserve Status not found with id " + id));
    }

    public boolean deleteReserveStatus(Long id) {
        return reserveStatusRepository.findById(id)
                .map(status -> {
                    reserveStatusRepository.delete(status);
                    return true;
                })
                .orElse(false);
    }
}

