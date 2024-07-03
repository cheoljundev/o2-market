function handleNullInArray(arr, defaultValue) {
    if (!Array.isArray(arr)) {
        throw new Error('Expected an array');
    }

    return arr.map(item => {
        if (item === null || item === undefined) {
            return defaultValue;
        }
        return item;
    });
}

// 사용 예시
let arr = [1, null, 'Hello', undefined, 5, null, 'World'];

let result = handleNullInArray(arr, 'Default Value');

console.log(result);