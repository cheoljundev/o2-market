function handleNullInArray(arr) {
    if (!Array.isArray(arr)) {
        throw new Error('Expected an array');
    }
    let result = 1;
    for (let i = 0 ; i < arr.length; i++) {
        if (arr[i] == null || arr[i] == '') {
            result = 0;
        }
    }
    return result;
}


