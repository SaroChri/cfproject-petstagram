function requestAdoption(event) {
    event.preventDefault();
    var petName = event.target.getAttribute('data-pet-name');
    localStorage.setItem('adoptedPet', petName);
    window.location.href = event.target.href;
}