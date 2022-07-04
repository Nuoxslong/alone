package cn.codegraffiti.alone.finance.repository;

import cn.codegraffiti.alone.finance.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

}
