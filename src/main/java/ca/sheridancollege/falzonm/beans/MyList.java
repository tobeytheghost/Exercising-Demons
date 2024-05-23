package ca.sheridancollege.falzonm.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyList {
	private Long id;
	
	@NonNull
	private String listName;
	
	
	private Integer sets;
	
	private Integer reps;
	
	private Double weight;
}
