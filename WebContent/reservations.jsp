
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="apple-touch-icon" sizes="76x76"
	href="template/img/apple-icon.png">
<link rel="icon" type="image/png" href="template/img/favicon.png">
<title>Gestion Des Reservations</title>
<script src="script/jquery-3.6.0.min.js"></script>
<script src="script/reservation.js"></script>

<%@ include file="/include/css.jsp"%>

</head>

<body class="g-sidenav-show  bg-gray-200">
	<%@ include file="/include/Sidebar.jsp"%>
	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<%@ include file="/include/NavBar.jsp"%>
		<div class="container-fluid py-4">
			<div class="row">
				<div class=" mt-4">
					<div class="card">
						<div class="card-header pb-0 px-3">
							<h6 class="mb-0">Nouvelle Reservation</h6>
						</div>
						<div class="card-body">
							<form action="ReservationController"  action="ReservationTestController" method="POST" role="form"
								class="text-start">
								<div class="input-group input-group-outline my-3">
									<label class="form-label">Reference</label> <input id="ref"
										type="text" class="form-control" onfocus="focused(this)"
										onfocusout="defocused(this)">
								</div>
								<div class="input-group input-group-outline mb-3">
									<label class="form-label">Nom de Client</label> <input id="nomClient"
										type="text" class="form-control"
										onfocus="focused(this)" onfocusout="defocused(this)">
								</div>

								<div class="input-group input-group-outline mb-3">
									<label class="form-label">Date de Reservation</label> <input id="dateReservation"
										type=date class="form-control" onfocus="focused(this)"
										onfocusout="defocused(this)">
								</div>

								<div class="input-group input-group-outline mb-3">
									<label class="form-label"></label> <select name=""
										class="form-control " onfocus="focused(this)"  onfocusout="defocused(this)" id="salle">
									</select> 
								</div>


								<div style="display: none"
									class="alert alert-danger alert-dismissible text-white"
									role="alert" id="notificationRemplir">
									<span class="text-sm">Veuillez remplir tout les champs </span>
									<button type="button" class="btn-close text-lg py-3 opacity-10"
										data-bs-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">�</span>
									</button>
								</div>
								<div style="display: none"
									class="alert alert-success alert-dismissible text-white"
									role="alert" id="notificationModifier">
									<span class="text-sm">Bien Modifi� </span>
									<button type="button" class="btn-close text-lg py-3 opacity-10"
										data-bs-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">�</span>
									</button>
								</div>
								<div style="display: none"
									class="alert alert-success alert-dismissible text-white"
									role="alert" id="refExiste">
									<span class="text-sm">Reference existe deja </span>
									<button type="button" class="btn-close text-lg py-3 opacity-10"
										data-bs-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">�</span>
									</button>
								</div>
								<div style="display: none"
									class="alert alert-success alert-dismissible text-white"
									role="alert" id="notificationAjouter">
									<span class="text-sm">Bien R�serv� </span>
									<button type="button" class="btn-close text-lg py-3 opacity-10"
										data-bs-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">�</span>
									</button>
								</div>
								<div style="display: none"
									class="alert alert-danger alert-dismissible text-white"
									role="alert" id="notificationSuppr">
									<span class="text-sm">Bien Supprim� </span>
									<button type="button" class="btn-close text-lg py-3 opacity-10"
										data-bs-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">�</span>
									</button>
								</div>
								
								<div class="text-center">
									<button id="ajouterReservation" type="button"
										class="ajouterReservation btn bg-gradient-primary w-100 my-4 mb-2">R�server</button>
								</div>
								
								<div id="modifier" class="text-center">
									<button style="display: none" type="button"
										id="modifierReservation"
										class="modifierRes btn bg-gradient-success w-100 my-4 mb-2">Modifier</button>
								</div>

							</form>
						</div>
					</div>
				</div>

			</div>
			<br> <br>
			<div class="row">
				<div class="col-12">
					<div class="card my-4">
						<div
							class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
							<div
								class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
								<h6 class="text-white text-capitalize ps-3">Liste Des
									Reservations</h6>
							</div>
						</div>
						<div class="card-body px-0 pb-2">
							<div class="table-responsive p-0">
								<table class="table align-items-center mb-0">
									<thead>
										<tr>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">ID</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Reference</th>
											
											<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">DateReservation</th>
												<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">nomClient</th>
											<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Salle</th>
											<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Supprimer</th>
											<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Modifier</th>
											<th class="text-secondary opacity-7"></th>
										</tr>
									</thead>
									<tbody id="content">






									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>




		</div>
		<%@ include file="/include/Footer.jsp"%>
		</div>
	</main>
	<%@ include file="/include/FixedPlugin.jsp"%>
	<%@ include file="/include/js.jsp"%>
</body>

</html>