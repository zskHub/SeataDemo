package com.zsk.seata.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsk.seata.storage.entity.StorageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StorageMapper extends BaseMapper<StorageEntity> {

    @Update("update storage_info set total = total - #{currentUsed} , used = used + #{currentUsed} where product_id = #{productId}")
    int updateUsed(@Param("productId") long productId , @Param("currentUsed") long currentUsed);

}
