package com.salesianos.dam.anuel.MiarmaNetwork.media.repo;

import com.salesianos.dam.anuel.MiarmaNetwork.media.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileMetadata, Long> {
}
