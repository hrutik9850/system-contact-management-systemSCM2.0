

// Get the current theme from localStorage or use the default
let currentTheme = getTheme();
//console.log(currentTheme);
// Initial theme application
document.addEventListener("DOMContentLoaded",() => {
    changeTheme();
});



// Function to change the theme
function changeTheme() {
    // set to web page 
    changPageTheme(currentTheme,currentTheme);


    // Set the listener for the theme change button
    const changeThemeButton = document.querySelector('#theme_chang_button');
    
    changeThemeButton.addEventListener("click",(event) =>{
        let oldTheme = currentTheme ;
        
    
                // Toggle the theme
                if (currentTheme === "dark") {
                    currentTheme = "light";
                } else {
                    currentTheme = "dark";
                }
    
                changPageTheme(currentTheme, oldTheme);
            });

   
}

// Set theme in localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

//chang current page theme
function changPageTheme(theme, oldtheme){
    // Update localStorage with the new theme
    setTheme(currentTheme);
    
    // Remove the old theme and apply the new one
    document.querySelector("html").classList.remove(oldtheme);
    document.querySelector("html").classList.add(currentTheme);    
    // let the curent button text chang 
   document.querySelector('#theme_chang_button').querySelector('span').textContent = theme =="light" ? "Dark" : "Light";


}