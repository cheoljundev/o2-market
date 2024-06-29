const startEvent = () => {
    if (document.querySelector(".number").value === "") {
        alert("추첨할 물품 수를 입력해주세요.");
        return false;
    }
    // category name에 선택된 것들의 id를 배열로 만들어서 fetch로 보내기
    const category = document.getElementsByName("category");
    const categoryCodes = [];
    category.forEach((c) => {
        if (c.checked) {
            categoryCodes.push(c.id);
        }
    });
    fetch(
        "/admin/half/event/draw",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                categoryCodes: categoryCodes,
                number: document.querySelector(".number").value
            })
        }
    )
};