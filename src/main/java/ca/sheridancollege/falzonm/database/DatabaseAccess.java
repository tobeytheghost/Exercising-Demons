package ca.sheridancollege.falzonm.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.falzonm.beans.*;

@Repository
public class DatabaseAccess {
	
	@Autowired 
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	
	public User findUserAccount(String username) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where username = :username";
		namedParameters.addValue("username", username);
		
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
			
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	//Method to get User Roles for a specific User ID 
			public List<String> getRolesById(Long userId){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				String query = "SELECT sec_role.roleName FROM user_role, sec_role WHERE user_role.roleId = sec_role.roleId AND userId = :userId";
				namedParameters.addValue("userId", userId);
				return jdbc.queryForList(query, namedParameters, String.class);
			}

			//REGISTER DATABSE METHODS 
			
			public void addUser(String username, String password) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "INSERT INTO sec_user (username, encryptedPassword, enabled) VALUES (:username, :encryptedPassword, 1)";
				namedParameters.addValue("username",username);
				namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
				jdbc.update(query, namedParameters);
				}
			
			public void addRole(Long userId, Long roleId) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "INSERT INTO user_role (userId, roleId) VALUES (:userId, :roleId)";
				namedParameters.addValue("userId", userId);
				namedParameters.addValue("roleId", roleId);
				jdbc.update(query, namedParameters);

			}
			
			//CODE FOR USER
			
			//getWorkoutList code
			public List<Workout> getWorkoutList(){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM workouts";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			public List<Workout> getChestWorkoutList(){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM chestWorkouts";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
//			public Workout getChestWorkoutById(Long id) {
//				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//				
//				namedParameters.addValue("id", id);
//				
//				String query = "SELECT * FROM chestWorkouts WHERE id = :id";
//				
//				return jdbc.queryForObject(query, namedParameters, (rs, rowNum) ->{
//					Workout workout = new Workout();
//					workout.setName(rs.getString("id"));
//					workout.setDescription(rs.getString("id"));
//					workout.setTargetMuscles(rs.getString("id"));
//					
//					return workout;
//				});
//			}
//			
			public List<Workout> getChestWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("id", id);
				
				String query = "SELECT * FROM chestWorkouts WHERE id = :id";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			public List<Workout> getBackWorkoutList(){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM backWorkouts";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			
//			public Workout getBackWorkoutById(Long id) {
//				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//				
//				namedParameters.addValue("id", id);
//				
//				String query = "SELECT * FROM backWorkouts WHERE id = :id";
//				
//				return jdbc.queryForObject(query, namedParameters, (rs, rowNum) ->{
//					Workout workout = new Workout();
//					workout.setName(rs.getString("id"));
//					workout.setDescription(rs.getString("id"));
//					workout.setTargetMuscles(rs.getString("id"));
//					
//					return workout;
//				});
//			}
			public List<Workout> getBackWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("id", id);
				
				String query = "SELECT * FROM backWorkouts WHERE id = :id";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			public List<Workout> getLegWorkoutList(){

				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM legWorkouts";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			
//			public Workout getLegWorkoutById(Long id) {
//				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//				
//				namedParameters.addValue("id", id);
//				
//				String query = "SELECT * FROM legWorkouts WHERE id = :id";
//				
//				return jdbc.queryForObject(query, namedParameters, (rs, rowNum) ->{
//					Workout workout = new Workout();
//					workout.setName(rs.getString("id"));
//					workout.setDescription(rs.getString("id"));
//					workout.setTargetMuscles(rs.getString("id"));
//					
//					return workout;
//				});
//			}
			
			public List<Workout> getLegWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("id", id);
				
				String query = "SELECT * FROM legWorkouts WHERE id = :id";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			public List<Workout> getArmsWorkoutList(){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM armWorkouts";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
//			public Workout getArmWorkoutById(Long id) {
//				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//				
//				namedParameters.addValue("id", id);
//				
//				String query = "SELECT * FROM armWorkouts WHERE id = :id";
//				
//				return jdbc.queryForObject(query, namedParameters, (rs, rowNum) ->{
//					Workout workout = new Workout();
//					workout.setName(rs.getString("id"));
//					workout.setDescription(rs.getString("id"));
//					workout.setTargetMuscles(rs.getString("id"));
//					
//					return workout;
//				});
//			}

			public List<Workout> getArmWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("id", id);
				
				String query = "SELECT * FROM armWorkouts WHERE id = :id";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Workout>(Workout.class));
			}
			
			
			//CODE FOR ADMIN
		
			//insertWorkout code
			
			public void insertChestWorkout(Workout workout) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "INSERT INTO chestWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				jdbc.update(query, namedParameters);
			}

			
			public void insertBackWorkout(Workout workout) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "INSERT INTO backWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				jdbc.update(query, namedParameters);
			}
			
			public void insertLegWorkout(Workout workout) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "INSERT INTO legWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				jdbc.update(query, namedParameters);
			}

			public void insertArmWorkout(Workout workout) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "INSERT INTO armWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				jdbc.update(query, namedParameters);
			}

			//Update Workout Code
			public void updateChestWorkout(Workout workout) {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "UPDATE chestWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				int rowsAffected = jdbc.update(query, namedParameters);
				
				
				if (rowsAffected > 0) {
					System.out.println("Updated Workout with ID" + workout.getId());
				}
			}
			
			
			public void updateBackWorkout(Workout workout) {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "UPDATE backWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				int rowsAffected = jdbc.update(query, namedParameters);
				
				
				if (rowsAffected > 0) {
					System.out.println("Updated Workout with ID" + workout.getId());
				}
			}
			
			public void updateLegWorkout(Workout workout) {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "UPDATE legWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				int rowsAffected = jdbc.update(query, namedParameters);
				
				
				if (rowsAffected > 0) {
					System.out.println("Updated Workout with ID" + workout.getId());
				}
			}
			
			public void updateArmWorkout(Workout workout) {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("name", workout.getName());
				namedParameters.addValue("description", workout.getDescription());
				namedParameters.addValue("targetMuscles", workout.getTargetMuscles());
				namedParameters.addValue("imageUrl", workout.getImageUrl());
				
				
				String query = "UPDATE armWorkouts (name, description, targetMuscles, imageUrl) VALUES (:name, :description, :targetMuscles, :imageUrl)";
				
				int rowsAffected = jdbc.update(query, namedParameters);
				
				
				if (rowsAffected > 0) {
					System.out.println("Updated Workout with ID" + workout.getId());
				}
			}
			
		
			
			//deleteWorkoutById
			public void deleteChestWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "DELETE FROM chestWorkouts WHERE id = :id";
				
				namedParameters.addValue("id", id);
				
				if(jdbc.update(query, namedParameters) > 0 ) {
					System.out.println("Deleted Workout " + id + " from the database");
				}
			}
			
			
			public void deleteBackWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "DELETE FROM backWorkouts WHERE id = :id";
				
				namedParameters.addValue("id", id);
				
				if(jdbc.update(query, namedParameters) > 0 ) {
					System.out.println("Deleted Workout " + id + " from the database");
				}
			}
			
			public void deleteLegWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "DELETE FROM legWorkouts WHERE id = :id";
				
				namedParameters.addValue("id", id);
				
				if(jdbc.update(query, namedParameters) > 0 ) {
					System.out.println("Deleted Workout " + id + " from the database");
				}
			}
			
			public void deleteArmWorkoutById(Long id) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "DELETE FROM armWorkouts WHERE id = :id";
				
				namedParameters.addValue("id", id);
				
				if(jdbc.update(query, namedParameters) > 0 ) {
					System.out.println("Deleted Workout " + id + " from the database");
				}
			}
			
			
			
			//Add To List Code
			

	        public void addListToDatabase(String userId, List<Workout> list) {
	            // Iterate through the cart and insert each item into the database
	            for (Workout workout : list) {
	            	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	                namedParameters.addValue("userId", userId);
	                namedParameters.addValue("id", workout.getId());

	                // Add additional parameters as needed for your database schema

	                String query = "INSERT INTO user_list (userId, id) VALUES (:userId, :id)";
	                jdbc.update(query, namedParameters);
	            }
	        }
	        
	        //create new list for when a user wants to make a new list
	        public void createNewList(MyList lists) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				namedParameters.addValue("listName", lists.getListName());
				
				
				String query = "INSERT INTO myWorkoutList (listName) VALUES (:listName)";
				
				jdbc.update(query, namedParameters);
			}
	        
	        //get all list names from the table
	    	public List<MyList> getMyLists(){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM myWorkoutList";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<MyList>(MyList.class));
			}
			//getWorkoutListById from the table
			
			public List<MyList> getListDetailsById(Long id){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM myWorkoutList WHERE id = :id";
				
				namedParameters.addValue("id", id);
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<MyList>(MyList.class));
			}
			
			
			
			//API CODE 
			public List<Activity> findAll(){
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM activity";
				
				return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Activity>(Activity.class));
			}
			
			
			public Activity findById(Long id) {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM activity WHERE id = :id";
				
				namedParameters.addValue("id", id);
				
				return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Activity>(Activity.class));
				
			}
			
			
			public Long save (Activity activity) {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
				
				String query = "INSERT INTO activity (activityName, caloriesPerHour, duration, totalCalories) VALUES (:activityName, :caloriesPerHour, :duration, :totalCalories)";
				
				namedParameters.addValue("activityName", activity.getActivityName());
				namedParameters.addValue("caloriesPerHour", activity.getCaloriesPerHour());
				namedParameters.addValue("duration", activity.getDuration());
				namedParameters.addValue("totalCalories", activity.getTotalCalroies());
				
				
				jdbc.update(query, namedParameters, generatedKeyHolder);
				
				return (Long)generatedKeyHolder.getKey();
				
			}
			
			
			public void deleteAll() {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "DELETE FROM activity";
				
				jdbc.update(query, namedParameters);
			}
			
			public Long count() {
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
				String query = "SELECT count(*) FROM activity";
				
				return jdbc.queryForObject(query, namedParameters, Long.TYPE);
			}
			
			
			public void saveAll(List<Activity> activityList) {
				for (Activity a : activityList) {
					save(a);
				}
			}
			
}
