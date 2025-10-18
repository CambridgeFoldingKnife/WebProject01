package com.zut.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    //推荐使用包装类型（基本类型有默认值易混）
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
 /*   public Dept() {
    }
    public Dept(Integer id, String name, LocalDateTime updateTime) {
        this.setId(id);
        this.setName(name);
        this.setUpdateTime(updateTime);
    }*/

   /* public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }*/
}
