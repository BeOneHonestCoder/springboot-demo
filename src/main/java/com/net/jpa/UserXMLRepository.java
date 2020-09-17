package com.net.jpa;

import com.net.domain.UserXML;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserXMLRepository extends JpaRepository<UserXML, Long> {
}
