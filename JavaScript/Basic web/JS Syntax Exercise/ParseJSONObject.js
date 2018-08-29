function parseJSONObject(arr) {
    let objects=[];

    for (let i = 0; i < arr.length; i++) {
        let obj=JSON.parse(arr[i]);
        objects.push(obj);
    }

    for (let obj of objects) {
        console.log(`Name: ${obj["name"]}`);
        console.log(`Age: ${obj["age"]}`);
        console.log(`Date: ${obj["date"]}`);
    }
}
parseJSONObject([
    '{"name":"Gosho","age":10,"date":"19/06/2005"}',
    '{"name":"Tosho","age":11,"date":"04/04/2005"}'
]);