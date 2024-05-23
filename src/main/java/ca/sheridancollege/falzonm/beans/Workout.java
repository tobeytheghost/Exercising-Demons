package ca.sheridancollege.falzonm.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String targetMuscles;
	
	private String imageUrl;

}
