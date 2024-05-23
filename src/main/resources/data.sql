INSERT INTO sec_user (username, encryptedPassword, enabled)
VALUES ('admin', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO sec_user (username, encryptedPassword, enabled)
VALUES ('guest', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 2);

 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');

 
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

--List Table
INSERT INTO myWorkoutList(listName)
VALUES ('Arm Day');


--Workout Tables
INSERT INTO chestWorkouts(name, description, targetMuscles, imageUrl)
VALUES ('Dumbbell Bench Press', 'To perform the dumbbell bench press, an individual lies on a flat or inclined bench with a dumbbell in each hand, positioned at shoulder level. The movement involves lowering the dumbbells to chest level and then pressing them back up to the starting position. ', 'Chest, Tricepts', 'https://media.musclewiki.com/media/uploads/videos/branded/female-dumbbell-bench-press-side_5wGWrBA.mp4#t=0.1' ),
('Push Up', 'Place your hands firmly on the ground, directly under shoulders. Flatten your back so your entier body is straight and slowly lower your body keeping elbows tucked.', 'Chest', 'https://media.musclewiki.com/media/uploads/videos/branded/female-Bodyweight-push-up-front.mp4#t=0.1');

INSERT INTO backWorkouts(name, description, targetMuscles, imageUrl) VALUES
('Barbell Deadlift', 'Stand with feet under the bar and grip the bar with your hands about a shoulder width apart. Bend your knees, then lift the bar by straightening your back. Stand to your full height and hold.', 'Mid-Back, Lower Back', 'https://media.musclewiki.com/media/uploads/videos/branded/male-Barbell-barbell-deadlift-front.mp4#t=0.1'),
('Pull Ups', 'To perform a pull-up, an individual hangs from a horizontal bar with their palms facing away from their body and their hands placed slightly wider than shoulder-width apart. The movement involves pulling the body upward until the chin surpasses the bar, then lowering the body back down to the starting position.', 'Lats, Trapezius, Biceps', 'https://media.musclewiki.com/media/uploads/videos/branded/male-bodyweight-pullup-front.mp4#t=0.1');


INSERT INTO legWorkouts(name, description, targetMuscles, imageUrl) VALUES
('Dumbbell Bulgarion Split Squat', 'Find a bench that is knee hight. Place toes or top of foot on the box. Aim to get your front leg to hit parallel depth or lower.', 'Quads, Glutes', 'https://media.musclewiki.com/media/uploads/videos/branded/male-Dumbbells-dumbbell-bulgarian-split-squat-side.mp4#t=0.1'),
('Bodyweight Squat', 'Stand with feet shoulder-width apart, lower your body by bending your knees and pushing your hips back. Keep your chest up and weight on your heels. Descend until your thighs are parallel to the ground, then return to the starting position.', 'Quads', 'https://media.musclewiki.com/media/uploads/videos/branded/female-Bodyweight-bodyweight-squat-front.mp4#t=0.1');


INSERT INTO armWorkouts(name, description, targetMuscles, imageUrl) VALUES
('Dumbbell Skullcrusher', 'Lay flat on the floor or a bench with your firsts extended to the ceiling and a neutral grip. Break at the elbows until fists are by temples then exten your elbows and flex at the top', 'Triceps', 'https://media.musclewiki.com/media/uploads/videos/branded/male-dumbbell-skullcrusher-side_bgn7Uzz.mp4#t=0.1'),
('Barbell Curl', 'Stand with feet shoulder-width apart, grip a barbell with palms facing up, and curl it toward your shoulders. Keep elbows close, pause at the top, then lower with control. Its effective for building bicep strength and aesthetics. Maintain proper form to maximize benefits and reduce the risk of injury.', 'Biceps', 'https://media.musclewiki.com/media/uploads/videos/branded/male-Barbell-barbell-curl-side.mp4#t=0.1');