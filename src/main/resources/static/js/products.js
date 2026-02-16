document.addEventListener("DOMContentLoaded", function() {
    const tbody = document.getElementById('productTBody');
    const searchInput = document.getElementById('searchInput');
    const searchBtn = document.getElementById("searchButton");
    const clearBtn = document.getElementById("clearButton");

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
            <button class="btn btn-sm btn-outline-primary">Edit</button>
            <button class="btn btn-sm btn-outline-danger">Delete</button>
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





    fetchProducts("/api/products/getAll");
});
