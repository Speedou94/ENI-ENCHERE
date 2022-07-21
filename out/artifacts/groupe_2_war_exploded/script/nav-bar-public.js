// Affichage de la navbar en responsive (Niko 18/07/2022)

const burgerIcon = document.querySelector('#burger');
const navBarMenu = document.querySelector('#navbar-public');

burgerIcon.addEventListener('click',()=>{
    navBarMenu.classList.toggle('is-active')
})


