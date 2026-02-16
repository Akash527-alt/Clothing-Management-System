document.addEventListener("DOMContentLoaded", function() {
    const billingTBody = document.getElementById('billingTBody');
    const searchInput = document.getElementById('search_input');
    const searchBtn = document.getElementById("search_button");
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
        billingTBody.innerHTML = '';

        if (!products || products.length === 0) {
            billingTBody.innerHTML = `
        <tr>
          <td colspan="8" class="text-center text-muted py-5">
            no matching products found
          </td>
        </tr>
      `;
            return;
        }

        products.forEach((product) => {
            billingTBody.innerHTML += `
        <tr>
          <td>${product.id}</td>
          <td>${product.name ?? "-"}</td>

          <td>${product.sellingPrice ?? 0}</td>
          <td>${product.quantity ?? 0}</td>
          <td>
                  <button
                      class="btn btn-sm btn-success add-btn"
                      data-id="${product.id}"
                      data-name="${product.name}"
                      data-price="${product.sellingPrice}">
                      Add
                  </button>
          </td>

        </tr>
      `;
        });
    }

    searchBtn.addEventListener("click", () => {
        console.log("haaa in search button");
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
    console.log("haaa");
        if (e.key === "Enter") searchBtn.click();
    });

    billingTBody.addEventListener("click", function (e) {
        if (e.target.classList.contains("add-btn")) {

            const id = e.target.dataset.id;
            const name = e.target.dataset.name;
            const price = e.target.dataset.price;

            console.log("Adding:", id, name, price);

            addProductToCart({ id, name, price });
        }
    });

    function addProductToCart(product) {

        const cartBody = document.getElementById("cartTBody");

        cartBody.innerHTML += `
            <tr>
                <td>${product.name}</td>
                <td>1</td>
                <td>₹${product.price}</td>
                <td>
                    <button class="btn btn-sm btn-danger remove-btn">
                        Remove
                    </button>
                </td>
            </tr>
        `;
    }



    fetchProducts("/api/products/getAll");
});
