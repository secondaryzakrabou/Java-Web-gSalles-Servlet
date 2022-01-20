$(document).ready(function() {
	var test = "t";
	$("#Recherche").click(function() {
				if (document.getElementById("notificationModifier").style.display == "block") {
					$("#notificationModifier").css("display", "none");
		
				}
				if (document.getElementById("notificationRemplir").style.display == "block") {
					$("#notificationRemplir").css("display", "none");
		
				}
		if ($("#dateReservationUn").val() == "" || $("#dateReservationDeux").val() == "") {

			$("#notificationModifier").css("display", "block");
			test="j";
		}

		$.ajax({
			url: "EntreDateController",
			data: { op: "load", dateUn: $("#dateReservationUn").val(), dateDeux: $("#dateReservationDeux").val() },
			method: 'POST',
			success: function(data, textStatus, jqXHR) {
				remplir(data);
			},
			error: function(data) {

				console.log(data);
			}
		});
	});

	function remplir(data) {
		var ligne = "";
		if (data[0] == undefined && test=="t") {
				$("#notificationRemplir").css("display", "block");
			}
			if (test=="j") {
				$("#notificationModifier").css("display", "block");
				test="t";
			}
		data.forEach(e => {
			
			ligne += "<tr> <td> <div class='d-flex px-2 py-1'> <div class='d-flex flex-column justify-content-center'> <h6 class='mb-0 text-sm'>" + e.id + "</h6> </div> </div> </td> <td> <p class='text-xs font-weight-bold mb-0'>" + e.reference + "</p> </td> <td class='align-middle text-center text-sm'> <p class='text-xs font-weight-bold mb-0'>" + e.dateReservation + "</p> </td><td class='align-middle text-center text-sm'> <p class='text-xs font-weight-bold mb-0'>" + e.nomClient + "</p> </td><td class='align-middle text-center text-sm'> <p class='text-xs font-weight-bold mb-0'>" + e.salle.libelle + "</p> </td></tr>";
		});
		$("#content").html(ligne);
	}

});