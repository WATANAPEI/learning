import express from "express";

const app = express();

app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.get("/", (req, res) => {
    //res.render("index.html");
    res.send("hello, world!");
})

app.listen(8080, () => {console.log(`Waiting at port 8080.`)});
