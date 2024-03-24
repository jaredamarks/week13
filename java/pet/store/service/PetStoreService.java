package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	public PetStoreData savePetStore(PetStoreData petStoreData) {
        Long petStoreId = petStoreData.getPetStoreId();
        PetStore petStore = findOrCreatePetStore(petStoreId);
        copyPetStoreFields(petStore, petStoreData);
        petStore = petStoreDao.save(petStore);

        return new PetStoreData(petStore);
    }

	private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId == null) {
        	return new PetStore();
        	
        } else {
        	
        return findPetStoreById(petStoreId);
    }
	}
    private PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId)
        		.orElseThrow(() -> new NoSuchElementException("Pet Store with ID" + petStoreId + "was not found."));
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
    	petStore.setPetStoreId(petStoreData.getPetStoreId());
    	petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
}
