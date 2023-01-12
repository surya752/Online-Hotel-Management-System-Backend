package com.example.MangerMicroservice.Controller;





import com.example.MangerMicroservice.entity.InventoryReports;
import com.example.MangerMicroservice.exception.ResourceNotFoundException;
import com.example.MangerMicroservice.repository.InventoryReportsRepository;
import com.example.MangerMicroservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/Manager")
public class InventoryReportsController {


    @Autowired
    private SequenceGeneratorService service;


    @Autowired
    private InventoryReportsRepository inventoryReportsRepository;

    @GetMapping("/inreports/list")
    public List<InventoryReports> getAllInventor(){
        return  this.inventoryReportsRepository.findAll();


    }

    @GetMapping("/inreports/{id}")
    public ResponseEntity<InventoryReports> getinventory(@PathVariable(value = "id") Long  inReportsId)
            throws ResourceNotFoundException {
        InventoryReports inventoryReports = inventoryReportsRepository.findById(inReportsId)
                .orElseThrow(() -> new ResourceNotFoundException("inventory not found for this id :: " +inReportsId ));
        return ResponseEntity.ok().body(inventoryReports);
    }
    @PostMapping("/inreports/save")
    public InventoryReports createinventory( @Valid @RequestBody InventoryReports inventoryReports){
        inventoryReports.setId(service.getSequenceNumber(InventoryReports.SEQUENCE_NAME));
        return  this.inventoryReportsRepository.save(inventoryReports);

    }
    @PutMapping("inreports/edit/{id}")
    public ResponseEntity<InventoryReports> updateInventory(@PathVariable(value = "id") Long  inReportsId,
                                                   @Validated @RequestBody InventoryReports inventoryDetails  )
            throws ResourceNotFoundException {
        InventoryReports inventoryReports = inventoryReportsRepository.findById(inReportsId)
                .orElseThrow(() -> new ResourceNotFoundException("inventory not found for this id :: " +inReportsId ));


        inventoryReports.setTotalincome(inventoryDetails.getTotalincome());
        inventoryReports.setMaintainanceCost(inventoryDetails.getMaintainanceCost());
        inventoryReports.setEmployeesalary(inventoryDetails.getEmployeesalary());
        inventoryReports.setTotalProfit(inventoryDetails.getTotalProfit());

        return ResponseEntity.ok(this.inventoryReportsRepository.save(inventoryReports));


    }
    @DeleteMapping("inreports/delete/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long  inReportsId)throws ResourceNotFoundException {
        InventoryReports inventoryReports=inventoryReportsRepository.findById(inReportsId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " +inReportsId ));

        this.inventoryReportsRepository.delete(inventoryReports);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }


}
