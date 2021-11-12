package hu.webuni.hr.szabi.repository;

import hu.webuni.hr.szabi.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    @NonNull
    Optional<Position> findByPositionNameIgnoreCase(String positionName);


}
