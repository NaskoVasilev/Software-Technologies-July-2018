function printLines(lines) {
    let index=0;
    
    while (lines[index]!="Stop") {
        console.log(lines[index]);
        index++;
    }
}
printLines([
    'Line 1',
    'Line 2',
    'Line 3',
    'Stop',
])