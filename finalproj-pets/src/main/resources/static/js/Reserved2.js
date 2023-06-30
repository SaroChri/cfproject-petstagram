$(document).ready(function() {
    var adoptedPet = localStorage.getItem('adoptedPet');
    if(adoptedPet) {
        $('#pet-' + adoptedPet).addClass("unclickable");
        localStorage.removeItem('adoptedPet');
    }
});