package ca.sheridancollege.falzonm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.falzonm.database.DatabaseAccess;
import ca.sheridancollege.falzonm.beans.*;

@RestController

@RequestMapping("/api/v1/activity")
public class ActivityController {

	@Autowired
	private DatabaseAccess da;
	
	@GetMapping
	public List<Activity> getActivtyCollection(){
		return da.findAll();
	}
	
	@GetMapping(value="/{id}")
	public Activity getIndividualActivity(@PathVariable Long id) {
		return da.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	public String postActivity(@RequestBody Activity activity) {
		return "http://localhost:8080/api/v1/activity" + da.save(activity);
	}
	
	@PutMapping(consumes = "application/json")
	public String putActivityCollection(@RequestBody List<Activity> activityList) {
		da.deleteAll();
		da.saveAll(activityList);
		
		return "Total Records: " + da.count();
	}
}
