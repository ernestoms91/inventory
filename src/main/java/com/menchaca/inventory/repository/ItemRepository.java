package com.menchaca.inventory.repository;


import com.menchaca.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, PagingAndSortingRepository<Item, Long>{

    Optional<Item> findByStockNumber(int stockNumber);


}
