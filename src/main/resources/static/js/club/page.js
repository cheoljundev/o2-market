function getpaging(result){
    $('#pagination').html('');
    let pageHtml= '';
    // 이전 페이지 블록 화살표 처리-+
    if (result.paging.startPage != 1) {
        pageHtml += `
            <li class="page-item">
                <a class="page-link" href="#" onclick="getList(${result.paging.startPage - 1});" aria-label="Previous">
                    <span aria-hidden="true">&lt;</span>
                </a>
            </li>
        `;
    }

    // 페이지 번호 처리
    for (let i = result.paging.startPage ; i <= result.paging.endPage; i++) {
        if (i == result.paging.nowPage) {
            pageHtml += `
                <li class="page-item active"><a class="page-link">${i}</a></li>
            `;
        } else {
            pageHtml += `
                <li class="page-item"><a class="page-link" href="#" onclick="getList(${i});">${i}</a></li>
            `;
        }
    }

    // 다음 페이지 블록 화살표 처리
    if (result.paging.endPage != result.paging.lastPage) {
        pageHtml += `
            <li class="page-item">
                <a class="page-link" href="#" onclick="getList(${result.paging.endPage+1 });" aria-label="Next">
                    <span aria-hidden="true">&gt;</span>
                </a>
            </li>
        `;
    }

    $('#pagination').append(pageHtml);
    }
