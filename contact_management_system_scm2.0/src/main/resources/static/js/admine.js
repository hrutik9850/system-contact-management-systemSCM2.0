console.log("admin.js Loaded");
// to show the image preview before uploading the image on the server side 
document.querySelector("#image_fill_input").addEventListener("change", function () {
    let file = this.files[0];
    let reader = new FileReader();
    reader.onload = function (e) {
        const output = document.querySelector("#upload_image_priveiw");
        output.src = e.target.result;
    }
    reader.readAsDataURL(file);
});

// Clear the image preview when the reset button is clicked
document.querySelector("button[type='reset']").addEventListener("click", function () {
    const output = document.querySelector("#upload_image_priveiw");
    output.src = "";
});