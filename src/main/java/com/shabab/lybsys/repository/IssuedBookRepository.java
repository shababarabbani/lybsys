package com.shabab.lybsys.repository;

import com.shabab.lybsys.entity.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook,Long> {

    List<IssuedBook> findAllByIssuedBookIdIn(List<Long> issuedBookIds);
}
