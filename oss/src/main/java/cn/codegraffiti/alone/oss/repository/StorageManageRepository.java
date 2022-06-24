package cn.codegraffiti.alone.oss.repository;

import cn.codegraffiti.alone.oss.entity.StorageManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface StorageManageRepository extends JpaRepository<StorageManage, Long> {

    /**
     * 查询过期记录
     */
    @Query("SELECT s FROM StorageManage AS s WHERE s.createTime >= ?1")
    List<StorageManage> findExpiredList(@Param("time") LocalDateTime time);

}
