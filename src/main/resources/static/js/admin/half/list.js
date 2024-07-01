const showDetail = function(btn)  {
    const productNo = parseInt(btn.textContent);
    fetch("/admin/half/list", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: productNo
    }).then(
        response => response.json()
    ).then(
        data => {
            const productDetail = document.querySelector(".product-detail");
            if (productDetail.classList.contains("d-none")) {
                productDetail.classList.toggle("d-none");
            }

            document.querySelector(".title").textContent = data.title;
            document.querySelector(".seller-id").textContent = data.sellerMemberId;
            document.querySelector(".admin-memo").textContent = data.adminMemo;

            if (data.isDone) {
                productDetail.querySelector(".admin-memo").readOnly = true;
                productDetail.querySelector(".btn-done").disabled = true;
            } else {
                productDetail.querySelector(".admin-memo").readOnly = false;
                productDetail.querySelector(".btn-done").disabled = false;
            }
        }
    ).catch(
        error => console.error(error)
    );
};

const closeDetail = () => {
    const orderDetail = document.querySelector(".product-detail");
    if (!orderDetail.classList.contains("d-none")) {
        orderDetail.classList.toggle("d-none");
    }
};
