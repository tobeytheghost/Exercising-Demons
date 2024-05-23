package ca.sheridancollege.falzonm.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Activity {

	private Long id;
	
	@NonNull
	private String activityName;
	
	private Integer caloriesPerHour;
	
	private Integer duration;
	
	private Integer totalCalroies;
	
	
	public Activity(String activityName, Integer caloriesPerHour, Integer duration, Integer totalCalories) {
		this.activityName = activityName;
		this.caloriesPerHour = caloriesPerHour;
		this.duration = duration;
		this.totalCalroies = totalCalories;
	}
}
