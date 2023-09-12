package cn.codegraffiti.alone.finance.repository;

import cn.codegraffiti.alone.finance.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

    @Query(value = "SELECT f FROM Flow f ORDER BY f.createTime DESC LIMIT 1")
    Flow last();

}
