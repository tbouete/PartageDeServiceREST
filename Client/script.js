

window.onload  = function fillWithServices(){
	// Récupération d'une référence à la table
	var tab = document.getElementById("tabServices");

	var i = 0;
	console.log("passe ici");
  $.ajax({
			type: "GET",
		url: "http://localhost:8080/PartageDeService/api/services",
		dataType: "xml",
		success: function(xml) {
			$(xml).find('services').children('SERVICE').each(function(){
				console.log("passe icii");
		 
			  // Insère une ligne dans la table à l'indice de ligne 0
			  var nouvelleLigne = tab.insertRow(i);

			  // Insère une cellule dans la ligne à l'indice 0
			  var nouvelleCellule = nouvelleLigne.insertCell(0);
  			  nouvelleCellule.innerHTML+=$(this).find('ID').text();
			  var nouvelleCellule = nouvelleLigne.insertCell(1);
  			  nouvelleCellule.innerHTML+=$(this).find('NOM').text();
			  var nouvelleCellule = nouvelleLigne.insertCell(2);
  			  nouvelleCellule.innerHTML+=$(this).find('TYPE').text();
			  var nouvelleCellule = nouvelleLigne.insertCell(3);
  			  nouvelleCellule.innerHTML+=$(this).find('PRIX').text();
			  var nouvelleCellule = nouvelleLigne.insertCell(4);
  			  nouvelleCellule.innerHTML+=$(this).find('UTILISATEUR').text();			  
				
				i++
			});
		}
	});


  
};