package cn.codegraffiti.alone.oss.service;

import cn.codegraffiti.alone.core.AloneException;
import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.oss.domain.StorageObject;
import cn.codegraffiti.alone.oss.entity.StorageManage;
import cn.codegraffiti.alone.oss.enums.StateEnum;
import cn.codegraffiti.alone.oss.enums.storage.PropertyEnum;
import cn.codegraffiti.alone.oss.repository.StorageManageRepository;
import cn.hutool.crypto.digest.DigestUtil;
import io.minio.DeleteObjectTagsArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OssService {

    final MinioClient minioClient;

    final StorageManageRepository storageManageRepository;

    public R<String> tempStorage(MultipartFile file) {
        // 构建存储对象
        StorageObject storageObject = buildStorageObject(file);
        // 上传
        upload(storageObject);
        // 存根
        stub(storageObject);
        return R.ok(storageObject.getName());
    }

    /**
     * 获取存储对象
     */
    public InputStream get(String identifier) {
        try {
            return this.minioClient.getObject(GetObjectArgs.builder()
                    .bucket("alone")
                    .object(identifier)
                    .build());
        } catch (IOException | MinioException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new AloneException("存储对象加载失败", e);
        }
    }

    /**
     * 存储对象上传
     */
    public void upload(StorageObject object) {
        try {
            this.minioClient.putObject(
                    PutObjectArgs.builder().bucket("alone").object(object.getName())
                            .stream(object.getInputStream(), object.getSize(), -1).build());
        } catch (Exception e) {
            throw new AloneException("upload error", e);
        }
    }

    /**
     * 网络传输文件转存储对象
     */
    public StorageObject buildStorageObject(MultipartFile file) {
        try {
            if (Objects.isNull(file)) {
                throw new RuntimeException("build storage object error, file is null");
            }
            StorageObject object = new StorageObject();
            object.setName(UUID.randomUUID().toString());
            object.setOriginalName(file.getOriginalFilename());
            object.setSize(file.getSize());
            object.setInputStream(file.getInputStream());
            object.setProperty(PropertyEnum.TEMP.getCode());
            object.setHash(DigestUtil.md5Hex(file.getInputStream()));
            return object;
        } catch (Exception e) {
            throw new AloneException("build storage object error ", e);
        }
    }

    /**
     * 存根记录
     */
    public void stub(StorageObject object) {
        StorageManage storageManage = new StorageManage();
        storageManage.setProperty(object.getProperty());
        storageManage.setHash(object.getHash());
        storageManage.setName(object.getName());
        storageManage.setCreateTime(LocalDateTime.now());
        storageManage.setState(StateEnum.DEF.getCode());
        this.storageManageRepository.save(storageManage);
    }

    public void clearingExpiredObject() {
        log.info("clearing expired object ");
        List<StorageManage> list = this.storageManageRepository.findExpiredList(LocalDateTime.now().plusDays(-7));
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(e -> {
            try {
                this.minioClient.deleteObjectTags(DeleteObjectTagsArgs.builder().bucket("alone").object(e.getName()).build());
            } catch (Exception ex) {
                log.error("clearing expired object error!", ex);
            }
        });
        this.storageManageRepository.deleteAllById(list.stream().map(StorageManage::getId).toList());
    }
}
