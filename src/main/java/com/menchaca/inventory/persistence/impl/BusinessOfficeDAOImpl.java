package com.menchaca.inventory.persistence.impl;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.DepartmentName;
import com.menchaca.inventory.persistence.IBusinessOfficeDAO;
import com.menchaca.inventory.repository.BusinessOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BusinessOfficeDAOImpl implements IBusinessOfficeDAO {
    @Autowired
    private BusinessOfficeRepository businessOfficeRepository;

    @Override
    public Optional<BusinessOffice> findById(Long id) {
        return businessOfficeRepository.findById(id);
    }

    @Override
    public Optional<BusinessOffice> findByDepartmentName(DepartmentName departmentName) { return businessOfficeRepository.findByDepartmentName(departmentName);}

    @Override
    public List<BusinessOffice> findAll() {
        return (List<BusinessOffice>) businessOfficeRepository.findAll();
    }

    @Override
    public void save(BusinessOffice businessOffice) {
        businessOfficeRepository.save(businessOffice);
    }

    @Override
    public void deleteById(Long id) {
        businessOfficeRepository.deleteById(id);
    }
}
