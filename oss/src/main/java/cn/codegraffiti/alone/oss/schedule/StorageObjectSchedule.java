package cn.codegraffiti.alone.oss.schedule;

import cn.codegraffiti.alone.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageObjectSchedule {

    final OssService ossService;

    /**
     * 清理过期文件
     */
    @Scheduled(fixedRate = 3600 * 24 * 3 * 1000)   // 每三天执行一次
    private void clearingExpiredObject() {
        this.ossService.clearingExpiredObject();
    }
}