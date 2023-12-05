package com.quick.recording.qrb2bservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "session_store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionStoreEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "store")
    @JdbcTypeCode(SqlTypes.JSON)
    private String store;

}
