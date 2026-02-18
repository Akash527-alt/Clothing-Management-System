document.addEventListener("DOMContentLoaded", function() {
    const cart = {};
    const cartBody = document.getElementById("cartTBody");
    const billingTBody = document.getElementById('billingTBody');
    const searchInput = document.getElementById('search_input');
    const searchBtn = document.getElementById("search_button");
    const clearBtn = document.getElementById("clearButton");
    const generateBillbtn=document.getElementById("generateBillbtn")
    generateBillbtn.disabled=true;

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

        const activeproducts=products.filter(product=>product.quantity>0)
        activeproducts.forEach((product) => {
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
                      data-price="${product.sellingPrice}"
                      data-stock="${product.quantity}">
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
            const stock=e.target.dataset.stock;
            console.log("Adding:", id, name, price);
            console.log("raw data - ",id,name,price,stock);

            addProductToCart({ id, name, price, stock });
        }
    });

    function addProductToCart(product) {

        const id = product.id;

        if (cart[id]) {
            if (cart[id].qty < cart[id].stock) {
                  cart[id].qty += 1;
            }
        } else {
            cart[id] = {
                id: product.id,
                name: product.name,
                price: parseFloat(product.price),
                stock: parseInt(product.stock),
                qty: 1
            };
        }

        renderCart();
    }


    function renderCart() {

        cartBody.innerHTML = "";

        Object.values(cart).forEach(item => {

            cartBody.innerHTML += `
            <tr data-id="${item.id}">
                <td>${item.name}</td>
                <td>
                    <button class="btn btn-sm btn-danger fw-bold px-2 qty-minus"
                    ${item.qty <= 1 ? "disabled" : ""}>-</button>
                    <span class="mx-2">${item.qty}</span>
                    <button class="btn btn-sm btn-success fw-bold px-2 qty-plus"
                    ${item.qty >= item.stock ? "disabled" : ""}>+</button>
                </td>
                <td>₹${item.price * item.qty}</td>
                <td>
                    <button class="btn btn-sm btn-danger remove-btn">
                        Remove
                    </button>
                </td>
            </tr>
        `;
        });

            updateGenerateButtonState();
    }


    cartBody.addEventListener("click", function(e) {

        const row = e.target.closest("tr");
        if (!row) return;

        const id = row.dataset.id;
        if (!cart[id]) return;

        // REMOVE
        if (e.target.classList.contains("remove-btn")) {
            delete cart[id];
            renderCart();
            return;
        }

        // INCREMENT
        if (e.target.classList.contains("qty-plus")) {
            if (cart[id].qty < cart[id].stock) {
                cart[id].qty += 1;
                renderCart();
            }
            return;
        }

        // DECREMENT (never auto-delete here)
        if (e.target.classList.contains("qty-minus")) {
            if (cart[id].qty > 1) {
                cart[id].qty -= 1;
                renderCart();
            }
            return;
        }

    });



    generateBillbtn.addEventListener("click", async function(){
        try {

            const payload = {
                items: Object.values(cart).map(item => ({
                    productId: parseInt(item.id),
                    quantity: item.qty
                }))
            };

            const res = await fetch("/api/sales", {
                method: "POST", // or GET depending on your API
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload) // remove if GET
            });

            if (!res.ok) {
                console.error("Generate bill failed:", res.status);
                return;
            }

            const data = await res.json(); // remove if no response body
            console.log("Bill generated:", data);

        } catch (err) {
            console.error("Error while generating bill:", err);
        }

        Object.keys(cart).forEach(id => delete cart[id]);
        renderCart();

        await fetchProducts("/api/products/getAll");

    });


    function updateGenerateButtonState() {
        const isEmpty = Object.keys(cart).length === 0;
        generateBillbtn.disabled = isEmpty;
    }

    fetchProducts("/api/products/getAll");
});
