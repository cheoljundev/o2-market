const PAGE = {
    paging: function pagenation(pageNo, totalPageCount, totalElementCount) {



        let pageBlock = 10;
        let blockNo = PAGE.toInt(pageNo / pageBlock) + 1;
        let startPageNo = (blockNo - 1) * pageBlock;
        let endPageNo = blockNo * pageBlock - 1;

        if (endPageNo > totalPageCount - 1) {
            endPageNo = totalPageCount - 1;
        }

        let prevBlockPageNo = (blockNo - 1) * pageBlock - 1;
        let nextBlockPageNo = blockNo * pageBlock;

         $('#pagination').html('');
         let pageHtml = '';
         if (prevBlockPageNo >= 0) {
         pageHtml += `
               <li class="page-item">
                    <a class="page-link" href="#" onclick="getList(`+ prevBlockPageNo +`);" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
             `;
         }
         for (let i = startPageNo; i <= endPageNo; i++) {
             if (i == 0) {

             } else {
                if (i == pageNo) {
                    pageHtml += `
                        <li class="page-item active"><a class="page-link">`+(i)+`</a></li>
                    `;
                } else {
                    pageHtml += `
                        <li class="page-item"><a class="page-link" onclick="getList(`+(i)+`)">`+(i)+`</a></li>
                    `;
                }
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
        console.log(totalPageCount);
        if (totalPageCount == 0) {
            $('#pagination').html('');
            return false;
        }
    },
    toInt: function(value) {
        if (value != null) {
            return parseInt(value, 10);
        }
    },

    pageRowNumber: function(pageNo, pageSize, index, totalCount) {
        debugger;
        if (totalCount) {
            return totalCount - ((pageNo) * pageSize + index);
        } else {
            return (pageNo) * pageSize + (index + 1);
        }
    }
}
