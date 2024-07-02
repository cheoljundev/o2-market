const showDetail = function(btn)  {
    const orderNo = parseInt(btn.textContent);
    fetch("/admin/half/order", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: orderNo
    }).then(
        response => response.json()
    ).then(
        data => {
            const orderDetail = document.querySelector(".order-detail");
            if (orderDetail.classList.contains("d-none")) {
                orderDetail.classList.toggle("d-none");
            }
            orderDetail.querySelector(".order-no").textContent = data.orderNo;
            orderDetail.querySelector(".create-at").textContent = data.createAt;
            orderDetail.querySelector(".title").textContent = data.title;
            orderDetail.querySelector(".recipient-name").textContent = data.recipientName;
            orderDetail.querySelector(".recipient-phone").textContent = data.recipientPhone;
            orderDetail.querySelector(".recipient-address").textContent = data.recipientAddress;
            orderDetail.querySelector(".delivery-memo").textContent = data.deliveryMemo;
            orderDetail.querySelector(".invoice").value = data.invoice;
            if (data.invoice) {
                orderDetail.querySelector(".invoice").readOnly = true;
                orderDetail.querySelector(".btn-send-invoice").disabled = true;
            } else {
                orderDetail.querySelector(".invoice").readOnly = false;
                orderDetail.querySelector(".btn-send-invoice").disabled = false;
            }

        }
    ).catch(
        error => console.error(error)
    );
};

const sendInvoice = () => {
    const orderNo = parseInt(document.querySelector(".order-no").textContent);
    const invoice = document.querySelector(".invoice").value;

    fetch(`/admin/half/order/${orderNo}/invoice`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(invoice)
    }).then(
        response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
        }
    ).then(()=>{
        const orderDetail = document.querySelector(".order-detail");
        orderDetail.classList.toggle("d-none");
        orderDetail.querySelector(".invoice").readOnly = true;
        orderDetail.querySelector(".btn-send-invoice").disabled = true;
        window.location.href = "/admin/half/order";
    }).catch(
        error => console.error(error)
    );
};

const closeDetail = () => {
    const orderDetail = document.querySelector(".order-detail");
    if (!orderDetail.classList.contains("d-none")) {
        orderDetail.classList.toggle("d-none");
    }
};