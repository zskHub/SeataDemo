package com.zsk.seata.storage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jianjun.ren
 * @since 2021/02/16
 */
@TableName("storage_info")
@Data
@Accessors(chain = true)
public class StorageEntity {

    @TableId
    private Long id;

    private Long total;

    private Long productId;

    private Long used;

}
