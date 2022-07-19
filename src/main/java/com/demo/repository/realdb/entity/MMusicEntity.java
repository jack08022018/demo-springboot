package com.demo.repository.realdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"updatedAt", "updatedUser"})
@JsonPropertyOrder({"id", "name"})
@Entity
@Table(name = "m_music")
public class MMusicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_user")
    private String updatedUser;

    @Transient
    @JsonProperty
    private Integer pageSize;

    @Transient
    @JsonProperty
    private Integer currentPage;

}
