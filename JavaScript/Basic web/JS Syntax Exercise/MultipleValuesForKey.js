function multipleValuesForKey(arr) {
    let values = [];
    let wantedKey = arr[arr.length-1];

    for (let i = 0; i < arr.length - 1; i++) {
        let tokens = arr[i].split(' ');
        let key = tokens[0];
        let value = tokens[1];

        if (key == wantedKey) {
            values.push(value);
        }
    }

    if (values.length==0)
    {
        console.log("None");
    }
    else{
        for (let i = 0; i < values.length; i++) {
            console.log(values[i]);
        }
    }
}
multipleValuesForKey([
    '3 test',
    '3 test1',
    '4 test2',
    '4 test3',
    '4 test5',
    '4'
]);