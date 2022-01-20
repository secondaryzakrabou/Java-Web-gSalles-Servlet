$(document).ready(function() {
	var indice1;
	var indice2;
	var indice3;
	$.ajax({
		url: "SalleController",
		data: { op: "load" },
		method: 'POST',
		success: function(data) {
			remplir(data);
		},
		error: function(data) {
			console.log(data);
		}
	});
	$("#ajouterSalle").click(function() {
		var type = $("#type").val();
		var libelle = $("#libelle").val();
		if (type == "" || libelle == "") {
			$("#notificationRemplir").css("display", "block");
			if (document.getElementById("notificationSuppr").style.display == "block") {
				$("#notificationSuppr").css("display", "none");

			}
			if (document.getElementById("notificationAjouter").style.display == "block") {
				$("#notificationAjouter").css("display", "none");

			} if (document.getElementById("notificationModifier").style.display == "block") {
				$("#notificationModifier").css("display", "none");

			}
		} else {
			$.ajax({
				url: "SalleController",
				data: { type: type, libelle: libelle },
				method: 'POST',
				success: function(data) {
					if (document.getElementById("notificationSuppr").style.display == "block") {
						$("#notificationSuppr").css("display", "none");

					}
					if (document.getElementById("notificationAjouter").style.display == "block") {
						$("#notificationAjouter").css("display", "none");

					} if (document.getElementById("notificationModifier").style.display == "block") {
						$("#notificationModifier").css("display", "none");

					}

					$("#notificationAjouter").css("display", "block");
					$("#type").val("");
					$("#libelle").val("");
					$("#notificationRemplir").css("display", "none");
					remplir(data);
				},
				error: function(data) {
					console.log(data);
				}
			});
		}

	});

	$("#content").on('click', '.delete', function() {
		var indice = $(this).attr('indice');
		$.ajax({
			url: "SalleController",
			data: { op: "delete", indice: indice },
			method: 'POST',
			success: function(data) {
				if (document.getElementById("notificationAjouter").style.display == "block") {
					$("#notificationAjouter").css("display", "none");

				}

				if (document.getElementById("notificationSuppr").style.display == "block") {
					$("#notificationSuppr").css("display", "none");

				}
				if (document.getElementById("notificationRemplir").style.display == "block") {
					document.getElementById("notificationRemplir").style.display = "none"
				}
				if (document.getElementById("notificationModifier").style.display == "block") {
					$("#notificationModifier").css("display", "none");

				}
				$("#notificationSuppr").css("display", "block");

				remplir(data);
			},
			error: function(data) {
				console.log(data);
			}
		});
	});


	$("#content").on('click', '.update', function() {
		indice1 = $(this).attr('indice');
		var indice2 = $(this).attr('indice2');
		var indice3 = $(this).attr('indice3');

		$("#type").val(indice2);
		$("#libelle").val(indice3);
		if (document.getElementById("notificationModifier").style.display == "block") {
			$("#notificationModifier").css("display", "none");

		}
		if (document.getElementById("notificationSuppr").style.display == "block") {
			$("#notificationSuppr").css("display", "none");

		}

		if (document.getElementById("notificationAjouter").style.display == "block") {
			$("#notificationAjouter").css("display", "none");

		}
		$("button#modifierReservation").css("display", "block");

		$("button#mm").css("display", "block");
		const buttonM = document.getElementById('ajouterSalle');
		buttonM.disabled = true;
		if (document.getElementById("notificationRemplir").style.display == "block") {
			document.getElementById("notificationRemplir").style.display = "none"
		} else {
			$("#notificationRemplir").attr("display", "block");
		}

	});


	$("#modifier").on('click', '.modifierSalle', function() {
		if ($("#type").val() == "" || $("#libelle").val() == "") {
			$("#notificationRemplir").css("display", "block");
		}
		else {
			$.ajax({
				url: "SalleController",
				data: { op: "update", indice1: indice1, type: $("#type").val(), libelle: $("#libelle").val() },
				method: 'POST',
				success: function(data) {
					remplir(data);
					$("button#mm").css("display", "none");
					$("button#ajouterSalle").enabled;
					$("#notificationRemplir").css("display", "none");
					$("#notificationModifier").css("display", "block");

					$("#type").val("");
					$("#libelle").val("");
					const buttonM = document.getElementById('ajouterSalle');
					buttonM.disabled = false;
				},
				error: function(data) {
					console.log(data);
				}
			});
		}
	});

	function remplir(data) {
		var ligne = "";
		data.forEach(e => {
			//ligne += "<tr><td>" + e.id + "</td><td>" + e.type + "</td><td>" + e.libelle + "</td><td><button indice=" + e.id + " class=' delete btn bg-gradient-danger mb-0 toast-btn' type='button' data-target='dangerToast'>Supprimer</button></td><td><button indice=" + e.id + " indice2=" + e.type + " indice3=" + e.libelle + " class=' update btn bg-gradient-success mb-0 toast-btn' type='button' data-target='successToast'>Modifier</button></td></tr>";


			ligne += "<tr> <td> <div class='d-flex px-2 py-1'> <div class='d-flex flex-column justify-content-center'> <h6 class='mb-0 text-sm'>" + e.id + "</h6> </div> </div> </td> <td> <p class='text-xs font-weight-bold mb-0'>" + e.type + "</p> </td> <td class='align-middle text-center text-sm'> <p class='text-xs font-weight-bold mb-0'>" + e.libelle + "</p> </td> <td class='align-middle text-center'><button indice=" + e.id + " class=' delete btn bg-gradient-danger mb-0 toast-btn' type='button' data-target='dangerToast'>Supprimer</button></td> <td class='align-middle text-center'><button indice=" + e.id + " indice2=" + e.type + " indice3=" + e.libelle + " class=' update btn bg-gradient-success mb-0 toast-btn' type='button' data-target='successToast'>Modifier</button> </td> </tr>";
		});
		$("#content").html(ligne);
	}


});