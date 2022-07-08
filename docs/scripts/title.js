(() => {
    const span = document.getElementById("title_text");

    const speed_typing = 100;
    const speed_backspace = 50;
    const delay_backwards = 2000;

    const values = [
        ["JavaMarkdown", "","JavaMarkdown"],
        ["*JavaMarkdown*", "font-style: italic;","JavaMarkdown"],
        ["~~JavaMarkdown~~", "text-decoration: line-through;","JavaMarkdown"],
        ["**JavaMarkdown**", "font-weight: bolder;","JavaMarkdown"],
        ["`JavaMarkdown`","background-color: #343942;border-radius: 10px;padding: 2px;font-family: monospace;","JavaMarkdown"]
    ];

    var index = 0;
    var showCount = 0;
    var deleting = false;

    var interval = null;


    function step() {
        if (deleting) {
            showCount--;
            span.style.cssText = "";
            span.innerText = values[index][0].substring(0,showCount);
            if (showCount == 0) {
                index = (index + 1) % values.length;
                deleting = false;
                clearInterval(interval);
                interval = setInterval(step,speed_typing);
            }
        } else {
            showCount++;
            span.innerText = values[index][0].substring(0,showCount);
            if(showCount == values[index][0].length) {
                span.innerText = values[index][2];
                span.style.cssText = values[index][1];
                clearInterval(interval);
                deleting = true;
                setTimeout(() => {
                    interval = setInterval(step,speed_backspace)
                },delay_backwards);
            }
        }
    }

    interval = setInterval(step,speed_typing);



})();
