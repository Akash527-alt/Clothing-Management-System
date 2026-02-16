document.addEventListener("DOMContentLoaded", function() {
    const tbody = document.getElementById('productTBody');
    const searchInput = document.getElementById('searchInput');
    const searchBtn = document.getElementById("searchButton");
    const clearBtn = document.getElementById("clearButton");
    const addProductForm = document.getElementById("addProductForm");
    const editStockForm = document.getElementById("editStockForm");

    console.log("js loaded");

    async function fetchProducts(url) {
        const res = await fetch(url);

        if (!res.ok) {
            console.error("Fetch failed:", res.status, res.statusText, "URL:", url);
            renderProducts([]); // show "no matching products found"
            return;
        }

        const data = await res.json();

        const products = Array.isArray(data) ? data : (data.content || []);
        renderProducts(products);
    }


    function renderProducts(products) {
        tbody.innerHTML = '';

        if (!products || products.length === 0) {
            tbody.innerHTML = `
        <tr>
          <td colspan="9" class="text-center text-muted py-4">
            no matching products found
          </td>
        </tr>
      `;
            return;
        }

        products.forEach((product) => {
            tbody.innerHTML += `
        <tr>
          <td>${product.id}</td>
          <td>${product.name ?? "-"}</td>
          <td>${product.brand ?? "-"}</td>
          <td>${product.category ?? "-"}</td>
          <td>${product.gender ?? "-"}</td>
          <td>${product.sellingPrice ?? 0}</td>
          <td>${product.quantity?? "--/--/----" }</td>
          <td>${product.addedDate ?? 0}</td>
          <td>
            <button
              class="btn btn-sm btn-outline-primary edit-btn"
              data-id="${product.id}"
              data-price="${product.sellingPrice}"
              data-quantity="${product.quantity}">
              Edit
            </button>
          </td>
        </tr>
      `;
        });
    }

    searchBtn.addEventListener("click", () => {
        const q = searchInput.value.trim();

        if (!q) {
            fetchProducts("/api/products/getAll");
            return;
        }
        fetchProducts(`/api/products/search?q=${encodeURIComponent(q)}`);
    });

    clearBtn.addEventListener("click", () => {
        searchInput.value = "";
        fetchProducts("/api/products/getAll");
    });

    searchInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") searchBtn.click();
    });

    addProductForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const formData = new FormData(addProductForm);

    const product = {
        name: formData.get("productName"),
        brand: formData.get("brand"),
        category: formData.get("category"),
        gender: formData.get("gender"),
        costPrice: parseFloat(formData.get("costPrice")),
        sellingPrice: parseFloat(formData.get("sellingPrice")),
        quantity: parseInt(formData.get("stockQuantity")),
        addedDate: formData.get("date")
    };

    const res = await fetch("/api/products", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(product)
    });

    if (!res.ok) {
        console.error("Failed to add product");
        return;
    }

    addProductForm.reset();

    const modalElement = document.getElementById("addProductModal");
    const modalInstance = bootstrap.Modal.getInstance(modalElement);
    modalInstance.hide();

    fetchProducts("/api/products");
    });


    tbody.addEventListener("click", function (e) {
        if (e.target.classList.contains("edit-btn")) {

            const id = e.target.dataset.id;
            const price = e.target.dataset.price;
            const quantity = e.target.dataset.quantity;

            document.getElementById("editProductId").value = id;
            document.getElementById("editSellingPrice").value = price;
            document.getElementById("editStockQuantity").value = quantity;

            const modal = new bootstrap.Modal(document.getElementById("editStockModal"));
            modal.show();
        }
    });



    editStockForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const id = document.getElementById("editProductId").value;

        const updatedProduct = {
            sellingPrice: parseFloat(document.getElementById("editSellingPrice").value),
            quantity: parseInt(document.getElementById("editStockQuantity").value)
        };

        const res = await fetch(`/api/products/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updatedProduct)
        });

        if (!res.ok) {
            console.error("Failed to update product");
            return;
        }

        const modalElement = document.getElementById("editStockModal");
        const modalInstance = bootstrap.Modal.getInstance(modalElement);
        modalInstance.hide();

        fetchProducts("/api/products/${id}");
    });


    fetchProducts("/api/products/getAll");
});
