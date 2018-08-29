function indexesInArray(arr) {
    let length=Number(arr[0]);
    let array=[];
    for (let i = 0; i < length; i++) {
        array[i]=0;
    }
    for(let i=1;i<arr.length;i++){
        let tokens=arr[i].split(" - ");
        let index=Number(tokens[0]);
        let value = tokens[1];
        array[index]=value;
    }

    console.log(array.join("\n"));
}
indexesInArray(['3','0 - 5','1 - 6',])