import express from "express";
import path from "path";

let router = express.Router();

router.get("/about/:id", (req, res) => {
    let options = {
        // root: path.join(__dirname, 'client')
        root: "/home/app/src/client"
    }
    console.log(`root is ${options.root}`);
    res.sendFile("index.html", options, (err) => {
        if(err) {
            console.log("error occured.");
            res.send("error occured.");
        } else {
            let id = req.params.id;
            console.log(`id is ${id}`);
        }
    });
})

router.get("/", (req, res) => {
    console.log("here is root.");
    res.send("root.");
});

router.get("/about", (req, res) => {
    console.log("here is about page.");
    res.send("about page.");
})


export default router;