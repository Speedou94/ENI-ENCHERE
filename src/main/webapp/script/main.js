const inputRadioAchat = document.getElementById("achat");
const inputRadioVente = document.getElementById("vente");
const checkboxEncours = document.getElementById("en-cours");
const checkboxNonDebuter = document.getElementById("non-debuter");
const checkboxTerminer = document.getElementById("terminer");
const checkboxOuverte = document.getElementById("ouverte");
const checkboxMesEncheres = document.getElementById("mes-encheres");
const checkboxRemporte = document.getElementById("remporte");
inputRadioAchat.addEventListener('change',(e)=>{

    checkboxOuverte.removeAttribute('disabled')
    checkboxMesEncheres.removeAttribute('disabled')
    checkboxRemporte.removeAttribute('disabled')
    checkboxNonDebuter.setAttribute('disabled','true')
    checkboxEncours.setAttribute('disabled','true')
    checkboxTerminer.setAttribute('disabled','true')


})

inputRadioVente.addEventListener('change',(e)=>{
    checkboxOuverte.setAttribute('disabled','true')
    checkboxMesEncheres.setAttribute('disabled','true')
    checkboxRemporte.setAttribute('disabled','true')
    checkboxNonDebuter.removeAttribute('disabled')
    checkboxEncours.removeAttribute('disabled')
    checkboxTerminer.removeAttribute('disabled')
})
