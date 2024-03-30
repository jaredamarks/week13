package pet.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
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
	
    
    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
      log.info("Adding employee to pet store: {}", petStoreId);
      return petStoreService.saveEmployee(petStoreId, employee);
    }
    //Retrieves all pet store data by calling retrieveAllPetStores()
    @GetMapping()
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieve all pet stores.");
		return petStoreService.retrieveAllPetStores();
	}
    //Retrieves a pet store's data from the database by calling the retrievePetStoreById()
    @GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store by ID={}", petStoreId);
		return petStoreService.retrievePetStoreById(petStoreId);
	}
    //Inserts a customer's data into the database by calling the insertCustomer()
	@PostMapping("/pet_store/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer saveCustomer(@PathVariable Long petStoreId,
			@RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Adding customer {} for pet store with ID={}", petStoreCustomer, petStoreId);

		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}
    
    @DeleteMapping("/pet_store/{petStoreId}")
    public ResponseEntity<Map<String, String>> deletePetStoreById(@PathVariable Long petStoreId) {
    	log.info("Received delete request for pet store with ID: " + petStoreId);
    	petStoreService.deletePetStoreById(petStoreId);
    	Map<String, String> response = new HashMap<>();
    	response.put("message", "Pet store deleted sucessfully");
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
