package com.scm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.scm.entity.*;

@Repository
public interface ContactFormRepo  extends JpaRepository<Contact ,String> {
 //const find methods 
    List<Contact> findByUser(User user);
    //custome find methods
@Query("SELECT C FROM Contact C WHERE C.user.id = :userId")
List<Contact> findByUserId(@Param("userId") String userId);


}
