var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
        URL.revokeObjectURL(output.src) // free memory
    }
};
const file = new File(["blob data"], "foo.txt", {
    type: "image/*",

});
// Get a reference to the file input
const imageElement = document.querySelector('#employee-img-output');

// Get a reference to the file input
const fileInput = document.querySelector('#file-input');

// Listen for the change event so we can capture the file
fileInput.addEventListener('change', (e) => {
    // Get a reference to the file
    const file = e.target.files[0];

    // Set file as image source
    imageElement.src = URL.createObjectURL(file);
});