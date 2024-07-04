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


            document.querySelector(".product-no").textContent = data.productNo;
            document.querySelector(".title").textContent = data.title;
            document.querySelector(".seller-id").textContent = data.sellerMemberId;
            document.querySelector(".admin-memo").textContent = data.adminMemo;

            if (data.state != "WAITING") {
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

const done = () => {
    const productNo = document.querySelector(".product-no").textContent;
    console.log(productNo)
    const adminMemo = document.querySelector(".admin-memo").value;
    fetch("/admin/half/list/done", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            productNo: productNo,
            adminMemo: adminMemo
        })
    }).then(
        response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
        }
    ).then(()=>{
        alert("처리되었습니다.");
        window.location.href = "/admin/half/list";
    }).catch(
        error => {
            alert("처리에 실패했습니다.");
            console.error(error);
        }
    );
};