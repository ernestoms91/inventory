package com.menchaca.inventory.repository;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.DepartmentName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BusinessOfficeRepository extends CrudRepository<BusinessOffice, Long> {

    Optional<BusinessOffice> findByDepartmentName(DepartmentName departmentName);
}


