function sumsByTown(arr) {
    let objects=arr.map(JSON.parse);
    let towns={};
    for (let obj of objects) {
        if(obj.town in towns){
            towns[obj.town]+=obj.income;
        }
        else{
            towns[obj.town]=obj.income;
        }
    }

    let townNames=Object.keys(towns).sort();

    for (let i=0;i<townNames.length;i++)
    {
        console.log(`${townNames[i]} -> ${towns[townNames[i]]}`);
    }
}

sumsByTown([
    '{"town":"Sofia","income":200}',
    '{"town":"Varna","income":120}',
    '{"town":"Pleven","income":60}',
    '{"town":"Varna","income":70}',
])