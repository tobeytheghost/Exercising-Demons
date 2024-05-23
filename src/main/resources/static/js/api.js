function getActivity(id) {
	if (document.getElementById("activity" + id).innerHTML == "") {

		fetch('http://localhost:8080/getActivity/' + id)//use HomeController to fetch 
			.then(activity => activity.json()) // JSONify the data returned
			.then(function(activity) { // with the JSON data
				// modify textToDisplay below here!

				var textToDisplay = ""; // create and append to a blank var
				// textToDisplay += "ID: " + student.id + "<br>";
				textToDisplay += "Name: " + activity.grade + "<br>";
				textToDisplay += "Calories Per Hour: " + activity.letterGrade + "<br>";
				textToDisplay += "Duration: " + activity.attended + "<br>";
				textToDisplay += "Total Calories Burned: "+ activity.participated + "<br>";


				// finally, change our relevant div to display the var
				document.getElementById("activity" + id).innerHTML = textToDisplay;
			});
	}
	else {
		document.getElementById("activity" + id).innerHTML = "";
	}

}