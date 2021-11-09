package hu.webuni.hr.szabi.repository;

import hu.webuni.hr.szabi.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
