const PAGE = {
    paging: function(totalPageCount, pageNo, totalElementCount) {
        $('#pagination').html('');

        // 페이지 번호가 유효하지 않을 경우 기본적으로 첫 페이지로 설정
        if (typeof pageNo === "undefined" || pageNo === null || pageNo === "" || pageNo < 0) {
            pageNo = 1;
        }

        if (totalPageCount >= 11) {
            totalPageCount += -2;
        }

        console.log(totalPageCount, pageNo, totalElementCount);

        let pageBlock = 10;
        let blockNo = Math.floor((pageNo - 1) / pageBlock) + 1; // 페이지 블록 계산
        let startPageNo = (blockNo - 1) * pageBlock + 1; // 시작 페이지 번호
        let endPageNo = blockNo * pageBlock; // 끝 페이지 번호

        if (endPageNo > totalPageCount) {
            endPageNo = totalPageCount;
        }

        let prevBlockPageNo = (blockNo - 2) * pageBlock + 1; // 이전 블록의 마지막 페이지 번호
        let nextBlockPageNo = blockNo * pageBlock + 1; // 다음 블록의 첫 페이지 번호

        let pageHtml = "";

        // 이전 페이지 블록 화살표 처리
        if (prevBlockPageNo >= 1) {
            pageHtml += `
                <li class="page-item">
                    <a class="page-link" href="#" onclick="getList(${prevBlockPageNo});" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            `;
        }

        // 페이지 번호 처리
        for (let i = startPageNo; i <= endPageNo; i++) {
            if (i === pageNo) {
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
        if (nextBlockPageNo <= totalPageCount) {
            pageHtml += `
                <li class="page-item">
                    <a class="page-link" href="#" onclick="getList(${nextBlockPageNo});" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            `;
        }

        $('#pagination').append(pageHtml);
    },

    toInt: function(value) {
        if (value != null) {
            return parseInt(value, 10);
        }
        return 0; // 기본 값 설정
    }
};