package org.example.bootswagger.model.repository;

import org.example.bootswagger.model.entity.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile, String> {

}
