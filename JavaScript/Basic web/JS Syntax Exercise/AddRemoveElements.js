function addRemoveElements(arr) {
    let nums = [];

    for (let i = 0; i < arr.length; i++) {
        let tokens = arr[i].split(' ');
        let command = tokens[0];
        if (command === 'add') {
            let value = tokens[1];
            nums.push(value);
        }
        else if (command === 'remove') {
            let index = Number(tokens[1]);
            if (index < nums.length && index >= 0) {
                nums.splice(index,1);
            }
        }
    }

    console.log(nums.join("\n"));
}

addRemoveElements([
    'add 3',
    'add 5',
    'remove 2',
    'remove 0',
    'add 7'
]);