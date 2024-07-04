document.querySelector(".category-select").addEventListener("change", function() {
    // .category-select의 선택된 값을 가져온다.
    var category = this.value;
    // 새로운 URL로 페이지를 이동한다.
    location.href = "/half/list?selectedCategory=" + category;
});