package pt.edge.performancescanner.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.edge.performancescanner.domain.Report;
import pt.edge.performancescanner.domain.User;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

	List<Report> findByUserOrderByWeekDesc(User user);

	List<Report> findByWeek(int week);

	Report findByUserAndWeek(User user, int week);
}
