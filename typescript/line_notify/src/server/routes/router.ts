import express from "express";

let router = express.Router();

router.get("/:name", (req, res) => {
    let options = {
        // root: path.join(__dirname, 'client')
        root: "/home/app/src/client"
    }
    let filename = req.params.name;
    res.sendFile(filename, options, (err) => {
        if(err) {
            console.log("error occured.");
            res.send("error occured.");
        } else {
            // console.log(`sent ${filename}`);
        }
    });
})

router.get("/", (req, res) => {
    console.log("here is root.");
    res.send("root.");
});


export default router;