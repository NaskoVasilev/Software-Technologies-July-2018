function extractLowerCaseWords(strings) {
    let capitalCaseWords=[];
    for (let str of strings) {
        let words=str.split(new RegExp(/\W/)).filter(x=>x!="");
        for (let word of words) {
            if(word===word.toUpperCase()){
                capitalCaseWords.push(word);
            }
        }
    }

    console.log(capitalCaseWords.join(", "))
}

extractLowerCaseWords([
    'We start by HTML, CSS, JavaScript, JSON and REST.',
    'Later we touch some PHP, MySQL and SQL.',
    'Later we play with C#, EF, SQL Server and ASP.NET MVC.',
    'Finally, we touch some Java, Hibernate and Spring.MVC.'
])