// Array that stores all products added from the form.
const products = [];

// Get all required DOM nodes using getElementById as requested.
const productNameInput = document.getElementById("productName");
const productCategoryInput = document.getElementById("productCategory");
const productPriceInput = document.getElementById("productPrice");
const addProductBtn = document.getElementById("addProductBtn");
const productsList = document.getElementById("productsList");
const productFeedback = document.getElementById("productFeedback");

// Creates a single product card/list item and appends it to the list.
function renderProduct(product) {
  const listItem = document.createElement("li");
  listItem.className = "product-item";

  const nameElement = document.createElement("div");
  nameElement.className = "product-name";
  nameElement.textContent = product.name;

  const metaElement = document.createElement("div");
  metaElement.className = "product-meta";

  const categoryElement = document.createElement("span");
  categoryElement.textContent = product.category;

  const priceElement = document.createElement("span");
  priceElement.textContent = "$" + product.price.toFixed(2);

  metaElement.appendChild(categoryElement);
  metaElement.appendChild(priceElement);

  listItem.appendChild(nameElement);
  listItem.appendChild(metaElement);

  productsList.appendChild(listItem);
}

// Validates form inputs and returns an object if all values are valid.
function validateProductForm() {
  const name = productNameInput.value.trim();
  const category = productCategoryInput.value.trim();
  const priceValue = productPriceInput.value.trim();
  const price = Number(priceValue);

  if (!name || !category || !priceValue) {
    productFeedback.textContent = "Please fill in all product fields.";
    return null;
  }

  if (Number.isNaN(price) || price <= 0) {
    productFeedback.textContent = "Please enter a valid price greater than 0.";
    return null;
  }

  productFeedback.textContent = "";

  return {
    name,
    category,
    price,
  };
}

// Clears all form fields after successful submission.
function clearForm() {
  productNameInput.value = "";
  productCategoryInput.value = "";
  productPriceInput.value = "";
  productNameInput.focus();
}

// Handles click on Add Product button:
// validates input, updates array, renders UI, then clears fields.
addProductBtn.addEventListener("click", function () {
  const product = validateProductForm();

  if (!product) {
    return;
  }

  products.push(product);
  renderProduct(product);
  clearForm();
});
