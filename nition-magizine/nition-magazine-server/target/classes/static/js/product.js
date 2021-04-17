function openDeleteImageModal(productId, productImageId) {
    console.log(productId);
    console.log(productImageId);
    $.ajax({
        url: "/admin/products/" + productId + "/delete/image/" + productImageId + "/modal",
        success: function (data) {
            console.log(data);
            $("#deleteImageHolder").html(data);
            $("#deleteImage").modal("show");
        }
    });
}

function addNewImage(productId) {
    let table = document.getElementById('productInfoTable').getElementsByTagName('tbody')[0];
    console.log(table);

    const tr = document.createElement("tr");
    const tdInput = document.createElement("td");

    const inputGroup = document.createElement("div");
    inputGroup.classList.add("input-group");

    const inputGroupPrepend = document.createElement("div");
    inputGroupPrepend.classList.add("input-group-prepend");

    const inputGroupText1 = document.createElement("span");
    inputGroupText1.classList.add("input-group-text");

    const imageHome = document.createElement("i");
    imageHome.classList.add("imageHome");
    imageHome.classList.add("fa");
    imageHome.classList.add("fa-eye-slash");
    imageHome.style.color = "green";
    inputGroupText1.appendChild(imageHome);

    inputGroupPrepend.appendChild(inputGroupText1);

    const inputElement = document.createElement("input");
    inputElement.setAttribute("type", "text");
    inputElement.setAttribute("id", "newImage");
    inputElement.classList.add("form-control");

    inputGroup.appendChild(inputGroupPrepend);
    inputGroup.appendChild(inputElement);

    tdInput.appendChild(inputGroup);

    const tdRun = document.createElement("td");

    const runButton = document.createElement("a");
    runButton.setAttribute("onclick", "runAddImage(" + productId + ")");

    const imageButton = document.createElement("i");
    imageButton.classList.add("fa");
    imageButton.classList.add("fa-paper-plane");
    imageButton.style.color = "#007bff";
    imageButton.style.cursor = "pointer";
    runButton.appendChild(imageButton);

    tdRun.appendChild(runButton);

    tr.appendChild(tdInput);
    tr.appendChild(tdRun);
    table.appendChild(tr);
}

function runAddImage(productId) {
    console.log(productId);
    const element = document.getElementById("newImage");
    const text = element.innerText;
    console.log(text);
    console.log(element);
}

let checkboxes = document.querySelectorAll("input[type=checkbox]");
let imageHomeList = document.getElementsByClassName("imageHome");

checkboxes.forEach(function(element, key) {
    element.addEventListener('change', function() {
        checkboxes.forEach((current, index) => {
            current.checked = key === index;
        });
        for (let imageHome of imageHomeList) {
            if (imageHome.getAttribute("id") === "imageHome-" + key) {
                imageHome.classList.remove('fa-eye-slash');
                imageHome.classList.add('fa-eye');
            } else {
                imageHome.classList.remove('fa-eye');
                imageHome.classList.add('fa-eye-slash');
            }
        }
    })
});
