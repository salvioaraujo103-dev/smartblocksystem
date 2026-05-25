package com.mac3.smartblock.repositories;

import com.mac3.smartblock.models.EtapaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository  extends JpaRepository<EtapaModel, UUID> {
}
