const confirmOrder = (btn) =>{
    const orderNo = parseInt(btn.getAttribute("data-orderno"));
    fetch("/member/confirmOrder", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: orderNo,
    }).then((res) => {
        if(!res.ok){
            throw new Error("주문 확인 실패");
        }
    }).then(() => {
        alert("주문 확인 완료");
        location.reload();
    }).catch((error) => {
        console.error("주문 확인 실패", error);
    });
};