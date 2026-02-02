
document.addEventListener("DOMContentLoaded", function() {
const tbody = document.getElementById('productTBody');
const searchInput = document.getElementById('searchInput');
const searchBtn = document.getElementById("searchButton");
const clearBtn = document.getElementById("clearButton");



    console.log("js loaded")

    async function fetchProducts(url) {
        const res = await fetch(url);
        const products = await res.json();
        renderProducts(products);

    }


    function renderProducts(products) {
        tbody.innerHTML = '';

        if (!products || products.length === 0) {
            tbody.innerHTML = `
        <tr>
            <td colspan="8" class="text-center text-muted py-4">
            no matching products found
            </td>
            </tr>
        `;
            return;
        }

        products.forEach((product) => {
            tbody.innerHTML += `
        <tr>
            <td> ${product.id}</td>
            <td>${product.name ?? "-"}</td>
            <td>${product.brand ?? "-"}</td>
            <td>${product.productType?.name ?? "-"}</td>
            <td>${product.productType?.gender ?? "-"}</td>
            <td>${product.sellingPrice ?? 0}</td>
            <td>${product.quantity ?? 0}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary">Edit</button>
                <button class="btn btn-sm btn-outline-danger">Delete</button>
            </td>
        </tr>
        `;
        });





    }

    searchBtn.addEventListener("click", () => {
        console.count("search click handler fired");

        const q = searchInput.value.trim();
        if (!q) {
            fetchProducts("/api/products");
            return;
        }
        fetchProducts(`/api/products/search?q=${encodeURIComponent(q)}`);
    });


    clearBtn.addEventListener("click", () => {
        searchInput.value = "";
        fetchProducts("/api/products");
    });

    searchInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            searchBtn.click();
        }
    });

    fetchProducts("/api/products");

})
