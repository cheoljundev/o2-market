const PAGE = {
    paging: function pagenation(pageNo, totalPageCount, totalElementCount) {

    if (totalElementCount == 0) {
        $('#pagination').html('');
        return false;
    }

    let pageBlock = 10;
    let blockNo = PAGE.toInt(pageNo / (pageBlock + 0.1)) + 1;
    let startPageNo = (blockNo - 1) * pageBlock;
    let endPageNo = blockNo * pageBlock;

    if (endPageNo > totalPageCount - 1) {
        endPageNo = totalPageCount - 1;
    }

    let prevBlockPageNo = (blockNo - 1) * pageBlock - 1;
    let nextBlockPageNo = blockNo * (pageBlock + 1);

     $('#pagination').html('');
     let pageHtml = '';
     if (prevBlockPageNo >= 0) {
     // 나중에 수정할것 1
     pageHtml += `
           <li class="page-item">
                <a class="page-link" href="#" onclick="getList(`+ (prevBlockPageNo + 1) +`);" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
         `;
     }
     for (let i = startPageNo + 1; i <= endPageNo; i++) {
        if (i == pageNo) {
            pageHtml += `
                <li class="page-item active"><a class="page-link">`+(i)+`</a></li>
            `;
        } else {
            pageHtml += `
                <li class="page-item"><a class="page-link" onclick="getList(`+(i)+`)">`+(i )+`</a></li>
            `;
        }
     }
     if (nextBlockPageNo < totalPageCount) {
         pageHtml += `
            <li class="page-item">
                <a class="page-link" href="#" onclick="getList(`+ nextBlockPageNo +`)" aria-label="Next">
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
    }
}