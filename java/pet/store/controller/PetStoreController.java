package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	
	@Autowired
    private PetStoreService petStoreService;
    
    @PostMapping
    public PetStoreData insertPetStoreData(@RequestBody PetStoreData petStoreData) {
        log.info("Inserting pet store {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }
    
    @PutMapping("/{storeId}")
    public PetStoreData updatePetStoreData(@PathVariable Long storeId, @RequestBody PetStoreData petStoreData) {
        log.info("Updating pet store data for storeId: {}", storeId);
        petStoreData.setPetStoreId(storeId); // Set the pet store ID from the path variable
        return petStoreService.savePetStore(petStoreData);
    }
	
}