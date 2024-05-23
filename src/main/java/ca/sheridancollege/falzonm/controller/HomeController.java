package ca.sheridancollege.falzonm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.falzonm.beans.*;
import ca.sheridancollege.falzonm.database.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired 
	@Lazy
	private DatabaseAccess da;
	
List<Workout> workoutList = new CopyOnWriteArrayList<>();
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}
	
	@GetMapping("/secure/admin/index")
	public String secureAdminIndex() {
		return "/secure/admin/index";
	}
	
	@GetMapping("/secure/user/index")
	public String secureGuestIndex() {
		return "/secure/user/index";
	}
	
	@GetMapping("/secure/user/browse")
	public String secureBrowse() {
		return "/secure/user/browse";
	}
	
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		da.addUser(username, password);
		
		Long userId = da.findUserAccount(username).getUserId();
		
		da.addRole(userId, Long.valueOf(2));
		
		return "redirect:/login";
	}
	
	
	//Mapping for the Workout Pages
	@GetMapping("/secure/user/chest")
	public String chest(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getChestWorkoutList());
		return "/secure/user/chest";
	}
	
	@GetMapping("/secure/user/back")
	public String back(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getBackWorkoutList());
		return "/secure/user/back";
	}
	
	@GetMapping("/secure/user/legs")
	public String legs(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getLegWorkoutList());
		return "/secure/user/legs";
	}
	
	@GetMapping("/secure/user/arms")
	public String arms(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getArmsWorkoutList());
		return "/secure/user/legs";
	}
	
	@GetMapping("/secure/user/details/{id}")
	public String viewGuestDetails( @PathVariable Long id, HttpServletRequest request, Model model) {

		String referer = request.getHeader("Referer");

	    Workout workout;

	    if (referer != null && referer.contains("/secure/user/chest")) {
	        workout = da.getChestWorkoutList().get(0);
	    } else if (referer != null && referer.contains("/secure/user/back")) {
	        workout = da.getBackWorkoutList().get(0);
	    } else if (referer != null && referer.contains("/secure/user/legs")) {
	        workout = da.getLegWorkoutList().get(0);
	    }else if (referer != null && referer.contains("/secure/user/arms")) {
	        workout = da.getArmsWorkoutList().get(0);}
	    else {
	        // Handle unknown or unexpected cases
	        return "redirect:/error"; // Redirect to an error page or handle as needed
	    }

	    model.addAttribute("workout", workout);
	    
	    List<MyList> userWorkoutLists = da.getMyLists();
	    
	    model.addAttribute("myWorkoutList", userWorkoutLists);
	    return "/secure/user/details";
	}
	
	//Mapping for Admin Pages and Admin Functionality 
	@GetMapping("/secure/admin/chest")
	public String adminChest(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getChestWorkoutList());
		return "/secure/admin/chest";
	}
	
	@GetMapping("/secure/admin/back")
	public String adminBack(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getBackWorkoutList());
		return "/secure/admin/back";
	}
	
	@GetMapping("/secure/admin/legs")
	public String adminLegs(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getLegWorkoutList());
		return "/secure/admin/legs";
	}
	
	@GetMapping("/secure/admin/arms")
	public String adminArm(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getArmsWorkoutList());
		return "/secure/admin/arms";
	}
	
	//Insert Workout Mapping
	@PostMapping("/secure/admin/chest/insertChestWorkout")
	public String insertChestWorkout(Model model, @ModelAttribute Workout workout) {
		da.insertChestWorkout(workout);
		
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getChestWorkoutList());
		
		return "/secure/admin/chest";
	}
	
	@PostMapping("/secure/admin/back/insertBackWorkout")
	public String insertBackWorkout(Model model, @ModelAttribute Workout workout) {
		da.insertBackWorkout(workout);
		
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getBackWorkoutList());
		
		return "/secure/admin/back";
	}
	
	@PostMapping("/secure/admin/legs/insertLegWorkout")
	public String insertLegWorkout(Model model, @ModelAttribute Workout workout) {
		da.insertLegWorkout(workout);
		
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getLegWorkoutList());
		
		return "/secure/admin/legs";
	}
	
	@PostMapping("/secure/admin/arms/insertArmWorkout")
	public String insertArmWorkout(Model model, @ModelAttribute Workout workout) {
		da.insertArmWorkout(workout);
		
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getArmsWorkoutList());
		
		return "/secure/admin/arms";
	}
	
	//Delete Workout Mapping
	@GetMapping("/secure/admin/chest/deleteChestWorkoutById/{id}")
	public String deleteChestWorkoutById(@PathVariable Long id, Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getChestWorkoutList());
		
		
		
		da.deleteChestWorkoutById(id);
		
		return "/secure/admin/chest";
	}
	
	@GetMapping("/secure/admin/back/deleteBackWorkoutById/{id}")
	public String deleteBackWorkoutById(@PathVariable Long id, Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getBackWorkoutList());
		
		
		
		da.deleteBackWorkoutById(id);
		
		return "/secure/admin/back";
	}
	
	
	@GetMapping("/secure/admin/legs/deleteLegWorkoutById/{id}")
	public String deleteLegWorkoutById(@PathVariable Long id, Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getLegWorkoutList());
		
		
		
		da.deleteLegWorkoutById(id);
		
		return "/secure/admin/legs";
	}
	
	@GetMapping("/secure/admin/arms/deleteArmWorkoutById/{id}")
	public String deleteArmWorkoutById(@PathVariable Long id, Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("workoutList", da.getArmsWorkoutList());
		
		
		
		da.deleteArmWorkoutById(id);
		
		return "/secure/admin/arms";
	}
	
	
	//Edit Workout Code
	@GetMapping("/secure/admin/back/editBackWorkoutById/{id}")
	public String editBackWorkoutById(@PathVariable Long id, Model model) {
		
		Workout workout = ((List<Workout>) da.getBackWorkoutById(id)).get(0);
		
		model.addAttribute("workout", workout);
		
		da.deleteBackWorkoutById(id);
		
		model.addAttribute("workoutList", da.getBackWorkoutById(id));
		
		return "/secure/admin/back";
	}
	
	@GetMapping("/secure/admin/chest/editChestWorkoutById/{id}")
	public String editChestWorkoutById(@PathVariable Long id, Model model) {
		
		Workout workout = ((List<Workout>) da.getChestWorkoutById(id)).get(0);
		
		model.addAttribute("workout", workout);
		
		da.deleteChestWorkoutById(id);
		
		model.addAttribute("workoutList", da.getChestWorkoutById(id));
		
		return "/secure/admin/chest";
	}
	
	
	@GetMapping("/secure/admin/legs/editLegWorkoutById/{id}")
	public String editLegWorkoutById(@PathVariable Long id, Model model) {
		
		Workout workout = ((List<Workout>) da.getLegWorkoutById(id)).get(0);
		
		model.addAttribute("workout", workout);
		
		da.deleteLegWorkoutById(id);
		
		model.addAttribute("workoutList", da.getLegWorkoutById(id));
		
		return "/secure/admin/legs";
	}
	
	@GetMapping("/secure/admin/arms/editArmsWorkoutById/{id}")
	public String editArmsWorkoutById(@PathVariable Long id, Model model) {
		
		Workout workout = ((List<Workout>) da.getArmWorkoutById(id)).get(0);
		
		model.addAttribute("workout", workout);
		
		da.deleteArmWorkoutById(id);
		
		model.addAttribute("workoutList", da.getArmWorkoutById(id));
		
		return "/secure/admin/arms";
	}
	

	//Add workouts to List Functionality
	
	//showing all list names
	@GetMapping("/secure/user/list")
	public String myLists(Model model) {
		model.addAttribute("lists", new MyList());
		model.addAttribute("myWorkoutList", da.getMyLists());
		
		return "/secure/user/list";
	}
	
	@PostMapping("/secure/user/list/createNewList")
	public String createNewList(Model model, @ModelAttribute MyList lists) {
		da.createNewList(lists);
		
		model.addAttribute("lists", new MyList());
		model.addAttribute("myWorkoutList", da.getMyLists());
		
		return "/secure/user/list";
	}
	
	@GetMapping("/secure/user/listdetails/{id}")
	public String myListDetails(@PathVariable Long id, Model model, HttpSession session) {
		
		List<MyList> list = (List<MyList>)session.getAttribute("list");
		
	
		MyList myWorkoutList = da.getListDetailsById(id).get(0);
		
		model.addAttribute("myWorkoutList", myWorkoutList);
	
		
		return "/secure/user/listdetails";
	}
	
	@GetMapping("/secure/user/legday")
	public String legday() {
		return "/secure/user/legday";
	}

	
	@PostMapping("/secure/user/details/addToList/{id}")
	public String addToList(Model model, @RequestParam Long id, HttpServletRequest request, HttpSession session) {
		
		String referer = request.getHeader("Referer");

	    Workout workout;
	    
	    if (referer != null && referer.contains("/secure/user/chest")) {
	        workout = da.getChestWorkoutList().get(0);
	    } else if (referer != null && referer.contains("/secure/user/back")) {
	        workout = da.getBackWorkoutList().get(0);
	    } else if (referer != null && referer.contains("/secure/user/legs")) {
	        workout = da.getLegWorkoutList().get(0);
	    }else if (referer != null && referer.contains("/secure/user/arms")) {
	        workout = da.getArmsWorkoutList().get(0);}
	    else {
	        // Handle unknown or unexpected cases
	        return "redirect:/error"; // Redirect to an error page or handle as needed
	    }
	    
	    List<Workout> selectedWorkout = (List<Workout>) session.getAttribute("list");
	    if(selectedWorkout == null) {
	    	selectedWorkout = new ArrayList<>();
	    	session.setAttribute("list", selectedWorkout);
	    	
	    	
	    }
	    
	    selectedWorkout.add(workout);
		session.setAttribute("list", selectedWorkout);
		
		return "redirect:/secure/user/details/{id}";
	}
	
	
	//API CODE
			final String REST_URL = "http://localhost:8080/api/v1/activity";
			
			@GetMapping("/secure/user/activities")
			public String activities(Model model, RestTemplate restTemplate) {
				
							ResponseEntity<Activity[]> responseEntity = restTemplate.getForEntity(REST_URL, Activity[].class);
				
						model.addAttribute("activityList", responseEntity.getBody());
						
						
						
						
						return "/secure/user/activities";
						
						
						}
			
			@GetMapping(value="/getActivity/{id}", produces="application/json")
			@ResponseBody
			public Activity getActivity(@PathVariable int id, RestTemplate restTemplate) {
				ResponseEntity<Activity>responseEntity=restTemplate.getForEntity(REST_URL + "/" + id, Activity.class);
				
				return responseEntity.getBody();
			}
			
		
	

}
