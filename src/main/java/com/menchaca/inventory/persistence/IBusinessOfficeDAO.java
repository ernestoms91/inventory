package com.menchaca.inventory.persistence;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.DepartmentName;

import java.util.List;
import java.util.Optional;

public interface IBusinessOfficeDAO {
    Optional<BusinessOffice> findById(Long id);
    Optional<BusinessOffice> findByDepartmentName(DepartmentName departmentName);
    List<BusinessOffice> findAll();
    void save (BusinessOffice businessOffice);
    void deleteById(Long id);
}
