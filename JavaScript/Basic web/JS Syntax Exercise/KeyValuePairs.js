function keyValuePairs(arr) {
    let pairs = {};
    for (let i = 0; i < arr.length - 1; i++) {
        let tokens = arr[i].split(' ');
        let key = tokens[0];
        let value = tokens[1];
        pairs[key] = value;
    }
    let wantedKey = arr[arr.length - 1];
    if (pairs[wantedKey]) {
        console.log(pairs[wantedKey]);
    }
    else
        console.log("None");
}

keyValuePairs([
    'key value',
    'key eulav',
    'test tset',
    'key'

])