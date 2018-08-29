function stringToJSON(arr) {
    let objectInJSON = {};

    for (let i = 0; i < arr.length; i++) {

        let info = arr[i].split(' -> ');
        let key = info[0];
        let value = info[1];
        if (isNaN(Number(value))) {
            objectInJSON[key] = value;
        }
        else {
            objectInJSON[key] = Number(value);
        }
    }

    let objectInString = JSON.stringify(objectInJSON);
    console.log(objectInString);
}

stringToJSON([
    'name -> Angel',
    'surname -> Georgiev',
    'age -> 20',
    'grade -> 6.00',
    'date -> 23/05/1995',
    'town -> Sofia'
])