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
    ).then(response => {
        if (!response.ok) { // 응답 상태가 200-299가 아닌 경우
            throw new Error('응답 오류 : ' + response.statusText);
        }
    }).then(data => {
        alert("추첨이 완료되었습니다.");
        location.href = "/admin/half/event/result";
    }).catch((error) => {
        alert("추첨 중 오류가 발생했습니다.");
        console.log(error);
    });
};

const cancelEvent = () => {
    // 다시 이벤트 콘솔 페이지로 이동
    location.href = "/admin/half/event";
}

const doneEvent = () => {
    fetch("/admin/half/event/done", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(response => {
        if (!response.ok) { // 응답 상태가 200-299가 아닌 경우
            throw new Error('응답 오류 : ' + response.statusText);
        }
    }).then(data => {
        alert("이벤트가 종료되었습니다.");
        location.href = "/admin/half/list";
    }).catch((error) => {
        alert("이벤트 종료 중 오류가 발생했습니다.");
        console.log(error);
    });
}