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