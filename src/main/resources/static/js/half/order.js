const useMileage = () => {
    const myMileage = document.querySelector(".my-mileage").textContent;
    const mileageInput = document.querySelector(".mileage-input");
    const price = document.querySelector(".price").value;
    if (myMileage < price) {
        alert("마일리지가 부족합니다.");
        return;
    } else if (myMileage >= price) {
        mileageInput.value = price;
    }
}

const order = () => {
    const mileage = parseInt(document.querySelector(".mileage-input").value);
    const productNo = parseInt(document.querySelector(".product-no").value);
    const recipientName = document.querySelector(".recipient-name").value;
    const recipientPhone = document.querySelector(".recipient-phone").value;
    const recipientAddress = document.querySelector(".recipient-address").value;
    const deliveryMemo = document.querySelector(".delivery-memo").value;
    fetch("/half/order", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            productNo: productNo,
            recipientName: recipientName,
            recipientPhone: recipientPhone,
            recipientAddress: recipientAddress,
            deliveryMemo: deliveryMemo,
            mileage: mileage
        })
    }).then(response => {
        if (response.ok) {
            alert("주문이 완료되었습니다.");
            location.href = "/";
        }
    });
};