function symetricNumbers(arr) {
    let endNumber=Number(arr[0]);
    let result="";
    for (let i = 1; i <=endNumber ; i++) {
        if (isSymetric(i.toString())){
            result+=i+" ";
        }
    }
    console.log(result);

    function isSymetric(number) {
        for (let j = 0; j <number.length/2 ; j++) {
            if(number[j]!=number[number.length-j-1]){
                return false;
            }
        }
        return true;
    }
}
symetricNumbers(["1000"])